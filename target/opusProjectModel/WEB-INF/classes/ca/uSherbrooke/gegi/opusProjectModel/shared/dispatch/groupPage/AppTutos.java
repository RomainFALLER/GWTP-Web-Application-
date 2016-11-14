package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Olivier on 6/20/2016.
 */
public class AppTutos implements Serializable {
    ArrayList<TutoGroup> tutos = new ArrayList<TutoGroup>();
    ArrayList<Teacher> teachers = new ArrayList<Teacher>();
    AppID appID = new AppID();
    boolean validGroups = true;


    public AppTutos() {
    }

    public AppID getAppID() {
        return appID;
    }

    public void setAppID(AppID appID) {
        this.appID = appID;
    }

    public ArrayList<TutoGroup> getTutos() {
        return tutos;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public boolean getValidGroups() {
        return validGroups;
    }

    public void setValidGroups(boolean validGroups) {
        this.validGroups = validGroups;
    }
}
