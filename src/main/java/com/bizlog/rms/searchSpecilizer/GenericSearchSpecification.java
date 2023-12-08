package com.bizlog.rms.searchSpecilizer;

import com.bizlog.rms.entities.BaseClientEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class GenericSearchSpecification<T extends BaseClientEntity> {

    public Specification<T> search(Long clientId, Map<String,String> fieldNamesWithKeyWords) {
        return (root, query, criteriaBuilder) -> {
            Join<Object, Object> orgJoin = root.join("client");
            List<Predicate> predicates = new ArrayList<>();
            fieldNamesWithKeyWords.entrySet().stream().forEach(entry ->{
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get(entry.getKey())), "%" + entry.getValue().toLowerCase() + "%"));
            });
            predicates.add(criteriaBuilder.equal(orgJoin.get("id"), clientId));
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}