package com.andersen.gamestat.controller;

import com.andersen.gamestat.dto.Server;
import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.ServerStatResponseDto;
import com.andersen.gamestat.service.EndGameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {

    private final EndGameService endGameService;

    @GetMapping("/info")
    public ResponseEntity<GamesResponseDto> allGames() {
        GamesResponseDto response = endGameService.allGames();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{server}")
    public ResponseEntity<GamesResponseDto> gamesByServer(
            @PathVariable Server server
    ) {
        GamesResponseDto response = endGameService.gamesByServer(server);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{server}/matches")
    public ResponseEntity<GamesResponseDto> gamesByServerAndTimestamp(
            @PathVariable Server server,
            @RequestParam long timestamp
    ) {
        GamesResponseDto response = endGameService.gamesByServerAndTimestamp(server, timestamp);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{server}/stats")
    public ResponseEntity<ServerStatResponseDto> statsByServer(
            @PathVariable Server server
    ) {
        ServerStatResponseDto response = endGameService.statsByServer(server);
        return ResponseEntity.ok(response);
    }

}
