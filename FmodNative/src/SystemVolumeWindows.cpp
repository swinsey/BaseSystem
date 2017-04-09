#include "SystemVolume.h"

#if defined(WIN32)
#include <Windows.h>
#include <CommCtrl.h>
#include <mmdeviceapi.h>
#include <endpointvolume.h>

#define SAFE_RELEASE(punk)  \
              if ((punk) != NULL)  \
                { (punk)->Release(); (punk) = NULL; }

namespace {
	//-----------------------------------------------------------
	// Client implementation of IAudioEndpointVolumeCallback
	// interface. When a method in the IAudioEndpointVolume
	// interface changes the volume level or muting state of the
	// endpoint device, the change initiates a call to the
	// client's IAudioEndpointVolumeCallback::OnNotify method.
	//-----------------------------------------------------------
	class CAudioEndpointVolumeCallback : public IAudioEndpointVolumeCallback
	{
		LONG _cRef;

	public:
		CAudioEndpointVolumeCallback() :
			_cRef(1)
		{
		}

		~CAudioEndpointVolumeCallback()
		{
		}

		// IUnknown methods -- AddRef, Release, and QueryInterface

		ULONG STDMETHODCALLTYPE AddRef()
		{
			return InterlockedIncrement(&_cRef);
		}

		ULONG STDMETHODCALLTYPE Release()
		{
			ULONG ulRef = InterlockedDecrement(&_cRef);
			if (0 == ulRef)
			{
				delete this;
			}
			return ulRef;

		}

		HRESULT STDMETHODCALLTYPE QueryInterface(REFIID riid, VOID **ppvInterface)
		{
			if (IID_IUnknown == riid)
			{
				AddRef();
				*ppvInterface = (IUnknown*)this;
			}
			else if (__uuidof(IAudioEndpointVolumeCallback) == riid)
			{
				AddRef();
				*ppvInterface = (IAudioEndpointVolumeCallback*)this;
			}
			else
			{
				*ppvInterface = NULL;
				return E_NOINTERFACE;
			}
			return S_OK;
		}

		// Callback method for endpoint-volume-change notifications.

		HRESULT STDMETHODCALLTYPE OnNotify(PAUDIO_VOLUME_NOTIFICATION_DATA pNotify)
		{
			if (pNotify == NULL)
			{
				return E_INVALIDARG;
			}
			/*if (g_hDlg != NULL && pNotify->guidEventContext != g_guidMyContext)
			{
				PostMessage(GetDlgItem(g_hDlg, IDC_CHECK_MUTE), BM_SETCHECK,
					(pNotify->bMuted) ? BST_CHECKED : BST_UNCHECKED, 0);

				PostMessage(GetDlgItem(g_hDlg, IDC_SLIDER_VOLUME),
					TBM_SETPOS, TRUE,
					LPARAM((UINT32)(MAX_VOL*pNotify->fMasterVolume + 0.5)));
			}*/
			return S_OK;
		}
	};

	GUID g_guidMyContext = GUID_NULL;
	IAudioEndpointVolume *g_pEndptVol = NULL;
	IMMDeviceEnumerator* pEnumerator = NULL;
	IMMDevice* pDevice = NULL;
	CAudioEndpointVolumeCallback EPVolEvents;
}

int sysvol_init() {
	HRESULT hr = S_OK;

	CoInitialize(NULL);

	hr = CoCreateGuid(&g_guidMyContext);
	if (FAILED(hr)) {
		return SYSVOL_INIT_ERROR;
	}

	hr = CoCreateInstance(
		__uuidof(MMDeviceEnumerator),
		NULL, CLSCTX_INPROC_SERVER,
		__uuidof(IMMDeviceEnumerator),
		(void**)&pEnumerator);
	if (FAILED(hr)) {
		return SYSVOL_INIT_ERROR;
	}

	hr = pEnumerator->GetDefaultAudioEndpoint(eRender, eConsole, &pDevice);
	if (FAILED(hr)) {
		return SYSVOL_INIT_ERROR;
	}

	hr = pDevice->Activate(__uuidof(IAudioEndpointVolume),
		CLSCTX_ALL, NULL, (void**)&g_pEndptVol);
	if (FAILED(hr)) {
		return SYSVOL_INIT_ERROR;
	}

	hr = g_pEndptVol->RegisterControlChangeNotify(
		(IAudioEndpointVolumeCallback*)&EPVolEvents
	);
	if (FAILED(hr)) {
		return SYSVOL_INIT_ERROR;
	}

	InitCommonControls();
	
	return SYSVOL_INIT_OK;
}
void sysvol_dispose() {
	if (pEnumerator != NULL) {
		g_pEndptVol->UnregisterControlChangeNotify(
			(IAudioEndpointVolumeCallback*)&EPVolEvents);
	}
	SAFE_RELEASE(pEnumerator);
	SAFE_RELEASE(pDevice);
	SAFE_RELEASE(g_pEndptVol);
	CoUninitialize();
}

