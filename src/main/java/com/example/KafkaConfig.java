package com.example;


import org.apache.kafka.clients.CommonClientConfigs;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KafkaConfig {

    private static final String TOPIC_NAME = "weblogs";
    private static final String KAFKA_URL = "kafka://localhost:9092";

    public static String getTopic() {
        return TOPIC_NAME;
    }

    public static String getMessageKey() {
        return "weblogs.key";
    }


    public static Map<String, Object> producerDefaults() {
        Map<String, Object> properties = defaultKafkaProps();
        properties.put(ProducerConfig.ACKS_CONFIG, "all");
        properties.put(ProducerConfig.RETRIES_CONFIG, 0);
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 16384);
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 1);
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 33554432);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    private static Map<String, Object> defaultKafkaProps() {
        Map<String, Object> properties = new HashMap<>();
        List<String> hostPorts = new ArrayList<>();
        try {
            URI uri = new URI(KAFKA_URL);
            hostPorts.add(String.format("%s:%d", uri.getHost(), uri.getPort()));
            properties.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "PLAINTEXT");
            properties.put(CommonClientConfigs.BOOTSTRAP_SERVERS_CONFIG, String.join(",", hostPorts));
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        return properties;
    }

}
