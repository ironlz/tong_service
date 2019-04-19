package lintong.datamodel.clothes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class TemptureInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int road = -1;
    private int setTempture = -1;
    private double currentTempture = -1;

    public TemptureInformation() {
    }
    public TemptureInformation(int road, int setTempture, int currentTempture) {
        this.road = road;
        this.setTempture = setTempture;
        this.currentTempture = currentTempture;
    }

    @Override
    public String toString() {
        return "TemptureInformation{" +
                "road=" + road +
                ", setTempture=" + setTempture +
                ", currentTempture=" + currentTempture +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemptureInformation)) return false;
        TemptureInformation that = (TemptureInformation) o;
        return getRoad() == that.getRoad() &&
                getSetTempture() == that.getSetTempture() &&
                getCurrentTempture() == that.getCurrentTempture();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoad(), getSetTempture(), getCurrentTempture());
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public int getSetTempture() {
        return setTempture;
    }

    public void setSetTempture(int setTempture) {
        this.setTempture = setTempture;
    }

    public double getCurrentTempture() {
        return currentTempture;
    }

    public void setCurrentTempture(double currentTempture) {
        this.currentTempture = currentTempture;
    }
    public void setTemptureInfo(TemptureInformation t){
        setRoad(t.getRoad());
        setSetTempture(t.getSetTempture());
        setCurrentTempture(t.getCurrentTempture());
    }

}
