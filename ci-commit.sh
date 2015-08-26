#! /bin/bash

git add -- pom.xml
git commit -m "[layer-maven-plugin] update version"
git push origin $GIT_BRANCH_NAME
