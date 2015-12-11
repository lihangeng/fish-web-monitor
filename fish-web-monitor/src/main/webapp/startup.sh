#!/bin/sh
APP_PATH="/home/yihua/ATMVS"
export APP_PATH

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