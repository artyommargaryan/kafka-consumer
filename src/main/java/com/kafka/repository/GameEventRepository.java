package com.kafka.repository;


import com.kafka.payload.GameEvent;
import org.springframework.data.repository.CrudRepository;

public interface GameEventRepository extends CrudRepository<GameEvent, Long> {
}
