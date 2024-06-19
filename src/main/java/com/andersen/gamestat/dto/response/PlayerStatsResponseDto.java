package com.andersen.gamestat.dto.response;

import java.math.BigDecimal;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerStatsResponseDto {

    private int totalKills;

    private int totalDeaths;

    private int totalAssists;

    private BigDecimal kda;

    private BigDecimal score;

    private int gamesCount;

}
