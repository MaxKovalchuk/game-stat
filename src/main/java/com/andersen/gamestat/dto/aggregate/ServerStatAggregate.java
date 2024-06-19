package com.andersen.gamestat.dto.aggregate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ServerStatAggregate {

    private String server;
    private int count;

}
