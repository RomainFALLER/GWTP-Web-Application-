package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.AppTutos;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TutoGroup;
import com.google.gwt.event.shared.GwtEvent;

/**
 * Created by Olivier on 6/20/2016.
 */
public class TutoGroupAppTabChangeEvent extends GwtEvent<TutoGroupAppTabChangeEventHandler> {
    public static Type<TutoGroupAppTabChangeEventHandler> TYPE = new Type<TutoGroupAppTabChangeEventHandler>();

    public Type<TutoGroupAppTabChangeEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(TutoGroupAppTabChangeEventHandler handler) {
        handler.onTutoGroupAppTabChange(this);
    }

    private AppTutos app;

    public AppTutos getApp() {
        return app;
    }

    public void setApp(AppTutos app) {
        this.app = app;
    }
}
