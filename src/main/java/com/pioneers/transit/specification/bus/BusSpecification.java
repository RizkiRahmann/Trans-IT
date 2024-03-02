package com.pioneers.transit.specification.bus;

import com.pioneers.transit.entity.Bus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class BusSpecification {
    public static Specification<Bus> getSpecification(BusSearchDTO busSearchDTO){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (busSearchDTO.getBusName()!= null){
                predicates.add(criteriaBuilder.like(root.get("name"),"%"+busSearchDTO.getBusName()+"%"));
            }
            if (busSearchDTO.getBusChair()!=null){
                predicates.add(criteriaBuilder.equal(root.get("chair"),busSearchDTO.getBusChair()));
            }
            if (busSearchDTO.getBusPrice()!=null){
                predicates.add(criteriaBuilder.equal(root.get("price"),busSearchDTO.getBusPrice()));
            }
            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
