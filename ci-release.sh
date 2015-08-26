#! /bin/bash

if [ -z "$MVNCMD" ]
then
	MVNCMD=mvn
fi

COMMAND="$MVNCMD release:prepare -DdryRun=true -Dresume=false"
echo $COMMAND
$COMMAND
RETVAL=$?
if [ $RETVAL -eq 0 ]
then
	COMMAND="$MVNCMD release:clean"
	echo $COMMAND
	$COMMAND
	COMMAND="$MVNCMD release:prepare -DdryRun=false -Dresume=false"
	echo $COMMAND
	$COMMAND
	RETVAL=$?
	if [ $RETVAL -eq 0 ]
	then
		COMMAND="$MVNCMD release:perform"
		echo $COMMAND
		$COMMAND
		RETVAL=$?
	fi
fi
exit $RETVAL
