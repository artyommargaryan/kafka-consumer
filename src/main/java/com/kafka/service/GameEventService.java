package com.kafka.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.exceptions.FieldsAreNotValidException;
import com.kafka.payload.GameEvent;
import com.kafka.repository.GameEventRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@Slf4j
@RequiredArgsConstructor
public class GameEventService {
    private final GameEventRepository gameEventRepository;
    private final ObjectMapper objectMapper;

    public void processEvent(String value) throws JsonProcessingException {
        GameEvent gameEvent = deserializeValue(value);
        processEvent(gameEvent);
    }

    private GameEvent deserializeValue(String value) throws JsonProcessingException {
        return objectMapper.readValue(value, GameEvent.class);
    }

    public void processEvent(GameEvent gameEvent) {
        validateFields(gameEvent);
        saveGameEvent(gameEvent);
    }

    private void validateFields(GameEvent gameEvent) {
        StringBuilder errorMessage = new StringBuilder("{");
        validateField(gameEvent.getTimestamp(), "Timestamp is null; ", errorMessage);
        validateStringField(gameEvent.getEventType(), "Event Type", errorMessage);
        validateField(gameEvent.getPlayerId(), "PlayerId is null; ", errorMessage);
        validateStringField(gameEvent.getData(), "Data", errorMessage);
        errorMessage.append("}");
        if (errorMessage.length() > 2) {
            throw new FieldsAreNotValidException(String.format("Event not saved because: %s", errorMessage));
        }
    }

    private void validateField(Object fieldValue, String errorMessagePart, StringBuilder errorMessage) {
        if (fieldValue == null) {
            errorMessage.append(errorMessagePart);
        }
    }

    private void validateStringField(String fieldValue, String fieldName, StringBuilder errorMessage) {
        if (!StringUtils.hasText(fieldValue)) {
            errorMessage.append(String.format("%s is null or blank; ", fieldName));
        }
    }

    private void saveGameEvent(GameEvent gameEvent) {
        GameEvent savedEvent = gameEventRepository.save(gameEvent);
        log.info("GameEvent is saved: {}", savedEvent);
    }
}
