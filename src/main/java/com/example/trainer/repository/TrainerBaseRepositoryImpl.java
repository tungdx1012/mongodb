package com.example.trainer.repository;

import com.example.trainer.entities.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class TrainerBaseRepositoryImpl implements TrainerBaseRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Trainer> findTrainerByHorse() {
        GroupOperation groupByAccountID = group("accountId");
        MatchOperation houreId = match(new Criteria("horseId").gt(3));
        Aggregation aggregation = newAggregation(groupByAccountID, houreId);
        AggregationResults<Trainer> results = mongoTemplate.aggregate(aggregation, "trainer", Trainer.class);

        return results.getMappedResults();
    }
}
