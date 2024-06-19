package com.andersen.gamestat.controller;

import com.andersen.gamestat.dto.response.PlayerStatsResponseDto;
import com.andersen.gamestat.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayersController {

    private final PlayerService playerService;

    @GetMapping("/{name}/stats")
    public ResponseEntity<PlayerStatsResponseDto> playerByName(
            @PathVariable String name
    ) {
        PlayerStatsResponseDto response = playerService.playerByName(name);
        return ResponseEntity.ok(response);
    }

}
