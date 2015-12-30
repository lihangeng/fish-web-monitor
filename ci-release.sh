#! /bin/bash

if [ -z "$MVNCMD" ]
then
	MVNCMD=mvn
fi
COMMAND="$MVNCMD release:prepare -Dmaven.test.skip=true -DdryRun=true -Dresume=false"
echo $COMMAND
echo $COMMAND
echo RETVAL=$?
RETVAL=0
if [ $RETVAL -eq 0 ]
then
	COMMAND="$MVNCMD release:prepare -Dmaven.test.skip=true -DdryRun=false -Dresume=false"
	echo $COMMAND
	$COMMAND
	RETVAL=$?
	if [ $RETVAL -eq 0 ]
	then
		COMMAND="$MVNCMD release:perform -Dmaven.test.skip=true"
		echo $COMMAND
		$COMMAND
		RETVAL=$?
	fi
fi
exit $RETVAL
