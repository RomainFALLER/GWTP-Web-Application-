package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import java.io.Serializable;

/**
 * Created by Olivier on 6/19/2016.
 */
public class Teacher implements Serializable {
    String name = "teacher name";
    String emailAdd = "emailAddress@usherbrooke.ca";

    public Teacher() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAdd() {
        return emailAdd;
    }

    public void setEmailAdd(String emailAdd) {
        this.emailAdd = emailAdd;
    }
}
