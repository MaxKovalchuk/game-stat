package com.andersen.gamestat.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServerDto {

    private Server serverName;

    private int amountOfGames;

}
