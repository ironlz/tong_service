package lintong.service.controller;

import lintong.datamodel.clothes.ClothesInformation;

public interface ClothesInterface {
    String getTemptureCommand();
    void postClothesInfo(ClothesInformation clothesInfo);
}
