
@echo off
set RELEASE_VERSION=1.6.0
set NEXT_VERSION=1.7.0

echo %~dp0
echo RELEASE: %RELEASE_VERSION%  NEXT: %NEXT_VERSION%

if not "%BRANCH_DIR%" == ""  goto release

echo Environment [BRANCH_DIR] undefined
goto end

:release
echo Release Begin
call mvn release:prepare -B -DautoVersionSubmodules=true -DtagBase=http://svn.nj.yihuacomputer.com/svn/fish/tags/%BRANCH_DIR% -DreleaseVersion=%RELEASE_VERSION% -DdevelopmentVersion=%NEXT_VERSION%-SNAPSHOT
call mvn release:perform
echo Release End

:end