package lintong.service.repository;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.datamodel.clothes.LocationInformation;
import lintong.datamodel.clothes.TemptureInformation;
import lintong.datamodel.command.TemptureCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class DataBaseCache {
    private ClothesInformation clothesInformationCache;
    private Map<Integer, TemptureCommand> commandCache;
    private volatile boolean isInit = false;
    @Autowired
    private ClothesStateRepository clothesStateRepository;
    @Autowired
    private TemptureCommandRepository temptureCommandRepository;
    private synchronized void initClothesInformationCache(){
        if(clothesInformationCache != null && commandCache != null && !commandCache.isEmpty()){
            return;
        }
        Iterable<ClothesInformation> allClothesInfo = clothesStateRepository.findAll();
        if(allClothesInfo.iterator().hasNext()){
            System.out.println("there has clothesState in database.");
            clothesInformationCache = allClothesInfo.iterator().next();
        }else{
            System.out.println("not hasNext");
            ArrayList<TemptureInformation> temps = new ArrayList<>();
            temps.add(new TemptureInformation(1, 37, 37));
            temps.add(new TemptureInformation(2, 37, 37));
            LocationInformation loc = new LocationInformation(107.40, 33.42, 0.0);
            clothesInformationCache = new ClothesInformation(temps, loc);
            clothesStateRepository.save(clothesInformationCache);
        }
        Iterator<TemptureCommand> allCommands = temptureCommandRepository.findAll().iterator();
        commandCache = new HashMap<>(2);
        TemptureCommand cmd = null;
        if(allCommands.hasNext()){
            while(allCommands.hasNext()){
                cmd = allCommands.next();
                commandCache.put(cmd.getRoad(), cmd);
            }
        }else{
            cmd = new TemptureCommand(1, 36, 0L);
            commandCache.put(cmd.getRoad(), cmd);
            cmd = new TemptureCommand(2, 36, 0L);
            commandCache.put(cmd.getRoad(), cmd);
        }
        if(commandCache.size() != 2){
            System.out.println("command size is 2, check it...");
        }
        saveTempCmd();
        isInit = true;
    }

    private void saveTempCmd(){
        if(commandCache == null || commandCache.isEmpty()){
            return;
        }
        for(TemptureCommand t : commandCache.values()){
            temptureCommandRepository.save(t);
        }
    }

    public ClothesInformation getClothesInformation(){
        if(!isInit){
            initClothesInformationCache();
        }
//        else{
//            Optional<ClothesInformation> target = clothesStateRepository.findById(clothesInformationCache.getId());
//            if(!target.isPresent()){
//                clothesInformationCache = null;
//                System.out.println("two");
//                initClothesInformationCache();
//            }else{
//                clothesInformationCache = target.get();
//            }
//        }
        return clothesInformationCache;
    }

    public synchronized void saveClothesInformation(ClothesInformation cloInfo){
        if(!isInit){
            initClothesInformationCache();
        }
        List<TemptureInformation> temps = cloInfo.getTemptureInformations();
        for(TemptureInformation t : temps){
            clothesInformationCache.addTemptureInfo(t);
        }
        clothesInformationCache.getLocationInformation().copy(cloInfo.getLocationInformation());
        clothesStateRepository.save(clothesInformationCache);
    }

    public synchronized void saveTemptureCommand(TemptureCommand tmpCmd){
        if(!isInit){
            initClothesInformationCache();
        }
        if(commandCache == null || commandCache.isEmpty()){
            System.out.println("command cache is init, check it!!!");
            return;
        }
        for(TemptureInformation information : clothesInformationCache.getTemptureInformations()){
            if(information.getRoad() == tmpCmd.getRoad()){
                information.setSetTempture(tmpCmd.getTempture());
                clothesStateRepository.save(clothesInformationCache);
                break;
            }
        }
        TemptureCommand t = commandCache.get(tmpCmd.getRoad());
        t.setTempture(tmpCmd.getTempture());
        t.setCommandDate(tmpCmd.getCommandDate());
        temptureCommandRepository.save(t);
    }
    public Map<Integer, TemptureCommand> getCommandCache(){
        if(!isInit){
            initClothesInformationCache();
        }
        return Collections.unmodifiableMap(commandCache);
    }
}
