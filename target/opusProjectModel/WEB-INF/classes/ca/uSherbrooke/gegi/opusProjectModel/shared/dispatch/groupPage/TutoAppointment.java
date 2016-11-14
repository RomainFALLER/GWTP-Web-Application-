package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Olivier on 6/25/2016.
 */
public class TutoAppointment implements Serializable {

    String label;
    Date startTime;
    Date endTime;
    String roomId;
    int appointmentId;

    public TutoAppointment() {
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}
