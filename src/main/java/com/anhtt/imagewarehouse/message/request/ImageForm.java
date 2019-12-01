package com.anhtt.imagewarehouse.message.request;

import javax.validation.constraints.NotBlank;

/**
 * ImageForm
 */
public class ImageForm {
    @NotBlank
    private String name;
    @NotBlank
    private String data;

    public ImageForm() {
    }

    public ImageForm(String name, String data) {
        this.name = name;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}