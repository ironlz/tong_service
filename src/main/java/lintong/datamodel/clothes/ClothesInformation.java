package lintong.datamodel.clothes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ClothesInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Transient
    private boolean clothesConnected = false;

    public boolean isClothesConnected() {
        return clothesConnected;
    }

    public void setClothesConnected(boolean clothesConnected) {
        this.clothesConnected = clothesConnected;
    }

    @ElementCollection
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private final List<TemptureInformation> temptureInformations = new ArrayList<>(2);
    @OneToOne(cascade = CascadeType.ALL)
    private LocationInformation locationInformation;
    public ClothesInformation() {
    }

    public ClothesInformation(List<TemptureInformation> temptureInformations, LocationInformation locationInformation) {
        this.temptureInformations.clear();
        this.temptureInformations.addAll(temptureInformations);
        this.locationInformation = locationInformation;
    }

    public List<TemptureInformation> getTemptureInformations() {
        return temptureInformations;
    }

    public void setTemptureInformations(ArrayList<TemptureInformation> temptureInformations) {
        this.temptureInformations.clear();
        this.temptureInformations.addAll(temptureInformations);
    }

    public LocationInformation getLocationInformation() {
        return locationInformation;
    }

    public void setLocationInformation(LocationInformation locationInformation) {
        this.locationInformation = locationInformation;
    }
    public void addTemptureInfo(TemptureInformation temptureInformation){
        for(TemptureInformation t : temptureInformations){
            if(t.getRoad() == temptureInformation.getRoad()){
                t.setTemptureInfo(temptureInformation);
                break;
            }
        }
    }
}
