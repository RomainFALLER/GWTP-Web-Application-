package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage;

import com.gwtplatform.dispatch.rpc.shared.UnsecuredActionImpl;

import java.util.ArrayList;

/**
 * Created by Olivier on 5/28/2016.
 */
public class PrefSaveAction extends UnsecuredActionImpl<PrefSaveResult> {
    ArrayList<AppPrefs> appsPrefs = new ArrayList<AppPrefs>();

    public PrefSaveAction(){
    }

    public ArrayList<AppPrefs> getAppsPrefs() {
        return appsPrefs;
    }

    public void setAppsPrefs(ArrayList<AppPrefs> appsPrefs) {
        this.appsPrefs = appsPrefs;
    }
}
