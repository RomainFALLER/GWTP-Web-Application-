package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

import java.util.ArrayList;

/**
 * Created by Olivier on 7/17/2016.
 */
public class GroupPageSaveAction extends UnsecuredActionImpl<GroupPageSaveResult> {
    AppTutos app;

    public GroupPageSaveAction() {
    }

    public AppTutos getApp() {
        return app;
    }

    public void setApp(AppTutos app) {
        this.app = app;
    }
}
