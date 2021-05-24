package com.example.trainer.repository;

import com.example.trainer.entities.Horse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface HorseRepository extends MongoRepository<Horse, String>, HorseBaseRepository {
    //    @Query("select new Horse(h.id, h.name, h.foaled) \n" +
//            "from Trainer t join HorseAccount ha on t.account_id=ha.account_id \n" +
//            "join Horse h on h.id=ha.horse_id\n" +
//            "where (:trainerId is null or t.id = :trainerId) and (:foaled is null or YEAR(h.foaled) = :foaled)")
//    Collection<Horse> findByTrainerandFoaled(@Param("trainerId") String trainerId,
//                                           @Param("foaled") Integer foaled);
//    List<Horse> findTrainerAndHorse(Set<String> listHorse);
}

