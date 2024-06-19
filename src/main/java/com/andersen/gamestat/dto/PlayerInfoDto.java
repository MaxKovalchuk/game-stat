package com.andersen.gamestat.dto;


public record PlayerInfoDto(
        String name,
        int kills,
        int deaths,
        int assists
)
{}