bool sysvol_get_mute() {
	BOOL mt;
	g_pEndptVol->GetMute(&mt);
	return static_cast<bool>(mt);
}
void sysvol_set_mute(bool mute) {
	BOOL mt = static_cast<BOOL>(mute);
	g_pEndptVol->SetMute(mt, &g_guidMyContext);
}

float sysvol_get_master_volume() {
	float level;
	g_pEndptVol->GetMasterVolumeLevelScalar(&level);
	return level;
}
void sysvol_set_master_volume(float volume) {
	g_pEndptVol->SetMasterVolumeLevelScalar(volume, &g_guidMyContext);
}

int sysvol_get_num_output_channels() {
	UINT channels;
	g_pEndptVol->GetChannelCount(&channels);
	return static_cast<int>(channels);
}
int sys_vol_get_output_channel_type(int index){
	//TODO Return Real values
	switch (sysvol_get_num_output_channels()) {
	case 1: {
		if (index == 0) {
			return CHANNEL_TYPE_MONO;
		}
		break;
	}
	case 2: {
		switch (index) {
		case 0: {
			return CHANNEL_TYPE_STEREO_LEFT;
		}
		case 1: {
			return CHANNEL_TYPE_STEREO_RIGHT;
		}
		}
		break;
	}
	case 4: {
		switch (index) {
		case 0: {
			return CHANNEL_TYPE_QUAD_FRONT_LEFT;
		}
		case 1: {
			return CHANNEL_TYPE_QUAD_FRONT_RIGHT;
		}
		case 2: {
			return CHANNEL_TYPE_QUAD_REAR_LEFT;
		}
		case 3: {
			return CHANNEL_TYPE_QUAD_REAR_RIGHT;
		}
		}
		break;
	}
	case 6: {
		switch (index) {
		case 0: {
			return CHANNEL_TYPE_5_1_FRONT_LEFT;
		}
		case 1: {
			return CHANNEL_TYPE_5_1_FRONT_CENTER;
		}
		case 2: {
			return CHANNEL_TYPE_5_1_FRONT_RIGHT;
		}
		case 3: {
			return CHANNEL_TYPE_5_1_REAR_LEFT;
		}
		case 4: {
			return CHANNEL_TYPE_5_1_REAR_RIGHT;
		}
		case 5: {
			return CHANNEL_TYPE_5_1_SUB;
		}
		}
		break;
	}
	case 8: {
		switch (index) {
		case 0: {
			return CHANNEL_TYPE_7_1_FRONT_LEFT;
		}
		case 1: {
			return CHANNEL_TYPE_7_1_FRONT_CENTER;
		}
		case 2: {
			return CHANNEL_TYPE_7_1_FRONT_RIGHT;
		}
		case 3: {
			return CHANNEL_TYPE_7_1_SIDE_LEFT;
		}
		case 4: {
			return CHANNEL_TYPE_7_1_SIDE_RIGHT;
		}
		case 5: {
			return CHANNEL_TYPE_7_1_REAR_LEFT;
		}
		case 6: {
			return CHANNEL_TYPE_7_1_REAR_RIGHT;
		}
		case 7: {
			return CHANNEL_TYPE_7_1_SUB;
		}
		}
		break;
	}
	}

	return CHANNEL_TYPE_INVALID;
}
float sysvol_get_output_volume(int index) {
	float vol;
	g_pEndptVol->GetChannelVolumeLevelScalar(index, &vol);
	return vol;
}
void sysvol_set_output_volume(int index, float volume) {
	g_pEndptVol->SetChannelVolumeLevelScalar(index, volume, &g_guidMyContext);
}

#endif