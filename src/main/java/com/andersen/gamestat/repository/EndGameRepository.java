package com.andersen.gamestat.repository;

import com.andersen.gamestat.document.EndGameDocument;
import com.andersen.gamestat.dto.aggregate.ServerStatAggregate;
import java.util.Date;
import java.util.List;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EndGameRepository extends MongoRepository<EndGameDocument, String> {

    List<EndGameDocument> findByServer(String server);

    @Query(value = "{ 'createdDate': { '$eq': ?0 }, 'server': ?1 }")
    List<EndGameDocument> findByServerAndCreatedDate(
            Date createdDate,
            String server
    );

    @Aggregation(pipeline = {
            "{ $sort: { 'createdDate': -1 } }",
            "{ $limit: ?0 }"
    })
    List<EndGameDocument> findMostRecentGames(int limit);

    @Aggregation(pipeline = {
            "{ $group: {'_id': '$server', 'count': { '$sum': 1 } } }",
            "{ $project: {_id: 0, 'server': '$_id', 'count': 1 } }",
            "{ $sort: { 'count': -1 } }",
            "{ $limit: ?0 }"
    })
    List<ServerStatAggregate> serversStats(int count);

}
