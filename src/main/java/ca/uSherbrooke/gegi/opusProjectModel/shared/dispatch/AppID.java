package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch;

import java.io.Serializable;

/**
 * Created by Olivier on 5/26/2016.
 */
public class AppID implements Serializable{
    String appLongName = "Description";
    String appShortName = "default app name";
    boolean isTeacher = false;

    public AppID() {}

    public String getAppLongName() {
        return appLongName;
    }

    public void setAppLongName(String appLongName) {
        this.appLongName = appLongName;
    }

    public String getAppShortName() {
        return appShortName;
    }

    public void setAppShortName(String appShortName) {
        this.appShortName = appShortName;
    }

    public boolean isTeacher() {
        return isTeacher;
    }

    public void setTeacher(boolean teacher) {
        isTeacher = teacher;
    }
}
