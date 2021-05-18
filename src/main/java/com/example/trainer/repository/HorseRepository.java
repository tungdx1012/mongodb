package com.example.trainer.repository;

import com.example.trainer.entities.Horse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface HorseRepository extends JpaRepository<Horse, Integer>, JpaSpecificationExecutor<Horse> {

//    @Query("select new Horse(h.id, h.name, h.foaled) \n" +
//            "from Trainer t join HorseAccount ha on t.account_id=ha.account_id \n" +
//            "join Horse h on h.id=ha.horse_id\n" +
//            "where (:trainerId is null or t.id = :trainerId) and (:foaled is null or YEAR(h.foaled) = :foaled)")
//    Collection<Horse> findByTrainerandYear(@Param("trainerId") Integer trainerId,
//                                           @Param("foaled") Integer foaled);

}

