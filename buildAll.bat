@echo off

SETLOCAL

SET APF_VERSION=1.0-SNAPSHOT
SET APFCREATOR_VERSION=1.0-SNAPSHOT
SET APPSDK_VERSION=1.0-SNAPSHOT
SET BASESYSTEM_VERSION=1.0-SNAPSHOT
SET TRANSPORT_VERSION=1.0-SNAPSHOT

echo Finding Tools

REM Finding VS
for /f "usebackq tokens=1* delims=: " %%i in (`%~dp0vswhere.exe -latest`) do (
  if /i "%%i"=="installationPath" set InstallDir=%%j
)

REM Finding CMake
SET CMAKE_PATH=%InstallDir%\Common7\IDE\CommonExtensions\Microsoft\CMake\CMake\bin\cmake.exe

if not exist "%CMAKE_PATH%" (
  echo Failed to detect cmake in vs installation
  goto :end
)

REM Finding MSBuild
SET MSBUILD_PATH=%InstallDir%\MSBuild\15.0\Bin\MSBuild.exe

if not exist "%MSBUILD_PATH%" (
  echo Failed to detect msbuild in vs installation
  goto :end
)

REM Defining Directories
SET REPO_PATH=%~dp0
SET BUILD_PATH=%REPO_PATH%build
SET TOOLS_PATH=%BUILD_PATH%\Tools
SET LIBS_PATH=%BUILD_PATH%\Libs
SET STAGING_AREA_PATH=%BUILD_PATH%\StagingArea

