package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.Teacher;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TutoGroup;
import com.google.gwt.event.shared.GwtEvent;

import java.util.ArrayList;

/**
 * Created by Olivier on 6/20/2016.
 */
public class TutoTabChangeEvent extends GwtEvent<TutoTabChangeEventHandler> {
    public static Type<TutoTabChangeEventHandler> TYPE = new Type<TutoTabChangeEventHandler>();

    public Type<TutoTabChangeEventHandler> getAssociatedType() {
        return TYPE;
    }

    protected void dispatch(TutoTabChangeEventHandler handler) {
        handler.onTutoTabChange(this);
    }

    private TutoGroup tuto;
    private boolean isUserATeacher;
    private boolean validGroups = true;
    private ArrayList<Teacher> teachers;

    public TutoGroup getTutoGroup() {
        return tuto;
    }

    public void setTutoGroup(TutoGroup tuto) {
        this.tuto = tuto;
    }

    public boolean isUserATeacher() {
        return isUserATeacher;
    }

    public void setUserATeacher(boolean userATeacher) {
        isUserATeacher = userATeacher;
    }

    public ArrayList<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(ArrayList<Teacher> teachers) {
        this.teachers = teachers;
    }

    public boolean areTheGroupsValid() {
        return validGroups;
    }

    public void setValidGroupsFlag(boolean areTheGroupsValid) {
        this.validGroups = areTheGroupsValid;
    }
}
