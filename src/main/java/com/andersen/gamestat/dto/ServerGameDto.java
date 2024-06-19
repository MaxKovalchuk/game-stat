package com.andersen.gamestat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServerGameDto {

    private GameInfoDto gameInfo;
    private long endGameTimestamp;
    private Server server;

}
