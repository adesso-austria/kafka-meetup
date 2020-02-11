# Turbine Simulation

This is a simulation of wind-turbines from various wind-parks that produce values for our Apache Kafka broker

## How to build

All build artifacts are located in folder **target**

```bash
mvn clean install
```

## How to start

Execute the shell script "startTurbines.sh" and provide a csv-file as an argument.

Remove, add or changes values as you please. Needs at least 2 properties per row, separation by ';'.

* windparkID (String)
* turbineID (String)

```bash
./startTurbines.sh turbines.csv
```
