package com.andersen.gamestat.mapping;

import com.andersen.gamestat.document.PlayerDocument;
import com.andersen.gamestat.dto.response.PlayerStatsResponseDto;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlayerDocumentMapper {

    public static PlayerStatsResponseDto toPlayerStatsResponseDto(PlayerDocument playerDocument) {
        PlayerStatsResponseDto playerStatsResponseDto = new PlayerStatsResponseDto();
        playerStatsResponseDto.setTotalKills(playerDocument.getTotalKills());
        playerStatsResponseDto.setTotalDeaths(playerDocument.getTotalDeaths());
        playerStatsResponseDto.setTotalAssists(playerDocument.getTotalAssists());
        playerStatsResponseDto.setKda(playerDocument.getKda());
        playerStatsResponseDto.setScore(playerDocument.getScore());
        playerStatsResponseDto.setGamesCount(playerDocument.getGameIdHistory().size());

        return playerStatsResponseDto;
    }

}
