package com.victor.imagelite_api_model.application.images;

import com.victor.imagelite_api_model.domain.entity.Image;
import com.victor.imagelite_api_model.domain.enums.ImageExtension;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;


import java.io.IOException;

@Component
public class ImageMapper {

    public Image mapToImage(MultipartFile file, String name, List<String> tags) throws IOException {
        return Image.builder()
                .name(name)
                .tags(String.join(",", tags))
                .size(file.getSize())
                .extension(ImageExtension.valueOf(MediaType.valueOf(file.getContentType())))
                .file(file.getBytes())
                .build();
    }

    public ImageDTO imageDTO(Image image, String url) {
        return ImageDTO.builder()
                .url(url)
                .extension(image.getExtension().name())
                .name(image.getName())
                .size(image.getSize())
                .uploadDate(image.getUploadDate().toLocalDate())
                .build();
    }
}
