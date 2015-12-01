@echo off
title Fish web monitor System Console
@echo Fish web monitore System Console

@ECHO Set environment variables, loops all the jar files in the lib directory of the current directory, and set the CLASSPATH


FOR %%F IN (WEB-INF\lib\*.jar) DO call :addcp %%F
goto extlibe
:addcp
SET CLASSPATH=%CLASSPATH%;%1
goto :eof

:extlibe


SET CLASSPATH=%CLASSPATH%;
SET CLASSPATH
java -Xms512m -Xmx1024m -verbosegc -Xloggc:logs/atmvs_gc_log.log -Dfile.encoding=UTF-8 com.yihuacomputer.fish.web.FishConsole

pause 
