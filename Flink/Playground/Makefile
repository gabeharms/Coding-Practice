
default: build load_job

build:
	mvn package

top_speed_windowing:
	~/Downloads/flink-1.16.0/bin/flink run target/top-speed-windowing-jar-with-dependencies.jar

grade_salary_joining:
	~/Downloads/flink-1.16.0/bin/flink run target/grade-salary-joining-jar-with-dependencies.jar

state_machine:
	~/Downloads/flink-1.16.0/bin/flink run target/state-machine-kafka-events-jar-with-dependencies.jar --kafka-topic alerts \
	& ~/Downloads/flink-1.16.0/bin/flink run target/state-machine-jar-with-dependencies.jar --backend hashmap --error-rate 0.5 --kafka-topic alerts