package lintong.service.controller;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.datamodel.clothes.TemptureInformation;
import lintong.service.repository.DataBaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/clothes")
public class ClothesInterfaceController implements ClothesInterface {

    @Autowired
    DataBaseCache dataBase;

    @RequestMapping(value = "tempture_set",method = RequestMethod.GET)
    @ResponseBody
    @Override
    public String getTemptureCommand() {
        System.out.println("/clothes/tempture_set");
        ClothesInformation clothesInformation = dataBase.getClothesInformation();
        return resolveTemptureCmd(clothesInformation);
    }

    private String resolveTemptureCmd(ClothesInformation clothesInformation) {
        if(clothesInformation == null)
            return null;
        List<TemptureInformation> tpInfo = clothesInformation.getTemptureInformations();
        if(tpInfo == null || tpInfo.isEmpty()){
            return null;
        }
        StringBuilder builder = new StringBuilder();
        TemptureInformation t = null;
        int i = 0;
        for(i = 0; i < tpInfo.size() - 1; i++){
            t = tpInfo.get(i);
            builder.append(t.getRoad()).append(':').append(t.getSetTempture()).append('+');
        }
        t = tpInfo.get(i);
        builder.append(t.getRoad()).append(':').append(t.getSetTempture());
        return builder.toString();
    }

    @RequestMapping(value = "clothes_info", method = RequestMethod.POST)
    @Override
    public void postClothesInfo(@RequestBody ClothesInformation clothesInfo) {
        dataBase.saveClothesInformation(clothesInfo);
    }
}
