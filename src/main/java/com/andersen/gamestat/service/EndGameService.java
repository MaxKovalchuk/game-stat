package com.andersen.gamestat.service;

import com.andersen.gamestat.document.EndGameDocument;
import com.andersen.gamestat.dto.Server;
import com.andersen.gamestat.dto.aggregate.ServerStatAggregate;
import com.andersen.gamestat.dto.message.EndGameMessageDto;
import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.ServerStatResponseDto;
import com.andersen.gamestat.repository.EndGameRepository;
import java.util.Date;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.andersen.gamestat.mapping.EndGameDocumentMapper.toServerResponseDto;
import static com.andersen.gamestat.mapping.EndGameDocumentMapper.toServerStatResponseDto;
import static com.andersen.gamestat.mapping.EndGameMessageMapper.toEndGameDocument;

@Service
@RequiredArgsConstructor
public class EndGameService {

    private final EndGameRepository repository;
    private final PlayerService playerService;

    public void save(EndGameMessageDto endGameMessage) {
        EndGameDocument endGameDocument = toEndGameDocument(endGameMessage);
        endGameDocument = repository.save(endGameDocument);

        String gameId = endGameDocument.getId();
        endGameDocument.getGameInfo()
                .blueTeam()
                .forEach(player -> playerService.save(player, gameId));
        endGameDocument.getGameInfo()
                .redTeam()
                .forEach(player -> playerService.save(player, gameId));
    }

    public GamesResponseDto allGames() {
        List<EndGameDocument> games = repository.findAll();
        return toServerResponseDto(games);
    }

    public GamesResponseDto gamesByServer(Server serverName) {
        List<EndGameDocument> games = repository.findByServer(serverName.toString());
        return toServerResponseDto(games);
    }

    public ServerStatResponseDto statsByServer(Server serverName) {
        List<EndGameDocument> games = repository.findByServer(serverName.toString());
        return toServerStatResponseDto(games);
    }

    public GamesResponseDto gamesByServerAndTimestamp(
            Server serverName,
            long timestamp
    ) {
        List<EndGameDocument> games =
                repository.findByServerAndCreatedDate(new Date(timestamp), serverName.toString());
        return toServerResponseDto(games);
    }

    public List<EndGameDocument> recentMatches(int count) {
        return repository.findMostRecentGames(count);
    }

    public List<ServerStatAggregate> serverStats(int count) {
        return repository.serversStats(count);
    }

}
