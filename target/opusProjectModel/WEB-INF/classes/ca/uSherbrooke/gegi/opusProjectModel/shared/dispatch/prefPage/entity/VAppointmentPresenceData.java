package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity;

import javax.persistence.*;

/**
 * Created by wolfy on 19/07/16.
 */

@NamedNativeQueries({
@NamedNativeQuery(name = "get_presences",
        query = "SELECT * FROM resource.v_appointment_presence WHERE user_id = ?",
        resultClass = VAppointmentPresenceData.class),

@NamedNativeQuery(name="set_presences",
        query ="SELECT resource.update_appointment_presence((?::int),(?::int),(?));"),
})
@NamedNativeQuery(name="set_resp",
            query = "SELECT public.update_user_group_responsability((?::int),(?::int),(?))")

@Entity
@Table(name = "v_appointment_presence", schema = "resource", catalog = "opus")
public class VAppointmentPresenceData {
    private int userId;
    private int appointmentId;
    private boolean present;

    @Id
    @Column(name = "user_id", nullable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "appointment_id", nullable = true)
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Basic
    @Column(name = "present", nullable = true)
    public boolean isPresent() {
        return present;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VAppointmentPresenceData that = (VAppointmentPresenceData) o;

        if (userId != that.userId) return false;
        if (appointmentId != that.appointmentId) return false;
        if (present != that.present) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + appointmentId;
        result = 31 * result + (present ? 1 : 0);
        return result;
    }
}
