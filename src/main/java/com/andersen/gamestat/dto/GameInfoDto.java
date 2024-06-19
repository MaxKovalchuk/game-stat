package com.andersen.gamestat.dto;

import java.util.List;


public record GameInfoDto(
        long durationMs,
        boolean redTeamWon,
        boolean blueTeamWon,
        List<PlayerInfoDto> redTeam,
        List<PlayerInfoDto> blueTeam
)
{}
