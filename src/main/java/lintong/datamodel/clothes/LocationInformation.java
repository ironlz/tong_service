package lintong.datamodel.clothes;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class LocationInformation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private double longitude;
    private double latitude;
    private double altitude;

    public LocationInformation() {
    }

    public LocationInformation(double longitude, double latitude, double altitude) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
    }

    @Override
    public String toString() {
        return "LocationInformation{" +
                "longitude=" + longitude +
                ", latitude=" + latitude +
                ", altitude=" + altitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LocationInformation)) return false;
        LocationInformation that = (LocationInformation) o;
        return Double.compare(that.getLongitude(), getLongitude()) == 0 &&
                Double.compare(that.getLatitude(), getLatitude()) == 0 &&
                Double.compare(that.getAltitude(), getAltitude()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLongitude(), getLatitude(), getAltitude());
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public void copy(LocationInformation locationInformation){
        this.setLatitude(locationInformation.getLatitude());
        this.setLongitude(locationInformation.getLongitude());
        this.setAltitude(locationInformation.getAltitude());
    }

}
