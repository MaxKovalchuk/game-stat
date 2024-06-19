package com.andersen.gamestat.service;

import com.andersen.gamestat.document.EndGameDocument;
import com.andersen.gamestat.dto.Server;
import com.andersen.gamestat.dto.aggregate.ServerStatAggregate;
import com.andersen.gamestat.dto.message.EndGameMessageDto;
import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.ServerStatResponseDto;
import com.andersen.gamestat.repository.EndGameRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.andersen.gamestat.util.DtoBuilder.buildEndGameDocument;
import static com.andersen.gamestat.util.DtoBuilder.buildEndGameMessageDto;
import static com.andersen.gamestat.util.DtoBuilder.buildServerStatAggregate;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class EndGameServiceTest {

    @Spy
    @InjectMocks
    EndGameService spy;

    @Mock
    EndGameRepository repository;
    @Mock
    PlayerService playerService;

    @Test
    void save() {
        EndGameMessageDto endGameMessageDto = buildEndGameMessageDto();

        doReturn(buildEndGameDocument())
                .when(repository).save(any());

        spy.save(endGameMessageDto);

        verify(repository).save(any());
        verify(playerService, times(2)).save(any(), any());
    }

    @Test
    void allGames() {
        doReturn(List.of(buildEndGameDocument()))
                .when(repository).findAll();

        GamesResponseDto actual = spy.allGames();

        verify(repository).findAll();
        assertNotNull(actual);
    }

    @Test
    void gamesByServer() {
        doReturn(List.of(buildEndGameDocument()))
                .when(repository).findByServer(anyString());

        GamesResponseDto actual = spy.gamesByServer(Server.LOCAL);

        verify(repository).findByServer(anyString());
        assertNotNull(actual);
    }

    @Test
    void statsByServer() {
        doReturn(List.of(buildEndGameDocument()))
                .when(repository).findByServer(anyString());

        ServerStatResponseDto actual = spy.statsByServer(Server.LOCAL);

        verify(repository).findByServer(anyString());
        assertNotNull(actual);
    }

    @Test
    void gamesByServerAndTimestamp() {
        doReturn(List.of(buildEndGameDocument()))
                .when(repository).findByServerAndCreatedDate(any(), anyString());

        GamesResponseDto actual = spy.gamesByServerAndTimestamp(Server.LOCAL, 100000l);

        verify(repository).findByServerAndCreatedDate(any(), anyString());
        assertNotNull(actual);
    }

    @Test
    void recentMatches() {
        doReturn(List.of(buildEndGameDocument()))
                .when(repository).findMostRecentGames(anyInt());

        List<EndGameDocument> actual = spy.recentMatches(1);

        verify(repository).findMostRecentGames(anyInt());
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }

    @Test
    void serverStats() {
        doReturn(List.of(buildServerStatAggregate()))
                .when(repository).serversStats(anyInt());

        List<ServerStatAggregate> actual = spy.serverStats(1);

        verify(repository).serversStats(anyInt());
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }
}