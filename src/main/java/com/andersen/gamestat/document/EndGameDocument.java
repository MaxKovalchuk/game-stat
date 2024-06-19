package com.andersen.gamestat.document;

import com.andersen.gamestat.dto.GameInfoDto;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder
@Document(collection = "end-game")
public class EndGameDocument {

    @Id
    private String id;

    private GameInfoDto gameInfo;

    private String server;

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

}
