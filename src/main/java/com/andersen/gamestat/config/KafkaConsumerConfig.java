package com.andersen.gamestat.config;

import com.andersen.gamestat.dto.message.EndGameMessageDto;
import java.util.HashMap;
import java.util.Map;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@Configuration
public class KafkaConsumerConfig {

    @Value("${kafka.bootstrap-server}")
    private String bootstrapServer;

    public <T> ConsumerFactory<String, T> consumerFactory(Class<T> messageClass) {
        Map<String, Object> config = new HashMap<>();
        config.put(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG,
                bootstrapServer
        );
        return new DefaultKafkaConsumerFactory<>(
                config, new StringDeserializer(), new JsonDeserializer<>(messageClass, false));
    }

    public <T>
    ConcurrentKafkaListenerContainerFactory<String, T> kafkaListenerContainerFactory(
            Class<T> messageClass
    ) {
        var factory = new ConcurrentKafkaListenerContainerFactory<String, T>();
        factory.setConsumerFactory(consumerFactory(messageClass));
        return factory;
    }

    @Bean("endGameKafkaListener")
    public ConcurrentKafkaListenerContainerFactory<String, EndGameMessageDto> endGameKafkaListener() {
        return kafkaListenerContainerFactory(EndGameMessageDto.class);
    }

}
