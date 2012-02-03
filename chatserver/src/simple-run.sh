#!/bin/sh

./run.sh | grep -v 'w/o source' | uniq > jpf.log
