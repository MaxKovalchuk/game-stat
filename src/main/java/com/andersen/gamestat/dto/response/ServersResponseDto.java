package com.andersen.gamestat.dto.response;

import com.andersen.gamestat.dto.ServerDto;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ServersResponseDto {

    private List<ServerDto> servers;

}
