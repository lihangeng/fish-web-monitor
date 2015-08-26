LAYER_VERSION=0.0.9
export LAYER_VERSION
M2_HOME=/opt/tools/apache-maven-3.0.5
MVNCMD="mvn --batch-mode"
export MVNCMD
PATH=$PATH:/usr/local/bin:$M2_HOME/bin
export PATH
CI_SCRIPT_PATH=$WORKSPACE
export CI_SCRIPT_PATH
if [ -z "$1" ]
then
	echo "Please set branch name"
	exit -100
fi
GIT_BRANCH_NAME=$1
export GIT_BRANCH_NAME

# checkout
git checkout $1
