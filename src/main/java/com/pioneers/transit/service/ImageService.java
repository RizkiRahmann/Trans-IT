package com.pioneers.transit.service;

import com.pioneers.transit.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
    Image uploadImage(MultipartFile file) throws IOException;
    byte[] getImage(String id);
}
