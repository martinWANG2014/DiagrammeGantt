#!/bin/bash
if [[ $# -eq 1 ]]; then
	for f in $1*.txt
	do
		echo $f
		java -jar DiagrammeGantt.jar $f 
	done	
else
	echo "Usage: runDiagrammeGantt.sh FileDir"
	exit
fi





