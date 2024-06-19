package com.andersen.gamestat.util;

import com.andersen.gamestat.document.EndGameDocument;
import com.andersen.gamestat.document.PlayerDocument;
import com.andersen.gamestat.dto.GameInfoDto;
import com.andersen.gamestat.dto.PlayerInfoDto;
import com.andersen.gamestat.dto.Server;
import com.andersen.gamestat.dto.aggregate.ServerStatAggregate;
import com.andersen.gamestat.dto.message.EndGameMessageDto;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DtoBuilder {

    public static EndGameMessageDto buildEndGameMessageDto() {
        return new EndGameMessageDto(
                buildGameInfoDto(),
                Server.LOCAL.toString()
        );
    }

    public static GameInfoDto buildGameInfoDto() {
        return new GameInfoDto(
                1000000l,
                true,
                false,
                List.of(buildPlayerInfoDto()),
                List.of(buildPlayerInfoDto())
        );
    }

    public static PlayerInfoDto buildPlayerInfoDto() {
        return new PlayerInfoDto(
                "name",
                1,
                1,
                1
        );
    }

    public static EndGameDocument buildEndGameDocument() {
        return EndGameDocument.builder()
                .id("id")
                .server(Server.LOCAL.toString())
                .gameInfo(buildGameInfoDto())
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }

    public static ServerStatAggregate buildServerStatAggregate() {
        return ServerStatAggregate.builder()
                .server(Server.LOCAL.toString())
                .count(10)
                .build();
    }

    public static PlayerDocument buildPlayerDocument() {
        return PlayerDocument.builder()
                .id("id")
                .name("name")
                .totalKills(1)
                .totalDeaths(1)
                .totalAssists(1)
                .kda(BigDecimal.ONE)
                .score(BigDecimal.ONE)
                .gameIdHistory(new ArrayList<>(List.of("id")))
                .createdDate(LocalDateTime.now())
                .lastModifiedDate(LocalDateTime.now())
                .build();
    }

}
