package com.pioneers.transit.specification.hotel;

import com.pioneers.transit.entity.Hotel;
import com.pioneers.transit.specification.destination.DestinationSearchDTO;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class HotelSpecification {
    public static Specification<Hotel> getSpecification(HotelSearchDTO hotelSearchDTO){
        return((root, query, criteriaBuilder) ->{
           List<Predicate> predicates = new ArrayList<>();
           if(hotelSearchDTO.getHotelID()!=null){
               predicates.add(criteriaBuilder.like(root.get("id"), "%"+hotelSearchDTO.getHotelID()+"%"));
           }
           if(hotelSearchDTO.getHotelname()!=null){
               predicates.add(criteriaBuilder.like(root.get("name"), "%"+hotelSearchDTO.getHotelname()+"%"));
           }
            if (hotelSearchDTO.getHotelHotelKey()!=null){
                predicates.add(criteriaBuilder.like(root.get("hotel_key"),"%"+hotelSearchDTO.getHotelHotelKey()+"%"));
            }

            Predicate[] predicates1 = predicates.toArray(new Predicate[predicates.size()]);
            return criteriaBuilder.and(predicates1);
        });
    }
}
