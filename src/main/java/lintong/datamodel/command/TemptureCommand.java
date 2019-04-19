package lintong.datamodel.command;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
@Entity
public class TemptureCommand implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public static final int COMMAND_OK = 1;
    public static final int COMMAND_FAILURE = 0;
    private int road = -1; // 要设置的温度部位
    private int tempture = -1; // 要设置的温度
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TemptureCommand)) return false;
        TemptureCommand that = (TemptureCommand) o;
        return getRoad() == that.getRoad() &&
                getTempture() == that.getTempture() &&
                getCommandDate() == that.getCommandDate();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRoad(), getTempture(), getCommandDate());
    }

    public long getCommandDate() {
        return commandDate;
    }

    public void setCommandDate(long commandDate) {
        this.commandDate = commandDate;
    }

    public TemptureCommand(int road, int tempture, long commandDate) {
        this.road = road;
        this.tempture = tempture;
        this.commandDate = commandDate;
    }

    private long commandDate = 0L;// 命令创建日期
    /**
     * 默认构造器
     */
    public TemptureCommand() {
    }

    /**
     * 指定要设定的部位和温度
     * @param road 要设置的温度部位
     * @param tempture 要设置的温度
     */
    public TemptureCommand(int road, int tempture) {
        this.road = road;
        this.tempture = tempture;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public int getTempture() {
        return tempture;
    }

    public void setTempture(int tempture) {
        this.tempture = tempture;
    }

    @Override
    public String toString() {
        return "TemptureCommand{" +
                "road=" + road +
                ", tempture=" + tempture +
                ", date=" + '}';
    }

}
