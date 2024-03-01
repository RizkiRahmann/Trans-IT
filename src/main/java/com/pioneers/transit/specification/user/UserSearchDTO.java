package com.pioneers.transit.specification.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSearchDTO {
    private String userUsername;
    private String userName;
    private String userAddress;
    private String userPhoneNumber;
}
