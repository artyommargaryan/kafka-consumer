package com.kafka.consumer;

import com.kafka.payload.GameEvent;
import com.kafka.service.GameEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameEventJsonConsumer {
    private final GameEventService gameEventService;

    @KafkaListener(
        topics = "${com.kafka.topicName}",
        groupId = "${com.kafka.group-ids[0]}",
        containerFactory = "kafkaListenerJsonContainerFactory",
        errorHandler = "gameEventHandler"
    )
    public void consumeMessage(ConsumerRecord<String, GameEvent> consumerRecord) {
        log.info("ConsumerRecord form GameEventConsume: {} ", consumerRecord);
        gameEventService.processEvent(consumerRecord.value());
    }
}

