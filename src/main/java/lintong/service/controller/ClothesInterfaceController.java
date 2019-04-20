package lintong.service.controller;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.datamodel.clothes.DataFromClothes;
import lintong.datamodel.clothes.TemptureInformation;
import lintong.datamodel.command.TemptureCommand;
import lintong.service.repository.DataBaseCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/clothes")
public class ClothesInterfaceController implements ClothesInterface {

    @Autowired
    DataBaseCache dataBase;

    @RequestMapping(value = "clothes_info",method = RequestMethod.GET)
    @ResponseBody
    @Override
    public String getTemptureCommand() {
        System.out.println("/clothes/clothes_info   GET");
        Map<Integer, TemptureCommand> commands = dataBase.getCommandCache();
        return resolveTemptureCmd(commands);
    }

    private String resolveTemptureCmd(Map<Integer, TemptureCommand> commands) {
        StringBuilder builder = new StringBuilder("cmd: ");
        TemptureCommand cmd1 = commands.get(1);
        TemptureCommand cmd2 = commands.get(2);
        long date = cmd1.getCommandDate() > cmd2.getCommandDate() ? cmd1.getCommandDate() : cmd2.getCommandDate();
        builder.append(Long.toString(date)).append("{");
        builder.append(cmd2String(cmd1)).append(",").append(cmd2String(cmd2)).append("}");
        return builder.toString();
    }
    private String cmd2String(TemptureCommand tmp){
        StringBuilder builder = new StringBuilder();
        builder.append("{").append(tmp.getRoad()).append(",").append(tmp.getTempture()).append("}");
        return builder.toString();
    }

    @RequestMapping(value = "clothes_info", method = RequestMethod.POST)
    @Override
    public void postClothesInfo(@RequestBody ClothesInformation clothesInfo) {
        System.out.println("/clothes/clothes_info   POST");
        System.out.println("get from remote clothesInfo = " + clothesInfo);
        dataBase.saveClothesInformation(clothesInfo);
    }

    @RequestMapping(value = "clothes_info_post",method = RequestMethod.GET)
    @ResponseBody
    public ClothesInformation getClotheInfoData(){
        return dataBase.getClothesInformation();
    }
}
