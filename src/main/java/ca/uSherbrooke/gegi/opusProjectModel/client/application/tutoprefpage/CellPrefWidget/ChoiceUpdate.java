package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.TimeBlock;
import com.google.gwt.event.shared.GwtEvent;

import java.util.ArrayList;

/**
 * Created by Olivier on 6/12/2016.
 */
public class ChoiceUpdate extends GwtEvent<ChoiceUpdateHandler> {
    public static Type<ChoiceUpdateHandler> TYPE = new Type<ChoiceUpdateHandler>();

    private ArrayList<TimeBlock.BlockChoice> newChoices = new ArrayList<TimeBlock.BlockChoice>();

    public Type<ChoiceUpdateHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(ChoiceUpdateHandler handler) {
        handler.onChoiceUpdate(this);
    }

    public ArrayList<TimeBlock.BlockChoice> getNewChoices() {
        return newChoices;
    }

    public void addNewChoice(TimeBlock.BlockChoice newChoice){
        newChoices.add(newChoice);
    }
}
