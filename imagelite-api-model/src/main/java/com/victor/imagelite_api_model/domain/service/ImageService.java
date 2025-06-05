package com.victor.imagelite_api_model.domain.service;

import com.victor.imagelite_api_model.domain.entity.Image;

import java.util.Optional;

public interface ImageService {
    Image save(Image image);

    Optional<Image> getById(String id);
}
