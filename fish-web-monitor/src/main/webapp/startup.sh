#!/bin/sh
APP_PATH=`pwd`
export APP_PATH
WEBPIDFILE=WEBPID.ini
WEB_PIDFILE=$APP_PATH/$WEBPIDFILE
#是否存在INI文件，不存在直接启动
if [ -f "$WEB_PIDFILE" ];then
#存在INI文件,读取PID
WEBPID=`awk -F "=" '{if($1=="WEB_PID")print $2}' $WEBPIDFILE`
#检查进程中是否存在相应的PID
checkReuslt=`ps -o pid |grep $WEBPID |awk '{if($1=='$WEBPID')print "ok"}'`
#存在退出启动操作
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

JAVA_OPTS="-Xms1024m -Xmx1024m -verbosegc -Xloggc:logs/gc_log.log"
export JAVA_OPTS

#java  $JAVA_OPTS -classpath $CLASSPATH -Dfile.encoding=utf-8 com.yihuacomputer.fish.web.FishConsole
nohup java  $JAVA_OPTS -classpath $CLASSPATH -Dfile.encoding=utf-8 com.yihuacomputer.fish.web.FishConsole >/dev/null 2>1 &
echo "Web App is Starting"