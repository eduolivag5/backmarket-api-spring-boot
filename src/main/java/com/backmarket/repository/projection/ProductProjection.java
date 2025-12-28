package com.backmarket.repository.projection;

import java.util.UUID;

public interface ProductProjection {

    UUID getId();
    Integer getCategory();
    Integer getBrand();
    String getNameShort();
    String getName();
    String[] getColors();
    Integer[] getStorages();
    String[] getImages();
    String[] getTags();

}
