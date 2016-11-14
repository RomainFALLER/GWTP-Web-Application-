package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.Student;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.user.cellview.client.Column;

/**
 * Created by Olivier on 7/19/2016.
 */
public abstract class AttendanceColumn extends Column<Student, Boolean> {
    int appointmentId;

    public AttendanceColumn(Cell<Boolean> cell) {
        super(cell);
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }
}
