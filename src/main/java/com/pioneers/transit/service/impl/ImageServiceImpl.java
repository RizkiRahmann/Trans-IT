package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.Image;
import com.pioneers.transit.repository.ImageRepository;
import com.pioneers.transit.service.ImageService;
import com.pioneers.transit.utils.compress.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
//                .imageData(file.getBytes())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();
        return imageRepository.save(image);
    }

    @Override
    public byte[] getImage(String id) {
        Image dbImage = imageRepository.findById(id)
                .orElseThrow(null);
        byte[] image = ImageUtil.decompressImage(dbImage.getImageData());
        return image;
//        return dbImage.map(image -> {
//            try {
//                return ImageUtil.decompressImage(image.getImageData());
//            } catch (Exception e){
//                throw new RuntimeException(e.getMessage());
//            }
//        }).orElse(null);
    }
}
