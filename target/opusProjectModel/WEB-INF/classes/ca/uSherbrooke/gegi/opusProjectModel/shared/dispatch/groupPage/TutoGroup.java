package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Olivier on 6/19/2016.
 */
public class TutoGroup implements Serializable {
    ArrayList<Student> students = new ArrayList<Student>();
    ArrayList<TutoAppointment> tutoAppointments = new ArrayList<TutoAppointment>();
    String tutoId = "Tx"; // T1, T2, T3, etc...

    public TutoGroup() {
    }

    public ArrayList<Student> getStudents() {
        return students;
    }

    public ArrayList<TutoAppointment> getTutoAppointments() {
        return tutoAppointments;
    }

    public String getTutoId() {
        return tutoId;
    }

    public void setTutoId(String tutoId) {
        this.tutoId = tutoId;
    }
}
