package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Olivier on 5/28/2016.
 */
public class AppPrefs implements Serializable {
    AppID appId = new AppID();
    egiID egiId;

    ArrayList<TimeBlock> blocks = new ArrayList<TimeBlock>();

    public AppPrefs() {
    }

    public egiID getEgiId() {
        return egiId;
    }

    public void setEgiId(egiID egiId) {
        this.egiId = egiId;
    }

    public AppID getAppId() {
        return appId;
    }

    public void setAppId(AppID appId) {
        this.appId = appId;
    }

    public ArrayList<TimeBlock> getBlocks() {
        return blocks;
    }

    public void setBlocks(ArrayList<TimeBlock> blocks) {
        this.blocks = blocks;
    }
}
