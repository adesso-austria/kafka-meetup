 # Turbine Simulation
 
 This is a simulation of wind-turbines from various wind-parks that produce values for our Apache Kafka broker
 
 ## How to build

All build artifacts are located in folder **target**
 
 `
 mvn clean install
 `

 ## How to start
 
 Execute the shell script "startTurbines.sh" located in the root of this project.
 
 Remove, add or changes values as you please. Needs at least 3 properties per row, separation by ';'.
 
 - windparkID
 - turbineID
 - Mode
 
 `
 ./startTurbines.sh turbines.csv
 `