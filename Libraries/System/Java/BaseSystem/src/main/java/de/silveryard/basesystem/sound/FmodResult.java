package de.silveryard.basesystem.sound;

/**
 * Created by Sebif on 25.03.2017.
 */
public enum FmodResult {
    /**
     * FMOD_OK
     */
    FMOD_OK(0),
    /**
     * FMOD_ERR_BADCOMMAND
     */
    FMOD_ERR_BADCOMMAND(1),
    /**
     * FMOD_ERR_CHANNEL_ALLOC
     */
    FMOD_ERR_CHANNEL_ALLOC(2),
    /**
     * FMOD_ERR_CHANNEL_STOLEN
     */
    FMOD_ERR_CHANNEL_STOLEN(3),
    /**
     * FMOD_ERR_DMA
     */
    FMOD_ERR_DMA(4),
    /**
     * FMOD_ERR_DSP_CONNECTION
     */
    FMOD_ERR_DSP_CONNECTION(5),
    /**
     * FMOD_ERR_DSP_DONTPROCESS
     */
    FMOD_ERR_DSP_DONTPROCESS(6),
    /**
     * FMOD_ERR_DSP_FORMAT
     */
    FMOD_ERR_DSP_FORMAT(7),
    /**
     * FMOD_ERR_DSP_INUSE
     */
    FMOD_ERR_DSP_INUSE(8),
    /**
     * FMOD_ERR_DSP_NOTFOUND
     */
    FMOD_ERR_DSP_NOTFOUND(9),
    /**
     * FMOD_ERR_DSP_RESERVED
     */
    FMOD_ERR_DSP_RESERVED(10),
    /**
     * FMOD_ERR_DSP_SILENCE
     */
    FMOD_ERR_DSP_SILENCE(11),
    /**
     * FMOD_ERR_DSP_TYPE
     */
    FMOD_ERR_DSP_TYPE(12),
    /**
     * FMOD_ERR_FILE_BAD
     */
    FMOD_ERR_FILE_BAD(13),
    /**
     * FMOD_ERR_FILE_COULDNOTSEEK
     */
    FMOD_ERR_FILE_COULDNOTSEEK(14),
    /**
     * FMOD_ERR_FILE_DISKEJECTED
     */
    FMOD_ERR_FILE_DISKEJECTED(15),
    /**
     * FMOD_ERR_FILE_EOF
     */
    FMOD_ERR_FILE_EOF(16),
    /**
     * FMOD_ERR_FILE_ENDOFDATA
     */
    FMOD_ERR_FILE_ENDOFDATA(17),
    /**
     * FMOD_ERR_FILE_NOTFOUND
     */
    FMOD_ERR_FILE_NOTFOUND(18),
    /**
     * FMOD_ERR_FORMAT
     */
    FMOD_ERR_FORMAT(19),
    /**
     * FMOD_ERR_HEADER_MISMATCH
     */
    FMOD_ERR_HEADER_MISMATCH(20),
    /**
     * FMOD_ERR_HTTP
     */
    FMOD_ERR_HTTP(21),
    /**
     * FMOD_ERR_HTTP_ACCESS
     */
    FMOD_ERR_HTTP_ACCESS(22),
    /**
     * FMOD_ERR_HTTP_PROXY_AUTH
     */
    FMOD_ERR_HTTP_PROXY_AUTH(23),
    /**
     * FMOD_ERR_HTTP_SERVER_ERROR
     */
    FMOD_ERR_HTTP_SERVER_ERROR(24),
    /**
     * FMOD_ERR_HTTP_TIMEOUT
     */
    FMOD_ERR_HTTP_TIMEOUT(25),
    /**
     * FMOD_ERR_INITIALIZATION
     */
    FMOD_ERR_INITIALIZATION(26),
    /**
     * FMOD_ERR_INITIALIZED
     */
    FMOD_ERR_INITIALIZED(27),
    /**
     * FMOD_ERR_INTERNAL
     */
    FMOD_ERR_INTERNAL(28),
    /**
     * FMOD_ERR_INVALID_FLOAT
     */
    FMOD_ERR_INVALID_FLOAT(29),
    /**
     * FMOD_ERR_INVALID_HANDLE
     */
    FMOD_ERR_INVALID_HANDLE(30),
    /**
     * FMOD_ERR_INVALID_PARAM
     */
    FMOD_ERR_INVALID_PARAM(31),
    /**
     * FMOD_ERR_INVALID_POSITION
     */
    FMOD_ERR_INVALID_POSITION(32),
    /**
     * FMOD_ERR_INVALID_SPEAKER
     */
    FMOD_ERR_INVALID_SPEAKER(33),
    /**
     * FMOD_ERR_INVALID_SYNCPOINT
     */
    FMOD_ERR_INVALID_SYNCPOINT(34),
    /**
     * FMOD_ERR_INVALID_THREAD
     */
    FMOD_ERR_INVALID_THREAD(35),
    /**
     * FMOD_ERR_INVALID_VECTOR
     */
    FMOD_ERR_INVALID_VECTOR(36),
    /**
     * FMOD_ERR_MAXAUDIBLE
     */
    FMOD_ERR_MAXAUDIBLE(37),
    /**
     * FMOD_ERR_MEMORY
     */
    FMOD_ERR_MEMORY(38),
    /**
     * FMOD_ERR_MEMORY_CANTPOINT
     */
    FMOD_ERR_MEMORY_CANTPOINT(39),
    /**
     * FMOD_ERR_NEEDS3D
     */
    FMOD_ERR_NEEDS3D(40),
    /**
     * FMOD_ERR_NEEDSHARDWARE
     */
    FMOD_ERR_NEEDSHARDWARE(41),
    /**
     * FMOD_ERR_NET_CONNECT
     */
    FMOD_ERR_NET_CONNECT(42),
    /**
     * FMOD_ERR_NET_SOCKET_ERROR
     */
    FMOD_ERR_NET_SOCKET_ERROR(43),
    /**
     * FMOD_ERR_NET_URL
     */
    FMOD_ERR_NET_URL(44),
    /**
     * FMOD_ERR_NET_WOULD_BLOCK
     */
    FMOD_ERR_NET_WOULD_BLOCK(45),
    /**
     * FMOD_ERR_NOTREADY
     */
    FMOD_ERR_NOTREADY(46),
    /**
     * FMOD_ERR_OUTPUT_ALLOCATED
     */
    FMOD_ERR_OUTPUT_ALLOCATED(47),
    /**
     * FMOD_ERR_OUTPUT_CREATEBUFFER
     */
    FMOD_ERR_OUTPUT_CREATEBUFFER(48),
    /**
     * FMOD_ERR_OUTPUT_DRIVERCALL
     */
    FMOD_ERR_OUTPUT_DRIVERCALL(49),
    /**
     * FMOD_ERR_OUTPUT_FORMAT
     */
    FMOD_ERR_OUTPUT_FORMAT(50),
    /**
     * FMOD_ERR_OUTPUT_INIT
     */
    FMOD_ERR_OUTPUT_INIT(51),
    /**
     * FMOD_ERR_OUTPUT_NODRIVERS
     */
    FMOD_ERR_OUTPUT_NODRIVERS(52),
    /**
     * FMOD_ERR_PLUGIN
     */
    FMOD_ERR_PLUGIN(53),
    /**
     * FMOD_ERR_PLUGIN_MISSING
     */
    FMOD_ERR_PLUGIN_MISSING(54),
    /**
     * FMOD_ERR_PLUGIN_RESOURCE
     */
    FMOD_ERR_PLUGIN_RESOURCE(55),
    /**
     * FMOD_ERR_PLUGIN_VERSION
     */
    FMOD_ERR_PLUGIN_VERSION(56),
    /**
     * FMOD_ERR_RECORD
     */
    FMOD_ERR_RECORD(57),
    /**
     * FMOD_ERR_REVERB_CHANNELGROUP
     */
    FMOD_ERR_REVERB_CHANNELGROUP(58),
    /**
     * FMOD_ERR_REVERB_INSTANCE
     */
    FMOD_ERR_REVERB_INSTANCE(59),
    /**
     * FMOD_ERR_SUBSOUNDS
     */
    FMOD_ERR_SUBSOUNDS(60),
    /**
     * FMOD_ERR_SUBSOUND_ALLOCATED
     */
    FMOD_ERR_SUBSOUND_ALLOCATED(61),
    /**
     * FMOD_ERR_SUBSOUND_CANTMOVE
     */
    FMOD_ERR_SUBSOUND_CANTMOVE(62),
    /**
     * FMOD_ERR_TAGNOTFOUND
     */
    FMOD_ERR_TAGNOTFOUND(63),
    /**
     * FMOD_ERR_TOOMANYCHANNELS
     */
    FMOD_ERR_TOOMANYCHANNELS(64),
    /**
     * FMOD_ERR_TRUNCATED
     */
    FMOD_ERR_TRUNCATED(65),
    /**
     * FMOD_ERR_UNIMPLEMENTED
     */
    FMOD_ERR_UNIMPLEMENTED(66),
    /**
     * FMOD_ERR_UNINITIALIZED
     */
    FMOD_ERR_UNINITIALIZED(67),
    /**
     * FMOD_ERR_UNSUPPORTED
     */
    FMOD_ERR_UNSUPPORTED(68),
    /**
     * FMOD_ERR_VERSION
     */
    FMOD_ERR_VERSION(69),
    /**
     * FMOD_ERR_EVENT_ALREADY_LOADED
     */
    FMOD_ERR_EVENT_ALREADY_LOADED(70),
    /**
     * FMOD_ERR_EVENT_LIVEUPDATE_BUSY
     */
    FMOD_ERR_EVENT_LIVEUPDATE_BUSY(71),
    /**
     * FMOD_ERR_EVENT_LIVEUPDATE_MISMATCH
     */
    FMOD_ERR_EVENT_LIVEUPDATE_MISMATCH(72),
    /**
     * FMOD_ERR_EVENT_LIVEUPDATE_TIMEOUT
     */
    FMOD_ERR_EVENT_LIVEUPDATE_TIMEOUT(73),
    /**
     * FMOD_ERR_EVENT_NOTFOUND
     */
    FMOD_ERR_EVENT_NOTFOUND(74),
    /**
     * FMOD_ERR_STUDIO_UNINITIALIZED
     */
    FMOD_ERR_STUDIO_UNINITIALIZED(75),
    /**
     * FMOD_ERR_STUDIO_NOT_LOADED
     */
    FMOD_ERR_STUDIO_NOT_LOADED(76),
    /**
     * FMOD_ERR_INVALID_STRING
     */
    FMOD_ERR_INVALID_STRING(77),
    /**
     * FMOD_ERR_ALREADY_LOCKED
     */
    FMOD_ERR_ALREADY_LOCKED(78),
    /**
     * FMOD_ERR_NOT_LOCKED
     */
    FMOD_ERR_NOT_LOCKED(79),
    /**
     * FMOD_ERR_RECORD_DISCONNECTED
     */
    FMOD_ERR_RECORD_DISCONNECTED(80),
    /**
     * FMOD_ERR_TOOMANYSAMPLES
     */
    FMOD_ERR_TOOMANYSAMPLES(81),

    /**
     * FMOD_RESULT_FORCEINT
     */
    FMOD_RESULT_FORCEINT(65536);

    /**
     * Converts an integer value to an enum value
     * @param value Integer value
     * @return Enum value
     */
    public static FmodResult getEnumValue(int value){
        FmodResult[] values = FmodResult.values();
        for(int i = 0; i < values.length; i++){
            if(values[i].getValue() == value){
                return values[i];
            }
        }
        return null;
    }

    private int value;

    /**
     * Constructor
     * @param value Integer value
     */
    FmodResult(int value){
        this.value = value;
    }

    /**
     * @return Gets the enums integer value
     */
    public int getValue(){
        return value;
    }
}
