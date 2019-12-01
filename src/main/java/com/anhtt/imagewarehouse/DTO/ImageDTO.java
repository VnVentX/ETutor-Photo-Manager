package com.anhtt.imagewarehouse.DTO;

import java.util.UUID;

/**
 * ImageDTO
 */
public class ImageDTO {
    private UUID id;
    private String name;

    public ImageDTO() {
    }

    public ImageDTO(UUID id, String name) {
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}