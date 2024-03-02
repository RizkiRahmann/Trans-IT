package com.pioneers.transit.specification.user;

import com.pioneers.transit.entity.User;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class UserSpecification {
    public static Specification<User> getSpecification(UserSearchDTO userSearchDTO){
        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (userSearchDTO.getUserUsername()!=null){
                predicates.add(criteriaBuilder.like(root.get("username"),"%"+userSearchDTO.getUserUsername()+"%"));
            }
            if (userSearchDTO.getUserName()!=null){
                predicates.add(criteriaBuilder.like(root.get("name"),"%"+userSearchDTO.getUserName()+"%"));
            }
            if (userSearchDTO.getUserAddress()!=null){
                predicates.add(criteriaBuilder.like(root.get("address"),"%"+userSearchDTO.getUserAddress()+"%"));
            }
            if (userSearchDTO.getUserPhoneNumber()!=null){
                predicates.add(criteriaBuilder.like(root.get("phoneNumber"),"%"+userSearchDTO.getUserPhoneNumber()+"%"));
            }
            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
