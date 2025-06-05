package com.victor.imagelite_api_model.application.images;

import com.victor.imagelite_api_model.domain.entity.Image;
import com.victor.imagelite_api_model.domain.service.ImageService;
import com.victor.imagelite_api_model.infra.repository.ImageRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository repository;

    @Override
    @Transactional
    public Image save(Image image) {
        return repository.save(image);
    }

    @Override
    public Optional<Image> getById(String id) {
        return repository.findById(id);
    }

}
