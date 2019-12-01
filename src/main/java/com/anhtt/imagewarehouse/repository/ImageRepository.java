package com.anhtt.imagewarehouse.repository;

import java.util.List;
import java.util.UUID;

import com.anhtt.imagewarehouse.DTO.ImageDTO;
import com.anhtt.imagewarehouse.model.Image;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * ImageRepository
 */
public interface ImageRepository extends JpaRepository<Image, UUID> {
    @Query("select i.data from Image i where i.id = :id")
    String findDataById(@Param("id") UUID id);

    @Query("select a.id from Image a where a.name = :name")
    String findIdByName(@Param("name") String name);

    @Query("SELECT new com.anhtt.imagewarehouse.DTO.ImageDTO(a.id, a.name) FROM Image a")
    List<ImageDTO> getAll();
}