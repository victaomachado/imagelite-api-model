package com.victor.imagelite_api_model.infra.repository;

import com.victor.imagelite_api_model.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, String> {
}
