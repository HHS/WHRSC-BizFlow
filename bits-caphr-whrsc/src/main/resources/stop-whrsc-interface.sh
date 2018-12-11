#!/bin/sh

#BASEDIR=$(dirname "$0")
BASEDIR=$(cd `dirname $0` && pwd)
echo using BASEDIR of $BASEDIR
cd $BASEDIR

#Get the PID of the process
PID=$(cat $BASEDIR/whrscinterface.pid)

/bin/kill $PID

rm $BASEDIR/whrscinterface.pid