@ECHO OFF

SET repo_dir=%~dp0
SET build_dir=%repo_dir%\build
SET staging_dir=%build_dir%\staging_area

if not exist %build_dir% mkdir %build_dir%
if not exist %staging_dir% mkdir %staging_dir%

REM Cleaning up
ECHO Cleaning up
del /s /f /q %build_dir%\*.* > Nul
for /f %%f in ('dir /ad /b %build_dir%\') do rd /s /q %build_dir%\%%f > Nul

REM Documentation
ECHO Packaging Documentation
SET documentation_dir=%staging_dir%\Documentation
if not exist %documentation_dir% mkdir %documentation_dir%
SET documentation_src=%repo_dir%\Documentation

xcopy %documentation_src% %documentation_dir% /s /e > Nul

REM Tools
SET tools_dir=%staging_dir%\Tools
if not exist %tools_dir% mkdir %tools_dir%

REM Tools/AppManager
ECHO Packaging AppManager
SET appmanager_dir=%tools_dir%\AppManager
SET appmanager_src=%repo_dir%\AppManager\target\jfx\native
if not exist %appmanager_dir% mkdir %appmanager_dir%

xcopy %appmanager_src% %appmanager_dir% /s /e > Nul



REM Libraries
ECHO Packaging Libraries
SET libraries_dir=%staging_dir%\Libraries
if not exist %libraries_dir% mkdir %libraries_dir%

REM Libraries/AppSDK
SET appsdk_dst=%libraries_dir%\appsdk.jar
SET appsdk_src=%repo_dir%\AppSDK\target\AppSDK-1.0-SNAPSHOT-jar-with-dependencies.jar

copy %appsdk_src% %appsdk_dst% > Nul



REM Runtime
ECHO Packaging Runtime
SET runtime_dir=%staging_dir%\Runtime
SET runtime_sys_dir=%runtime_dir%\sys
SET runtime_sys_res_dir=%runtime_sys_dir%\res
SET runtime_sys_res_images_dir=%runtime_sys_res_dir%\images
SET runtime_sys_res_fonts_dir=%runtime_sys_res_dir%\fonts
SET runtime_sys_bin_dir=%runtime_sys_dir%\bin
SET runtime_app_dir=%runtime_dir%\app
SET runtime_data_dir=%runtime_dir%\data

if not exist %runtime_dir% mkdir %runtime_dir%
if not exist %runtime_sys_dir% mkdir %runtime_sys_dir%
if not exist %runtime_sys_res_dir% mkdir %runtime_sys_res_dir%
if not exist %runtime_sys_res_images_dir% mkdir %runtime_sys_res_images_dir%
if not exist %runtime_sys_res_fonts_dir% mkdir %runtime_sys_res_fonts_dir%
if not exist %runtime_sys_bin_dir% mkdir %runtime_sys_bin_dir%
if not exist %runtime_app_dir% mkdir %runtime_app_dir%
if not exist %runtime_data_dir% mkdir %runtime_data_dir%

SET runtime_bin_dst=%runtime_sys_bin_dir%\system.jar
SET runtime_bin_src=%repo_dir%\TouchDisplay\target\TouchDisplay-1.0-SNAPSHOT-jar-with-dependencies.jar
copy %runtime_bin_src% %runtime_bin_dst% > Nul

SET runtime_images_src=%repo_dir%\TouchDisplay_Resources\Images\Atlas
xcopy %runtime_images_src% %runtime_sys_res_images_dir% /s /e > Nul

SET runtime_fonts_src=%repo_dir%\TouchDisplay_Resources\Fonts
xcopy %runtime_fonts_src% %runtime_sys_res_fonts_dir% /s /e > Nul

SET runtime_sdl_src=%repo_dir%\SDLNative\build\win\x64
xcopy %runtime_sdl_src% %runtime_sys_bin_dir% /s /e > Nul