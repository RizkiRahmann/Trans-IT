package com.pioneers.transit.service.impl;

import com.pioneers.transit.entity.Image;
import com.pioneers.transit.repository.ImageRepository;
import com.pioneers.transit.service.ImageService;
import com.pioneers.transit.utils.compress.ImageUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;
    @Override
    public Image uploadImage(MultipartFile file) throws IOException {
        Image image = Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .imageData(ImageUtil.compressImage(file.getBytes()))
                .build();
        return imageRepository.save(image);
    }

    @Override
    public byte[] getImage(String id) {
        Optional<Image> dbImage = imageRepository.findById(id);
        byte[] image = ImageUtil.decompressImage(dbImage.get().getImageData());
        return image;
    }
}
