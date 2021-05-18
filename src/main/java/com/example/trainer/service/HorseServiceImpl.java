package com.example.trainer.service;

import com.example.trainer.TrainerNotFoundException;
import com.example.trainer.dto.HorseRequest;
import com.example.trainer.dto.HorseResponse;
import com.example.trainer.entities.Horse;
import com.example.trainer.repository.HorseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

@Service


public class HorseServiceImpl implements HorseService {

    private final HorseRepository horseRepository;
    private final EntityManager em;

    public HorseServiceImpl(HorseRepository horseRepository, EntityManager em) {
        this.horseRepository = horseRepository;
        this.em = em;
    }

    //List All Horse
    @Override
    public List<Horse> getAllHorses() {
        return horseRepository.findAll();
    }


    //    Get Horse By Filter
    @Override
    public List<Horse> findByIDandFoaled(Integer trainerId, Integer foaled) {
        Map<String, Object> paramaterMap = new HashMap<String, Object>();
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("select h.id, h.name, h.foaled " +
                "from Trainer t inner join HorseAccount ha on t.account_id=ha.account_id " +
                "inner join Horse h on h.id = ha.horse_id where 1 = 1 ");
        if (trainerId != null) {
            queryBuilder.append("and t.id = :trainerId ");
            paramaterMap.put("trainerId", trainerId);
        }
        if (foaled != null) {
            queryBuilder.append("and YEAR(h.foaled) = :foaled ");
            paramaterMap.put("foaled", foaled);
        }
        Query jpaQuery = em.createQuery(queryBuilder.toString());
        for (String key : paramaterMap.keySet()) {
            jpaQuery.setParameter(key, paramaterMap.get(key));
        }
        return jpaQuery.getResultList();
    }
//    @Override
//    public List<Horse> findByIDandFoaled(Integer trainerId, Integer foaled) {
//        return horseRepository.findAll(new Specification<Horse>() {
//            @Override
//            public Predicate toPredicate(Root<Horse> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//                List<Predicate> predicates = new ArrayList<>();
//                if (trainerId != null) {
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("id"),  trainerId )));
//                }
//                if (foaled != null) {
//                    System.out.println(root.get("foaled"));
//                    predicates.add(criteriaBuilder.and(criteriaBuilder.equal(root.get("foaled"), foaled)));
//                }
//                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
//            }
//        });
//    }

    //Create New Horse
    @Override
    public HorseResponse createHorse(HorseRequest request) {
        Horse horse = new Horse();
        BeanUtils.copyProperties(request, horse);
        horseRepository.save(horse);
        return new HorseResponse(horse);
    }

    //Update Horse
    @Override
    public HorseResponse updateHorse(@RequestParam(value = "id") Integer Id,
                                     @RequestBody Horse horseDetails) {

        Horse t = horseRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Horse", "id", Id));
        t.setName(horseDetails.getName());
        t.setFoaled(horseDetails.getFoaled());
        Horse updatedHorse = horseRepository.save(t);
        return new HorseResponse(updatedHorse);
    }

    //Delete Horse By ID
    @Override
    public ResponseEntity<?> deleteHorse(@RequestParam(value = "id") Integer Id) {
        Horse t = horseRepository.findById(Id)
                .orElseThrow(() -> new TrainerNotFoundException("Horse", "id", Id));
        horseRepository.delete(t);
        return ResponseEntity.ok().build();
    }
}
