package com.andersen.gamestat.document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "players")
public class PlayerDocument {

    @Id
    private String id;

    private String name;

    private int totalKills;

    private int totalDeaths;

    private int totalAssists;

    private BigDecimal kda;

    private BigDecimal score;

    private List<String> gameIdHistory;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
