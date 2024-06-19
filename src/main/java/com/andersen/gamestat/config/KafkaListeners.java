package com.andersen.gamestat.config;

import com.andersen.gamestat.dto.message.EndGameMessageDto;
import com.andersen.gamestat.service.listener.EndGameListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class KafkaListeners {

    private final EndGameListener endGameListener;

    @KafkaListener(topics = "${kafka.consumer.end-game-topic}",
            groupId = "${kafka.consumer.group-id}",
            containerFactory = "endGameKafkaListener"
    )
    public void listenEndGameMessage(EndGameMessageDto message) {
        log.info("End game message received...");

        endGameListener.processEndGameMessage(message);
    }

}
