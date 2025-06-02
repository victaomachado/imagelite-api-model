package com.victor.imagelite_api_model.domain.entity;

import com.victor.imagelite_api_model.domain.enums.ImageExtension;
import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column
    private String name;
    @Column
    private Long size;
    @Column
    @Enumerated
    private ImageExtension extension;
    @Column
    @CreatedDate
    private LocalDateTime uploadDate;
    @Column
    private String tags;
    @Column
    @Lob
    private byte[] file;
}
