package com.andersen.gamestat.dto.response;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayersResponseDto {

    private List<PlayerStatsResponseDto> players;

}
