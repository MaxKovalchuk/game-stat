package com.andersen.gamestat.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServerStatResponseDto {

    private int amountOfGames;
    private long avgDuration;

}
