@echo off
title Fish web monitor System Console
@echo Fish web monitore System Console

@ECHO Set environment variables, loops all the jar files in the lib directory of the current directory, and set the CLASSPATH
set "APP_HOME=%cd%"

set "WEB_PIDFILE=%APP_HOME%\WEBPID.ini"

for /f "skip=1 delims== tokens=1,2" %%i in (%WEB_PIDFILE%) do if "%%i"=="WEB_PID" echo WEB_IDΪ :%%j & set "CURRPID=%%j"

set "pid=%CURRPID%"
echo %pid%

tasklist /FI "PID eq %pid%"|find /I "PID"

if "%ERRORLEVEL%"=="0" ( goto display )



FOR %%F IN (WEB-INF\lib\*.jar) DO call :addcp %%F
goto extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
goto :eof

:extlibe


SET CLASSPATH=%CLASSPATH%;WEB-INF\classes
SET CLASSPATH
java -server -Xms512m -Xmx1024m -XX:PermSize=512m -XX:MaxPermSize=1024m -verbosegc -Xloggc:logs/atmvs_gc_log.log -Dfile.encoding=UTF-8 com.yihuacomputer.fish.web.FishConsole
goto end

:display
echo WebApp already Running 
PAUSE  

:end 
echo WebApp is starting
