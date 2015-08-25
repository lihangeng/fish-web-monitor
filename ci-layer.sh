#!/bin/bash

if [ -z "$MVNCMD" ]
then
	MVNCMD=mvn
fi

echo mvn layer:roadmap
$MVNCMD com.yihuacomputer.maven:layer-maven-plugin:$LAYER_VERSION:roadmap
RETVAL=$?
if [ $RETVAL -eq 0 ]
then
	sh target/roadmap.sh
	RETVAL=$?
else
	echo "roadmap fail!"
fi
exit $RETVAL
