# Kubernetes Example

## Background

This example demonstrates deploying `Apache Flink StateFun`, together with a single [remote function](hello/functions.py) to `kubernetes`.

All the components are already pre-configured and contains all the resource definitions ready to be applied with `kubectl`.
All the resources are deployed to a dedicated namespace - `statefun`.

## What's inside?

The following components will be installed:

1. Apache Kafka
2. MinIO (S3 compatible object store)
3. A StateFun service [hello.py](hello/hello.py)
4. Apache Flink Cluster that executes a StateFun Job.  Take a look at [module.yaml](04-statefun/00-module.yaml) config-map. 
	The resources defined at [statefun-runtime.yaml](04-statefun/ 01-statefun-runtime.yaml) are already pre-configured for that specific example.


## Run
The k8 chart definitions in this repo will allow you to run an entire Flink Statefulfun cluster plus a Kafka isntance.

### Start cluster
> make start

Now you can explore Apache Flink's WEB interface:

[http://localhost:8081/#/overview](http://localhost:8081/#/overview)

### Send message to Kafka
> make invoke_function

This will prompt some input from you and eventually put into the `names` kafka topic. Which will in turn trigger
the function to be invoked. (note: you should now see the job from the Flink UI send records for each message you create)

### See logs from function
> make function_logs
This will show the logs for each function invocation of the python function.

### Teardown cluster
> make clean