package com.andersen.gamestat.service.listener;

import com.andersen.gamestat.dto.message.EndGameMessageDto;
import com.andersen.gamestat.service.EndGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EndGameListener {

    private final EndGameService endGameService;

    public void processEndGameMessage(EndGameMessageDto message) {
        endGameService.save(message);
    }

}
