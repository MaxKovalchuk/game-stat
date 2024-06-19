package com.andersen.gamestat.controller;

import com.andersen.gamestat.dto.response.GamesResponseDto;
import com.andersen.gamestat.dto.response.PlayersResponseDto;
import com.andersen.gamestat.dto.response.ServersResponseDto;
import com.andersen.gamestat.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final ReportService reportService;

    @GetMapping("/recent-matches/{count}")
    public ResponseEntity<GamesResponseDto> recentMatches(
            @PathVariable int count
    ) {
        GamesResponseDto response = reportService.recentMatches(count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/best-players/{count}")
    public ResponseEntity<PlayersResponseDto> bestPlayers(
            @PathVariable int count
    ) {
        PlayersResponseDto response = reportService.bestPlayers(count);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/popular-servers/{count}")
    public ResponseEntity<ServersResponseDto> popularServers(
            @PathVariable int count
    ) {
        ServersResponseDto response = reportService.popularServers(count);
        return ResponseEntity.ok(response);
    }

}
