#!/bin/sh

export JPF_HOME=${HOME}/jpf/jpf-core

if [ "`grep -c '\.\.\.' EchoSim.java`" != "0" ] ; then
	javac -classpath "${JPF_HOME}/build/jpf.jar" \
	EchoClient.java EchoServer.java env/java/*/*.java centr/rt/*.java
else
	javac -classpath "${JPF_HOME}/build/jpf.jar" \
				 *.java env/java/*/*.java centr/rt/*.java
fi
