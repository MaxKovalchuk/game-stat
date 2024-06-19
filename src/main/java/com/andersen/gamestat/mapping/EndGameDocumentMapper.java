package com.andersen.gamestat.mapping;

import com.andersen.gamestat.document.EndGameDocument;
import com.andersen.gamestat.dto.Server;
import com.andersen.gamestat.dto.ServerGameDto;
import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.ServerStatResponseDto;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EndGameDocumentMapper {

    public static GamesResponseDto toServerResponseDto(List<EndGameDocument> endGameDocuments) {
        GamesResponseDto gamesResponseDto = new GamesResponseDto();

        List<ServerGameDto> games = endGameDocuments.stream()
                .map(EndGameDocumentMapper::toServerGameDto)
                .collect(Collectors.toList());

        gamesResponseDto.setGames(games);

        return gamesResponseDto;
    }

    public static ServerStatResponseDto toServerStatResponseDto(
            List<EndGameDocument> endGameDocuments
    ) {
        ServerStatResponseDto serverStatResponseDto = new ServerStatResponseDto();

        long allGamesDuration = endGameDocuments.stream()
                .map(endGameDocument -> endGameDocument.getGameInfo().durationMs())
                .reduce(Long::sum)
                .orElseThrow(() -> new RuntimeException("Error while counting games avg duration"));
        int gamesCount = endGameDocuments.size();

        serverStatResponseDto.setAmountOfGames(gamesCount);
        serverStatResponseDto.setAvgDuration(allGamesDuration / gamesCount);
        return serverStatResponseDto;
    }

    public static ServerGameDto toServerGameDto(EndGameDocument endGameDocument) {
        ServerGameDto serverGameDto = new ServerGameDto();

        long epochSecond = endGameDocument.getCreatedDate()
                .atZone(ZoneOffset.systemDefault())
                .toInstant().toEpochMilli();
        serverGameDto.setEndGameTimestamp(epochSecond);
        serverGameDto.setGameInfo(endGameDocument.getGameInfo());
        serverGameDto.setServer(Server.valueOf(endGameDocument.getServer()));

        return serverGameDto;
    }

}
