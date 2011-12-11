#!/bin/sh

if [ "$1" = "" ]
then
	echo "*** Error: This script requires one argument: QueueTest or ProdCons" 1>&2
	exit 1
fi

case "$1" in
  q* | Q*) ~/jpf/jpf-core/bin/jpf QueueTest.jpf;;
  p* | P*) ~/jpf/jpf-core/bin/jpf ProdCons.jpf;;
  *)
	echo "*** Error: This script requires one argument: QueueTest or ProdCons" 1>&2
	exit 1
  ;;
esac
