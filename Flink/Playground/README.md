### Pre-Requisites
* Docker
* Docker Compose
* Java 11
* Flink binary 1.16.0

#### Start Cluster
> docker-compose up


#### Install Test Job
> ~/Downloads/flink-1.16.0/bin/flink run test/TopSpeedWindowing.jar


#### Monitor Jobs
Navigate to: http://localhost:8081/ to access the Flink UI

## Run Job
> make build

> make <job_name>

Valid job_names:
* top_speed_windowing
* grade_salary_join
* state_machine_alerts




### Job Descriptions
#### Top Speed Windowing
When no options are passed, an in memory CarSource is used to generate the input stream. Where the elements are: carID, speed,
distance, and timestamp. (carID = 0 or 1)

This source is then grouped by carID and then a window is applied. This example uses the `trigger` method on the window
which specifies the conditions under which the window is considered ready for the window function to be applied.

In our case we use a Delta trigger, and it is defined as such to trigger when the first arg `triggerMeters` is greater
than or equal to the custom delta function which calculates the difference in distance between the current data element
and the last. In the end, having the effect of triggering the window every time two data points are 50 or more meters apart.

Finally, after defining the trigger of our window, the window function defined simply as a `maxBy(1)` where "1" refers to the
column index to maximize by. In this case, speed.

This produces a new DataStream of max speeds within a 50 meter window grouped by carId.

When running this job, you should see a continuous stream of carId, speed, distance, timestamp in the task_manager container
logs.


#### Grade Salary Joining
Uses a GradeSource and SalarySource to generate input data randomly in-memory.  `runWindowJoin` then takes both
sources, and joins them together; defining a join condition on "name" (f0 for both sources). And it finally defines 
an `apply` which builds the output shape of the new DataStream.

Worth noting that this join is windowed. This means that data elements from each of the two sources will be joined when
they are in the same time window. Our application uses a "Tumbling Window Join". Which acts as an inner join. This means
if there is one element in a window that cannot match to an element from the other steam in the same window, there is no new
element in the new output stream. 

Read more about windows here: https://nightlies.apache.org/flink/flink-docs-master/docs/dev/datastream/operators/joining/

When running this job, you should see a continuous stream of name, grade, salary in the task_manager container
logs.


#### State Machine
This example is the most interesting because it does not use an in-memory-source. And even better it leverages Kafka event streams.
When running this job using the Makefile, there are actually 2 jobs launched; which run continuously in parallel to one another.

The first one is responsible simply for creating messages and putting them into a kafka topic for later 
consumption. It uses a custom generator:
https://github.com/apache/flink/tree/259c7a4776af4d9d7f174f871fb2d4145787f794/flink-examples/flink-examples-streaming/src/main/java/org/apache/flink/streaming/examples/statemachine/generator

This creates "events" in the source data stream. Where each event has an IP address, and a "type" which is one letter "a" through "g". Each IP is supposed to have
an event go in alphabetical order. Depending on the error probability provided, the generator will sometimes produce events that go out of order
for an event. This is an invalid state transition.
https://github.com/apache/flink/tree/259c7a4776af4d9d7f174f871fb2d4145787f794/flink-examples/flink-examples-streaming/src/main/java/org/apache/flink/streaming/examples/statemachine/event

These events are put into the kafka topic. 

The second job that's running is consuming events from this topic
and determining if any invalid state transitions have occurred.

It does this by taking the source data stream, grouping by the event's IP address, and then calling a map function over
each event, using a custom mapper `StateMachineMapper`.

This mapper takes each event, within an IP address, and:
* determines what the current state is
  * State is an enum that is persisted
* Checks to see if the input event transitions the current state in a valid way.
* If not, creates a new element in the Alert DataStream
* Otherwise, updates the current state to the input event state

Keep in mind that an instance of the StateMachineMapper is created for **_each_** IP address because of how
we grouped the data stream.

What's also interesting in this example is that we configure the job to use rocksdb as the persistence store. Thus, all state
is put into rocksdb and recalled from it within the `StateMachineMapper`. More info here:
https://nightlies.apache.org/flink/flink-docs-master/docs/ops/state/state_backends/#the-embeddedrocksdbstatebackend

Finally, an output DataStream "alerts" is created which contains: IP address, current state, invalid next state

This example demonstrates how to separate and independent process can work together in a streaming pipeline.

When running this job, you should see a continuous stream of alerts: ip address, and from -> to state in the task_manager
container logs.

