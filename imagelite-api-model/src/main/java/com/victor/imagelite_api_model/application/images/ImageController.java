package com.victor.imagelite_api_model.application.images;

import com.victor.imagelite_api_model.domain.entity.Image;
import com.victor.imagelite_api_model.domain.enums.ImageExtension;
import com.victor.imagelite_api_model.domain.service.ImageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/images")
@Slf4j
@RequiredArgsConstructor
public class ImageController {

    private final ImageService service;
    private final ImageMapper mapper;

    @PostMapping
    public ResponseEntity save(
            @RequestParam("file") MultipartFile file,
            @RequestParam("name") String name,
            @RequestParam("tags") List<String> tags
            ) throws IOException {
        log.info("Imagem recebida: name: {}, size: {}", file.getOriginalFilename(), file.getSize());

        Image image = mapper.mapToImage(file, name, tags);
        Image savedImage = service.save(image);
        URI imageUri = buildImageURL(savedImage);

        return ResponseEntity.created(imageUri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String id) {
        var possibleImage = service.getById(id);
        if (possibleImage.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var image = possibleImage.get();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(image.getExtension().getMediaType());
        headers.setContentLength(image.getSize());
        headers.setContentDispositionFormData("inline; filename=\"" + image.getFileName() + "\"", image.getFileName());

        return new ResponseEntity<>(image.getFile(), headers, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ImageDTO>> search(
            @RequestParam(value = "extension", required = false) String extension,
            @RequestParam(value = "query", required = false) String query) {

        var result = service.search(ImageExtension.valueOf(extension), query);
        var images = result.stream().map(image -> {
            var url = buildImageURL(image);
            return mapper.imageDTO(image, url.toString());
        }).collect(Collectors.toList());

        return ResponseEntity.ok(images);
    }

    private URI buildImageURL(Image image) {
        String imagePath = "/" + image.getId();
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path(imagePath)
                .build().toUri();
    }
}
