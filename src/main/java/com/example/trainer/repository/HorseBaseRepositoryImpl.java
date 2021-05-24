package com.example.trainer.repository;

import com.example.trainer.entities.Horse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MappedDocument;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.GroupOperation;
import org.springframework.data.mongodb.core.aggregation.MatchOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

public class HorseBaseRepositoryImpl implements HorseBaseRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Horse> findByPrams(Date fromDate, Date toDate, Set<String> trainerIds) {
        Criteria criteria = new Criteria();

        if (fromDate != null && toDate != null) {
            criteria.and("foaled").gte(fromDate).lte(toDate);
        }


        if (!CollectionUtils.isEmpty(trainerIds)) {
            criteria.and("trainerId").in(trainerIds);
        }

        Aggregation aggregation = Aggregation.newAggregation(
                match(criteria)
        );


        AggregationResults<Horse> results = mongoTemplate.aggregate(aggregation, "horse", Horse.class);

        return results.getMappedResults();
    }
}