REM Cleaning up
ECHO Cleaning up
del /s /f /q %BUILD_PATH%\*.* > Nul
for /f %%f in ('dir /ad /b %BUILD_PATH%\') do rd /s /q %BUILD_PATH%\%%f > Nul

REM Creating Directories
if not exist "%BUILD_PATH%" mkdir "%BUILD_PATH%"
if not exist "%STAGING_AREA_PATH%" mkdir "%STAGING_AREA_PATH%"
if not exist "%TOOLS_PATH%" mkdir "%TOOLS_PATH%"
if not exist "%LIBS_PATH%" mkdir "%LIBS_PATH%"

REM Building SDLNative
echo Building SDLNative

SET SDLNATIVE_PATH=%REPO_PATH%SDLNative
SET SDLNATIVE_DST_DIR_PATH=%LIBS_PATH%\SDLNative
SET SDLNATIVE_DST_PATH=%SDLNATIVE_DST_DIR_PATH%\SDLNative.dll
SET SDLNATIVE_STAGING_PATH=%STAGING_AREA_PATH%\SDLNative
SET SDLNATIVE_BIN_PATH=%SDLNATIVE_STAGING_PATH%\Debug\SDLNative.dll
SET SDL2_SRC_PATH=%SDLNATIVE_PATH%\sdl\win\debug\sdl2\lib\x64\*.dll
SET SDLIMG_SRC_PATH=%SDLNATIVE_PATH%\sdl\win\debug\sdl2_image\lib\x64\*.dll
SET SDLTTF_SRC_PATH=%SDLNATIVE_PATH%\sdl\win\debug\sdl2_ttf\lib\x64\*.dll

if not exist "%SDLNATIVE_DST_DIR_PATH%" mkdir "%SDLNATIVE_DST_DIR_PATH%"
if not exist "%SDLNATIVE_STAGING_PATH%" mkdir "%SDLNATIVE_STAGING_PATH%"

cd "%SDLNATIVE_STAGING_PATH%"
"%CMAKE_PATH%" "%SDLNATIVE_PATH%" -G "Visual Studio 15 2017 Win64"
"%MSBUILD_PATH%" "%SDLNATIVE_STAGING_PATH%\SDLNative.vcxproj" /p:configuration=debug
if not exist "%SDLNATIVE_BIN_PATH%" (
  echo Compilation of SDLNative failed
  goto :end
)

copy "%SDLNATIVE_BIN_PATH%" "%SDLNATIVE_DST_PATH%"
xcopy "%SDL2_SRC_PATH%" "%SDLNATIVE_DST_DIR_PATH%" /y
xcopy "%SDLIMG_SRC_PATH%" "%SDLNATIVE_DST_DIR_PATH%" /y
xcopy "%SDLTTF_SRC_PATH%" "%SDLNATIVE_DST_DIR_PATH%" /y
cd "%REPO_PATH%"

Building FmodNative
echo Building FmodNative

SET FMODNATIVE_PATH=%REPO_PATH%FmodNative
SET FMODNATIVE_DST_DIR_PATH=%LIBS_PATH%\FmodNative
SET FMODNATIVE_DST_PATH=%FMODNATIVE_DST_DIR_PATH%\FmodNative.dll
SET FMODNATIVE_STAGING_PATH=%STAGING_AREA_PATH%\FmodNative
SET FMODNATIVE_BIN_PATH=%FMODNATIVE_STAGING_PATH%\Debug\FmodNative.dll
SET FMOD_SRC_PATH=%FMODNATIVE_PATH%\fmod\win\lib\fmodL64.dll
SET FMOD_DST_PATH=%FMODNATIVE_DST_DIR_PATH%\fmodL64.dll

if not exist "%FMODNATIVE_DST_DIR_PATH%" mkdir "%FMODNATIVE_DST_DIR_PATH%"
if not exist "%FMODNATIVE_STAGING_PATH%" mkdir %FMODNATIVE_STAGING_PATH%"

cd %FMODNATIVE_STAGING_PATH%
"%CMAKE_PATH%" "%FMODNATIVE_PATH%" -G "Visual Studio 15 2017 Win64"
"%MSBUILD_PATH%" "%FMODNATIVE_STAGING_PATH%\FmodNative.vcxproj" /p:configuration=debug
if not exist "%FMODNATIVE_BIN_PATH%" (
  echo Compilation of FmodNative failed
  goto :end
)
copy "%FMODNATIVE_BIN_PATH%" "%FMODNATIVE_DST_PATH%"
copy "%FMOD_SRC_PATH%" "%FMOD_DST_PATH%"
cd "%REPO_PATH%"

REM Building Java Projects
echo Building Java Projects
call "%~dp0\3rdParty\apache-maven-3.3.9\bin\mvn" -f "%~dp0\pom.xml" install 


REM Copying AppManager
echo Copying AppManager

SET APPMANAGER_SRC_PATH=%REPO_PATH%AppManager\target\jfx\native\AppManager
SET APPMANAGER_SRC_EXE_PATH=%APPMANAGER_SRC_PATH%\AppManager.exe
SET APPMANAGER_DST_PATH=%TOOLS_PATH%\AppManager\

if not exist "%APPMANAGER_SRC_EXE_PATH%" (
  echo Failed to build AppManager
  goto :end
)
xcopy %APPMANAGER_SRC_PATH% %APPMANAGER_DST_PATH% /s /e

Copying APFCreator
echo Copying APFCreator

SET APFCREATOR_SRC_PATH=%REPO_PATH%APFCreator\target\APFCreator-%APFCREATOR_VERSION%-jar-with-dependencies.jar
SET APFCREATOR_DST_PATH=%TOOLS_PATH%\APFCreator.jar 

if not exist "%APFCREATOR_SRC_PATH%" (
  echo Failed to build APFCreator
  goto :end
)
copy "%APFCREATOR_SRC_PATH%" "%APFCREATOR_DST_PATH%"

REM Copying APF
echo Copying APF

SET APF_SRC_PATH=%REPO_PATH%APF\target\APF-%APF_VERSION%.jar
SET APF_DST_PATH=%LIBS_PATH%\APF.jar

if not exist "%APF_SRC_PATH%" (
  echo Failed to build APFCreator
  goto :end
)
copy "%APF_SRC_PATH%" "%APF_DST_PATH%"

REM Copying AppSDK
echo Copying AppSDK

SET APPSDK_SRC_NODEP_PATH=%REPO_PATH%AppSDK\target\AppSDK-%APPSDK_VERSION%.jar
SET APPSDK_DST_NODEP_PATH=%LIBS_PATH%\AppSDK-NoDependencies.jar
SET APPSDK_SRC_DEP_PATH=%REPO_PATH%AppSDK\target\AppSDK-%APPSDK_VERSION%-jar-with-dependencies.jar
SET APPSDK_DST_DEP_PATH=%LIBS_PATH%\AppSDK.jar 

if not exist "%APPSDK_SRC_NODEP_PATH%" (
  echo Failed to build APFCreator
  goto :end
)
if not exist "%APPSDK_SRC_DEP_PATH%" (
  echo Failed to build APFCreator
  goto :end
)
copy "%APPSDK_SRC_NODEP_PATH%" "%APPSDK_DST_NODEP_PATH%"
copy "%APPSDK_SRC_DEP_PATH%" "%APPSDK_DST_DEP_PATH%"

REM Copying BaseSystem
echo Copying BaseSystem

SET BASESYSTEM_SRC_NODEP_PATH=%REPO_PATH%BaseSystem\target\BaseSystem-%BASESYSTEM_VERSION%.jar
SET BASESYSTEM_DST_NODEP_PATH=%LIBS_PATH%\BaseSystem-NoDependencies.jar
SET BASESYSTEM_SRC_DEP_PATH=%REPO_PATH%BaseSystem\target\BaseSystem-%BASESYSTEM_VERSION%-jar-with-dependencies.jar
SET BASESYSTEM_DST_DEP_PATH=%LIBS_PATH%\BaseSystem.jar 

if not exist "%BASESYSTEM_SRC_NODEP_PATH%" (
  echo Failed to build BaseSystem
  goto :end
)
if not exist "%BASESYSTEM_SRC_DEP_PATH%" (
  echo Failed to build BaseSystem
  goto :end
)
copy "%BASESYSTEM_SRC_NODEP_PATH%" "%BASESYSTEM_DST_NODEP_PATH%"
copy "%BASESYSTEM_SRC_DEP_PATH%" "%BASESYSTEM_DST_DEP_PATH%"

REM Copying Transport
echo Copying Transport

SET TRANSPORT_SRC_PATH=%REPO_PATH%Transport\target\Transport-%TRANSPORT_VERSION%.jar
SET TRANSPORT_DST_PATH=%LIBS_PATH%\Transport.jar

if not exist "%TRANSPORT_SRC_PATH%" (
  echo Failed to build Transport
  goto :end
)
copy "%TRANSPORT_SRC_PATH%" "%TRANSPORT_DST_PATH%" 

echo ok
cd "%REPO_PATH%"
ENDLOCAL
EXIT /b 0

:end
cd "%REPO_PATH%"
ENDLOCAL
EXIT /b 1