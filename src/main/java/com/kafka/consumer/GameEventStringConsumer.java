package com.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.kafka.service.GameEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameEventStringConsumer {
    private final GameEventService gameEventService;

    @KafkaListener(
        topics = "${com.kafka.topicName}",
        groupId = "${com.kafka.group-ids[1]}",
        containerFactory = "kafkaListenerStringContainerFactory"
    )
    public void consumeMessage(ConsumerRecord<String, String> consumerRecord) throws JsonProcessingException {
        log.info("ConsumerRecord form GameEventStringConsume: {} ", consumerRecord);
        gameEventService.processEvent(consumerRecord.value());
    }
}
