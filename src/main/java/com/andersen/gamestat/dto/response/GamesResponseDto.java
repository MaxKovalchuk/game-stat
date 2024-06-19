package com.andersen.gamestat.dto.response;

import com.andersen.gamestat.dto.ServerGameDto;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class GamesResponseDto {

    private List<ServerGameDto> games;

}
