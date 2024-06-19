package com.andersen.gamestat.service;

import com.andersen.gamestat.document.PlayerDocument;
import com.andersen.gamestat.dto.PlayerInfoDto;
import com.andersen.gamestat.dto.response.PlayerStatsResponseDto;
import com.andersen.gamestat.exception.GameStatException;
import com.andersen.gamestat.mapping.PlayerInfoMapper;
import com.andersen.gamestat.repository.PlayersRepository;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.andersen.gamestat.mapping.PlayerDocumentMapper.toPlayerStatsResponseDto;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private static final BigDecimal GAMES_AMOUNT_COEFFICIENT = BigDecimal.valueOf(0.2);
    private static final BigDecimal KDA_AMOUNT_COEFFICIENT = BigDecimal.valueOf(0.8);

    private final PlayersRepository repository;

    public void save(
            PlayerInfoDto playerInfoDto,
            String gameId
    ) {
        PlayerDocument playerDocument = repository.findByName(playerInfoDto.name())
                .map(player -> updatePlayerStats(playerInfoDto, gameId, player))
                .orElse(PlayerInfoMapper.toPlayerDocument(playerInfoDto, gameId));

        repository.save(playerDocument);
    }

    public PlayerStatsResponseDto playerByName(String name) {
        PlayerDocument player = repository.findByName(name)
                .orElseThrow(() -> new GameStatException("Player not found", HttpStatus.NOT_FOUND));

        return toPlayerStatsResponseDto(player);
    }

    public List<PlayerDocument> bestPlayers(int count) {
        return repository.findTopPlayers(count);
    }

    private PlayerDocument updatePlayerStats(
            PlayerInfoDto playerInfoDto,
            String gameId,
            PlayerDocument player
    ) {
        player.setTotalKills(
                player.getTotalKills() + playerInfoDto.kills()
        );
        player.setTotalDeaths(
                player.getTotalDeaths() + playerInfoDto.deaths()
        );
        player.setTotalAssists(
                player.getTotalAssists() + playerInfoDto.assists()
        );
        player.getGameIdHistory().add(gameId);
        player.setKda(playerKda(player));
        player.setScore(playerScore(player));

        return player;
    }

    private BigDecimal playerKda(PlayerDocument player) {
        int totalKills = player.getTotalKills();
        int totalDeaths = player.getTotalDeaths();
        int totalAssists = player.getTotalAssists();
        return BigDecimal.valueOf(totalKills)
                .add(BigDecimal.valueOf(totalAssists))
                .divide(BigDecimal.valueOf(totalDeaths), 2, RoundingMode.HALF_UP);
    }

    private BigDecimal playerScore(PlayerDocument player) {
        return player.getKda().multiply(KDA_AMOUNT_COEFFICIENT)
                .add(
                        BigDecimal.valueOf(player.getGameIdHistory().size())
                                .multiply(GAMES_AMOUNT_COEFFICIENT)
                );
    }

}
