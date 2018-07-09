package com.ia.data.repository.datasource.cloud.model;


import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageCollectionDTO {
    @SerializedName("backdrops")
    List<ImageDTO> movieImageList;

    public List<ImageDTO> getResults () {
        return movieImageList;
    }
}
