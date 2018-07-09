package com.ia.data.repository.datasource.cloud.model;

import com.google.gson.annotations.SerializedName;

public class ImageDTO {

    @SerializedName("file_path")
    String image;

    public String getImage () {
        return image;
    }
}
