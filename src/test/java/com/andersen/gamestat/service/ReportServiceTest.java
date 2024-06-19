package com.andersen.gamestat.service;

import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.PlayersResponseDto;
import com.andersen.gamestat.dto.response.ServersResponseDto;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.andersen.gamestat.util.DtoBuilder.buildEndGameDocument;
import static com.andersen.gamestat.util.DtoBuilder.buildPlayerDocument;
import static com.andersen.gamestat.util.DtoBuilder.buildServerStatAggregate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ReportServiceTest {

    @Spy
    @InjectMocks
    ReportService spy;

    @Mock
    EndGameService endGameService;
    @Mock
    PlayerService playerService;

    @Test
    void recentMatches() {
        doReturn(List.of(buildEndGameDocument()))
                .when(endGameService).recentMatches(anyInt());

        GamesResponseDto actual = spy.recentMatches(1);

        verify(endGameService).recentMatches(anyInt());
        assertNotNull(actual);
        assertFalse(actual.getGames().isEmpty());
    }

    @Test
    void bestPlayers() {
        doReturn(List.of(buildPlayerDocument()))
                .when(playerService).bestPlayers(anyInt());

        PlayersResponseDto actual = spy.bestPlayers(1);

        verify(playerService).bestPlayers(anyInt());
        assertNotNull(actual);
        assertFalse(actual.getPlayers().isEmpty());
    }

    @Test
    void popularServers() {
        doReturn(List.of(buildServerStatAggregate()))
                .when(endGameService).serverStats(anyInt());

        ServersResponseDto actual = spy.popularServers(1);

        verify(endGameService).serverStats(anyInt());
        assertNotNull(actual);
        assertFalse(actual.getServers().isEmpty());
    }
}