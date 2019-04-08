package lintong.service.controller;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.service.repository.DataBaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/clothes/")
public class ClothesInterfaceController implements ClothesInterface {

    @Autowired
    DataBaseCache dataBase;

    @RequestMapping(value = "tempture_set",method = RequestMethod.GET)
    @ResponseBody
    @Override
    public String getTemptureCommand() {

        return null;
    }
    @RequestMapping(value = "clothes_info", method = RequestMethod.POST)
    @Override
    public void postClothesInfo(ClothesInformation clothesInfo) {

    }
}
