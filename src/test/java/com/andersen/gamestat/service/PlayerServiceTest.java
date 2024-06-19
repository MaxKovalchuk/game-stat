package com.andersen.gamestat.service;

import com.andersen.gamestat.document.PlayerDocument;
import com.andersen.gamestat.dto.PlayerInfoDto;
import com.andersen.gamestat.dto.response.PlayerStatsResponseDto;
import com.andersen.gamestat.repository.PlayersRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.andersen.gamestat.util.DtoBuilder.buildPlayerDocument;
import static com.andersen.gamestat.util.DtoBuilder.buildPlayerInfoDto;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {

    @Spy
    @InjectMocks
    PlayerService spy;

    @Mock
    PlayersRepository repository;

    @Test
    void save() {
        PlayerInfoDto playerInfoDto = buildPlayerInfoDto();

        doReturn(Optional.of(buildPlayerDocument()))
                .when(repository).findByName(anyString());

        spy.save(playerInfoDto, "id");

        verify(repository).findByName(anyString());
        verify(repository).save(any());
    }

    @Test
    void playerByName() {
        doReturn(Optional.of(buildPlayerDocument()))
                .when(repository).findByName(anyString());

        PlayerStatsResponseDto actual = spy.playerByName("name");

        verify(repository).findByName(anyString());
        assertNotNull(actual);
    }

    @Test
    void bestPlayers() {
        doReturn(List.of(buildPlayerDocument()))
                .when(repository).findTopPlayers(anyInt());

        List<PlayerDocument> actual = spy.bestPlayers(1);

        verify(repository).findTopPlayers(anyInt());
        assertNotNull(actual);
        assertFalse(actual.isEmpty());
    }
}