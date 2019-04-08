package lintong.service.repository;

import lintong.datamodel.clothes.ClothesInformation;
import lintong.datamodel.clothes.LocationInformation;
import lintong.datamodel.clothes.TemptureInformation;
import lintong.datamodel.command.TemptureCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataBaseCache {
    private ClothesInformation clothesInformationCache;
    private volatile boolean isInit = false;
    @Autowired
    private ClothesStateRepository clothesStateRepository;
    private synchronized void initClothesInformationCache(){
        if(clothesInformationCache != null){
            return;
        }
        Iterable<ClothesInformation> allClothesInfo = clothesStateRepository.findAll();
        if(allClothesInfo.iterator().hasNext()){
            System.out.println("hasNext");
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
        isInit = true;
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
        List<TemptureInformation> temps = cloInfo.getTemptureInformations();
        for(TemptureInformation t : temps){
            clothesInformationCache.addTemptureInfo(t);
        }
        clothesInformationCache.getLocationInformation().copy(cloInfo.getLocationInformation());
        clothesStateRepository.save(clothesInformationCache);
    }

    public synchronized void saveTemptureCommand(TemptureCommand tmpCmd){
        for(TemptureInformation information : clothesInformationCache.getTemptureInformations()){
            if(information.getRoad() == tmpCmd.getRoad()){
                information.setSetTempture(tmpCmd.getTempture());
                clothesStateRepository.save(clothesInformationCache);
                return;
            }
        }
    }
}
