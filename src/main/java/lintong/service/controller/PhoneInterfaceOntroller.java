package lintong.service.controller;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.datamodel.command.TemptureCommand;
import lintong.service.repository.DataBaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/phone")
public class PhoneInterfaceOntroller implements  PhoneInterface {
    @Autowired
    DataBaseCache dataBaseCache;

    @RequestMapping(value = "clothes_state", method = RequestMethod.GET)
    @ResponseBody
    @Override
    public ClothesInformation GetClothesInformation() {
        //just for test
        return dataBaseCache.getClothesInformation();
    }

    @RequestMapping(value = "tempture", method = RequestMethod.POST)
    @ResponseBody
    @Override
    public int PostTemptureCommand(@RequestBody TemptureCommand command) {
        System.out.println(command);
        dataBaseCache.saveTemptureCommand(command);
        return TemptureCommand.COMMAND_OK;
    }

}
