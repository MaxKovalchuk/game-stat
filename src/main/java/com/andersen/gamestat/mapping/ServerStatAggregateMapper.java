package com.andersen.gamestat.mapping;

import com.andersen.gamestat.dto.Server;
import com.andersen.gamestat.dto.ServerDto;
import com.andersen.gamestat.dto.aggregate.ServerStatAggregate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ServerStatAggregateMapper {

    public static ServerDto toServerDto(ServerStatAggregate serverStatAggregate) {
        ServerDto serverDto = new ServerDto();
        serverDto.setServerName(Server.valueOf(serverStatAggregate.getServer()));
        serverDto.setAmountOfGames(serverStatAggregate.getCount());
        return serverDto;
    }

}
