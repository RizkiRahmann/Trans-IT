package com.pioneers.transit.specification.destination;

import com.pioneers.transit.entity.Destination;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class DestinationSpecification {
    public static Specification<Destination> getSpecification(DestinationSearchDTO destinationSearchDTO){
        return((root, query, criteriaBuilder)->{
            List<Predicate> predicates = new ArrayList<>();
            if(destinationSearchDTO.getDestinationName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"), "%"+destinationSearchDTO.getDestinationName()+"%"));
            }
            if(destinationSearchDTO.getDestinationDescription()!=null){
                predicates.add(criteriaBuilder.like(root.get("description"), "%"+destinationSearchDTO.getDestinationDescription()+"%"));
            }
            if (destinationSearchDTO.getDestinationLocation()!=null){
                predicates.add(criteriaBuilder.like(root.get("location"),"%"+destinationSearchDTO.getDestinationLocation()+"%"));
            }
            if (destinationSearchDTO.getPrice()!=null){
                predicates.add(criteriaBuilder.equal(root.get("price"),destinationSearchDTO.getPrice()));
            }
            if (destinationSearchDTO.getRating()!=null){
                predicates.add(criteriaBuilder.equal(root.get("rating"),destinationSearchDTO.getRating()));
            }

            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
