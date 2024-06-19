package com.andersen.gamestat.mapping;

import com.andersen.gamestat.document.EndGameDocument;
import com.andersen.gamestat.dto.message.EndGameMessageDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EndGameMessageMapper {

    public static EndGameDocument toEndGameDocument(EndGameMessageDto endGameMessage) {
        return EndGameDocument.builder()
                .gameInfo(endGameMessage.gameInfo())
                .server(endGameMessage.server())
                .build();
    }

}
