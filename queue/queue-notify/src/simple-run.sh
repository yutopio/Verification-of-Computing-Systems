#!/bin/sh

./run.sh "$1" | grep -v 'w/o source' | uniq > jpf.log
