#!/bin/sh
APP_PATH=`pwd`
export APP_PATH
WEBPIDFILE=WEBPID.ini
WEB_PIDFILE=$APP_PATH/$WEBPIDFILE
WEBPID=`awk -F "=" '{if($1=="WEB_PID")print $2}' $WEBPIDFILE`
checkReuslt=`ps -e -o pid |grep $WEBPID |awk '{if($1=='$WEBPID')print "ok"}'`
if [[ $checkReuslt == "ok"  ]];then
`kill -9 $WEBPID`
fi
echo "Web App is stopped!"
