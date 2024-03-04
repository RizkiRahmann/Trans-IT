package com.pioneers.transit.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pioneers.transit.entity.UserCredential;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseImage {
    private String id;
    private String imageId;
    private String imageName;
    private String imageType;
}
