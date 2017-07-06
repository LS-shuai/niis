#!/bin/sh

#check JAVA_HOME & java
noJavaHome=false
if [ -z "$JAVA_HOME" ] ; then
    noJavaHome=true
fi
if [ ! -e "$JAVA_HOME/bin/java" ] ; then
    noJavaHome=true
fi
if $noJavaHome ; then
    echo
    echo "Error: JAVA_HOME environment variable is not set."
    echo
    exit 1
fi
#==============================================================================
#set JAVA_OPTS
JAVA_OPTS="-server -Xms2048M -Xmx2048M -XX:MaxPermSize=256M "
#JAVA_OPTS="-server -Xms4G -Xmx4G -XX:MaxPermSize=64M  -XX:+AggressiveOpts -XX:MaxDirectMemorySize=6G"
#performance Options
#JAVA_OPTS="$JAVA_OPTS -Xss256k"
#JAVA_OPTS="$JAVA_OPTS -XX:+AggressiveOpts"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseBiasedLocking"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseFastAccessorMethods"
#JAVA_OPTS="$JAVA_OPTS -XX:+DisableExplicitGC"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseParNewGC"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseConcMarkSweepGC"
#JAVA_OPTS="$JAVA_OPTS -XX:+CMSParallelRemarkEnabled"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseCMSCompactAtFullCollection"
#JAVA_OPTS="$JAVA_OPTS -XX:+UseCMSInitiatingOccupancyOnly"
#JAVA_OPTS="$JAVA_OPTS -XX:CMSInitiatingOccupancyFraction=75"
#JAVA_OPTS="$JAVA_OPTS -XX:CMSInitiatingOccupancyFraction=75"
#GC Log Options
#JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCApplicationStoppedTime"
#JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCTimeStamps"
#JAVA_OPTS="$JAVA_OPTS -XX:+PrintGCDetails"
#debug Options
#JAVA_OPTS="$JAVA_OPTS -Xdebug -Xrunjdwp:transport=dt_socket,address=8065,server=y,suspend=n"
#==============================================================================

#set HOME
CURR_DIR=`pwd`
ZP_HOME=`pwd`
cd $CURR_DIR
if [ -z "$ZP_HOME" ] ; then
    echo
    echo "Error: ZP_HOME environment variable is not defined correctly."
    echo
    exit 1
fi
#==============================================================================

#set CLASSPATH
ZP_CLASSPATH="./classes:lib/*"
#==============================================================================

#startup Server
RUN_CMD="\"$JAVA_HOME/bin/java\""
RUN_CMD="$RUN_CMD -DZP_HOME=\"$ZP_HOME\""
RUN_CMD="$RUN_CMD -classpath \"$ZP_CLASSPATH\""
RUN_CMD="$RUN_CMD $JAVA_OPTS"
RUN_CMD="$RUN_CMD com.tools.Server  $@"
RUN_CMD="$RUN_CMD >> \"$ZP_HOME/logs/console_`date +%Y%m%d`.log\" 2>&1 &"
echo $RUN_CMD
eval $RUN_CMD
#==============================================================================
