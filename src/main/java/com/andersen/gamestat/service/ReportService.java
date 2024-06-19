package com.andersen.gamestat.service;

import com.andersen.gamestat.dto.ServerDto;
import com.andersen.gamestat.dto.ServerGameDto;
import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.PlayerStatsResponseDto;
import com.andersen.gamestat.dto.response.PlayersResponseDto;
import com.andersen.gamestat.dto.response.ServersResponseDto;
import com.andersen.gamestat.mapping.EndGameDocumentMapper;
import com.andersen.gamestat.mapping.PlayerDocumentMapper;
import com.andersen.gamestat.mapping.ServerStatAggregateMapper;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService {

    private final EndGameService endGameService;
    private final PlayerService playerService;

    public GamesResponseDto recentMatches(int count) {
        List<ServerGameDto> recentMatches = endGameService.recentMatches(count).stream()
                .map(EndGameDocumentMapper::toServerGameDto)
                .collect(Collectors.toList());

        GamesResponseDto response = new GamesResponseDto();
        response.setGames(recentMatches);

        return response;
    }

    public PlayersResponseDto bestPlayers(int count) {
        List<PlayerStatsResponseDto> bestPlayers = playerService.bestPlayers(count).stream()
                .map(PlayerDocumentMapper::toPlayerStatsResponseDto)
                .toList();

        PlayersResponseDto response = new PlayersResponseDto();
        response.setPlayers(bestPlayers);
        return response;
    }

    public ServersResponseDto popularServers(int count) {
        List<ServerDto> popularServers = endGameService.serverStats(count).stream()
                .map(ServerStatAggregateMapper::toServerDto)
                .toList();

        ServersResponseDto response = new ServersResponseDto();
        response.setServers(popularServers);
        return response;
    }

}
