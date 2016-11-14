package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.TimeBlock;
import com.google.gwt.event.shared.GwtEvent;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by carrierca on 2016-06-01.
 */
public class TutoPrefTabChange extends GwtEvent<TutoPrefTabChangeHandler> {
    public static Type<TutoPrefTabChangeHandler> TYPE = new Type<TutoPrefTabChangeHandler>();

    public Type<TutoPrefTabChangeHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(TutoPrefTabChangeHandler handler) {
        handler.onTutoPrefTabChange(this);
    }

    public List<TimeBlock> getTimeBlock(){ return TimeBlocks; }
    public void setTimeBlocks(List<TimeBlock> timeBlocks){ TimeBlocks = timeBlocks; }

    public Date getTimeLockPref() { return TimeLockPref; }
    public void setTimeLockPref(Date value) { TimeLockPref = value; }

    List<TimeBlock> TimeBlocks = new ArrayList<TimeBlock>();
    Date TimeLockPref;
}
