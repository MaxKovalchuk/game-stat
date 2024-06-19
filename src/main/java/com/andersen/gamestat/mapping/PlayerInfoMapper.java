package com.andersen.gamestat.mapping;

import com.andersen.gamestat.document.PlayerDocument;
import com.andersen.gamestat.dto.PlayerInfoDto;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class PlayerInfoMapper {

    public static PlayerDocument toPlayerDocument(PlayerInfoDto playerInfoDto, String gameId) {
        return PlayerDocument.builder()
                .gameIdHistory(List.of(gameId))
                .name(playerInfoDto.name())
                .totalKills(playerInfoDto.kills())
                .totalDeaths(playerInfoDto.deaths())
                .totalAssists(playerInfoDto.assists())
                .build();
    }

}
