package com.procore.playground.statemachine;

import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.connector.kafka.sink.KafkaRecordSerializationSchema;
import org.apache.flink.connector.kafka.sink.KafkaSink;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.examples.statemachine.event.Event;
import org.apache.flink.streaming.examples.statemachine.generator.EventsGeneratorSource;
import org.apache.flink.streaming.examples.statemachine.kafka.EventDeSerializationSchema;

/**
 * Job to generate input events that are written to Kafka, for the {@link StateMachineExample} job.
 */
public class KafkaEventGenerator {

    public static void main(String[] args) throws Exception {

        final ParameterTool params = ParameterTool.fromArgs(args);

        double errorRate = params.getDouble("error-rate", 0.2);
        int sleep = params.getInt("sleep", 1);

        String kafkaTopic = params.get("kafka-topic");
        String brokers = params.get("brokers", "broker:9092");

        System.out.printf(
                "Generating events to Kafka with standalone source with error rate %f and sleep delay %s millis\n",
                errorRate, sleep);
        System.out.println();

        final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        env.addSource(new EventsGeneratorSource(errorRate, sleep))
                .sinkTo(
                        KafkaSink.<Event>builder()
                                .setBootstrapServers(brokers)
                                .setRecordSerializer(
                                        KafkaRecordSerializationSchema.builder()
                                                .setValueSerializationSchema(
                                                        new EventDeSerializationSchema())
                                                .setTopic(kafkaTopic)
                                                .build())
                                .build());

        // trigger program execution
        env.execute("State machine example Kafka events generator job");
    }
}