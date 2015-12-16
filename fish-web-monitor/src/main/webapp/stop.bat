@echo off
set "APP_HOME=%CD%"
set "WEB_PIDFILE=%APP_HOME%\WEBPID.ini"
for /f "skip=1 delims== tokens=1,2" %%i in (%WEB_PIDFILE%) do if "%%i"=="WEB_PID" echo WEB_IDΪ :%%j & set "CURRPID=%%j"

echo WEB_PID:%CURRPID%

taskkill /pid %CURRPID% /f /T
echo WebApp stopSuccessful