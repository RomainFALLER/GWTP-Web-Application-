package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget;

import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Olivier on 7/17/2016.
 */
public class PresenceOrRespoUpdatedEvent extends GwtEvent<PresenceOrRespoUpdatedHandler> {
    public static Type<PresenceOrRespoUpdatedHandler> TYPE = new Type<PresenceOrRespoUpdatedHandler>();

    public Type<PresenceOrRespoUpdatedHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(PresenceOrRespoUpdatedHandler handler) {
        handler.onPresenceOrRespoUpdated(this);
    }
}
