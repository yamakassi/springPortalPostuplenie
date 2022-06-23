package com.example.portal.services;

import com.example.portal.domain.Image;
import com.example.portal.repositories.ImageRepo;
import com.example.portal.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {
    @Autowired
    private ImageRepo imageRepo;

    public static Image toImageEntity(MultipartFile file) throws IOException {
        Image image = new Image();

        image.setOriginalFileName(file.getOriginalFilename());
        image.setType(file.getContentType());
        image.setSize(file.getSize());
        image.setBytes(file.getBytes());
        return image;
    }

    public List<String> findImagesNamesByUsername(Long user_id) {
        return imageRepo.findNamesByUserId(user_id);
    }
}
