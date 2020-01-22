#!/bin/bash
# -------- loop through csv rows & start a turbine simulation for each line --------
IFS=";"
while read -r windparkID turbineID mode
  do
    java -Xms128m -Xmx256m -Dlog4j.configuration=log4j.properties -jar ./target/turbine-1.0-SNAPSHOT-shaded.jar -k 0.0.0.0:9092 -t "$turbineID" -w "$windparkID" -m "$mode" &
  done < "$1"