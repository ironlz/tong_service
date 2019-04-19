package lintong.datamodel.clothes;

import java.io.Serializable;

public class DataFromClothes implements Serializable {
    private String lo;
    private String la;
    private String realTemp1;
    private String setTemp1;
    private String realTemp2;
    private String setTemp2;

    public DataFromClothes() {
        this("0.0","0.0","0.0","0.0","0.0","0.0");
    }

    public DataFromClothes(String lo, String la, String realTemp1, String setTemp1, String realTemp2, String setTemp2) {
        this.lo = lo;
        this.la = la;
        this.realTemp1 = realTemp1;
        this.setTemp1 = setTemp1;
        this.realTemp2 = realTemp2;
        this.setTemp2 = setTemp2;
    }

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getLa() {
        return la;
    }

    public void setLa(String la) {
        this.la = la;
    }

    public String getRealTemp1() {
        return realTemp1;
    }

    public void setRealTemp1(String realTemp1) {
        this.realTemp1 = realTemp1;
    }

    public String getSetTemp1() {
        return setTemp1;
    }

    public void setSetTemp1(String setTemp1) {
        this.setTemp1 = setTemp1;
    }

    public String getRealTemp2() {
        return realTemp2;
    }

    public void setRealTemp2(String realTemp2) {
        this.realTemp2 = realTemp2;
    }

    public String getSetTemp2() {
        return setTemp2;
    }

    public void setSetTemp2(String realTemp3) {
        this.setTemp2 = realTemp3;
    }
}
