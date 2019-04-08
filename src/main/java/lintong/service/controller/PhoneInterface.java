package lintong.service.controller;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.datamodel.command.TemptureCommand;

public interface PhoneInterface {
    ClothesInformation GetClothesInformation();
    int PostTemptureCommand(TemptureCommand command);
}
