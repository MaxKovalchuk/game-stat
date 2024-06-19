package com.andersen.gamestat.repository;

import com.andersen.gamestat.document.PlayerDocument;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayersRepository extends MongoRepository<PlayerDocument, String> {

    Optional<PlayerDocument> findByName(String name);

    @Aggregation(pipeline = {
            "{ $sort: { 'score': -1 } }",
            "{ $limit: ?0 }"
    })
    List<PlayerDocument> findTopPlayers(int limit);

}
