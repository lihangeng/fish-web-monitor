#!/bin/sh
APP_PATH=`pwd`
export APP_PATH
WEBPIDFILE=WEBPID.ini
WEB_PIDFILE=$APP_PATH/$WEBPIDFILE
#Isn't exist PID file,don't exist will running
if [ -f "$WEB_PIDFILE" ];then
#Exist PID file,read PID config
WEBPID=`awk -F "=" '{if($1=="WEB_PID")print $2}' $WEBPIDFILE`
#check pid from process
checkReuslt=`ps -o pid |grep $WEBPID |awk '{if($1=='$WEBPID')print "ok"}'`
#pid in the process,goto end
if [[ $checkReuslt = "ok" ]];then
        echo "Web App is started"
        exit;
fi
fi
APP_LIB_PATH="$APP_PATH/WEB-INF/lib"
export APP_LIB_PATH

PATH_SEPARATOR=:

CP=`ls $APP_LIB_PATH/*.jar | paste -s -d"$PATH_SEPARATOR" - `

CLASSPATH=$CP:$APP_CLASSES_PATH:$APP_PATH/WEB-INF/classes
export CLASSPATH

JAVA_OPTS="-server -Xms2048m -Xmx2048m -XX:PermSize=1024m -XX:MaxPermSize=2048m -verbosegc -Xloggc:logs/gc_log.log"
export JAVA_OPTS

#java  $JAVA_OPTS -classpath $CLASSPATH -Dfile.encoding=utf-8 com.yihuacomputer.fish.web.FishConsole
nohup java  $JAVA_OPTS -classpath $CLASSPATH -Dfile.encoding=utf-8 com.yihuacomputer.fish.web.FishConsole >/dev/null 2>1 &
echo "Web App is Starting"