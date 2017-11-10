package org.apache.cayenne.testdo.testmap.auto;

import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.testdo.testmap.Ball;
import org.apache.cayenne.testdo.testmap.Box;

/**
 * Class _Thing was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Thing extends CayenneDataObject {

    public static final String VOLUME_PROPERTY = "volume";
    public static final String WEIGHT_PROPERTY = "weight";
    public static final String BALL_PROPERTY = "ball";
    public static final String BOX_PROPERTY = "box";

    public static final String ID_PK_COLUMN = "ID";

    public void setVolume(Integer volume) {
        writeProperty("volume", volume);
    }
    public Integer getVolume() {
        return (Integer)readProperty("volume");
    }

    public void setWeight(Integer weight) {
        writeProperty("weight", weight);
    }
    public Integer getWeight() {
        return (Integer)readProperty("weight");
    }

    public void setBall(Ball ball) {
        setToOneTarget("ball", ball, true);
    }

    public Ball getBall() {
        return (Ball)readProperty("ball");
    }


    @SuppressWarnings("unchecked")
    public List<Box> getBox() {
        return (List<Box>)readProperty("box");
    }


}