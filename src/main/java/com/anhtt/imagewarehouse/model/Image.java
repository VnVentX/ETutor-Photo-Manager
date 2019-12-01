package com.anhtt.imagewarehouse.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "IMAGE")
public class Image {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", columnDefinition = "UNIQUEIDENTIFIER")
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "data" , columnDefinition = "NVARCHAR(MAX)")
    private String data;

    public Image() {
    }

    public Image(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}