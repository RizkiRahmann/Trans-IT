package com.pioneers.transit.dto.response;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelResponseClient<T> {
    private T result;
}
