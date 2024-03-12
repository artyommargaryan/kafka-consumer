package com.kafka.handler;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.MessageListenerContainer;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class GlobalErrorHandler implements CommonErrorHandler {
    @Override
    public boolean handleOne(Exception thrownException,
                             ConsumerRecord<?, ?> record, Consumer<?, ?> consumer,
                             MessageListenerContainer container
    ) {
        log.error("Global error handler, error message: {}, record: {}, groupId: {}",
            thrownException.getCause().getMessage(), record.value(), container.getGroupId());
        return true;
    }

    @Override
    public void handleOtherException(Exception thrownException, Consumer<?, ?> consumer, MessageListenerContainer container, boolean batchListener) {
        log.error("Global error handler other exception, error message: {}, groupId: {}",
            thrownException.getCause().getMessage(), container.getGroupId());
    }
}
