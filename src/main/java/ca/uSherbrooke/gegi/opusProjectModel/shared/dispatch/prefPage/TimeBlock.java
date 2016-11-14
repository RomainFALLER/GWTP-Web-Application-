package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Olivier on 5/26/2016.
 */
public class TimeBlock implements Serializable {
    public enum BlockChoice
    {
        FIRST_CHOICE,
        SECOND_CHOICE,
        THIRD_CHOICE,
        NO_CHOICE
    }

    Date startTime = new Date(0);
    Date endTime = new Date(0);
    BlockChoice choice = BlockChoice.NO_CHOICE;

    public TimeBlock() {}

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public BlockChoice getChoice() {
        return choice;
    }

    public void setChoice(BlockChoice choice) {
        this.choice = choice;
    }
}
