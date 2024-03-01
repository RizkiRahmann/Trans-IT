package com.pioneers.transit.dto.request;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelRequestClient<T> {
    private T result;
}
