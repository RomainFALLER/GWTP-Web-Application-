package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage;

import com.gwtplatform.dispatch.rpc.shared.Result;

import java.util.ArrayList;

/**
 * Created by Olivier on 6/1/2016.
 */
public class PrefPageInfosResult implements Result {
    ArrayList<AppPrefs> apps = new ArrayList<AppPrefs>();

    public PrefPageInfosResult() {
    }

    public PrefPageInfosResult(ArrayList<AppPrefs> prefs)
    {
        this.apps = prefs;
    }

    public ArrayList<AppPrefs> getApps() {
        return apps;
    }
}
