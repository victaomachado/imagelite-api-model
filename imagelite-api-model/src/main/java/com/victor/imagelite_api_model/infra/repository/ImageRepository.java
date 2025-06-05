package com.victor.imagelite_api_model.infra.repository;

import com.victor.imagelite_api_model.domain.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ImageRepository extends JpaRepository<Image, String>, JpaSpecificationExecutor<Image> {
}
