package com.andersen.gamestat.dto.message;

import com.andersen.gamestat.dto.GameInfoDto;

public record EndGameMessageDto(
        GameInfoDto gameInfo,
        String server
){}
