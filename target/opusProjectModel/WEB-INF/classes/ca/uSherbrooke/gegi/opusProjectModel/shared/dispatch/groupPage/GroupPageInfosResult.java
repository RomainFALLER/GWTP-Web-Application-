package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import com.gwtplatform.dispatch.rpc.shared.Result;

import java.util.ArrayList;

/**
 * Created by Olivier on 6/19/2016.
 */
public class GroupPageInfosResult implements Result {
    ArrayList<AppTutos> apps = new ArrayList<AppTutos>();

    public GroupPageInfosResult() {
    }

    public ArrayList<AppTutos> getApps() {
        return apps;
    }
}
