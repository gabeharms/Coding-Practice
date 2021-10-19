
### Summary

This tutorial helps setup and run a flink cluster where a fraud detection program is running on top of it.

### Running

Download flink from here:
https://flink.apache.org/downloads.html#apache-flink-1132

First start your cluster:
`~/Downloads/flink-1.13.0/bin/start-cluster.sh`

Ensure you have the following in your `~/Downloads/flink-1.13.0/conf/flink-conf.yaml`:
``
rest.port: 8081
rest.address: 0.0.0.0
```

Then navigate to localhost:8081 to monitor your jobs.

Next compile the FraudDetection application:
`mvn clean package`

Then run it on the cluster:
`~/Downloads/flink-1.13.0/bin/flink run ./target/frauddetection-0.1.jar`


This program uses a TransactionSource which creates an infinite number of credit card transactions, so the job
will run until you stop it.

The sink is a no-op and the way to see which alerts are coming through is to inspect ~/Downloads/flink-1.13.0/log/flink-gabeharms-taskexecutor-0-MACLAP-0SZJG5M.log
