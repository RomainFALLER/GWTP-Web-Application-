package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by wolfy on 09/06/16.
 */

@NamedNativeQueries({
        @NamedNativeQuery(name = "get_choices",
                query = "SELECT * FROM resource.v_appointment_preference WHERE eg_id = ? AND user_id = ?",
                resultClass = VAppointmentPreferenceData.class),

        @NamedNativeQuery(name="set_choices",
                query ="SELECT resource.update_member_appointment_preference((?::int),(?::int),(?::int),(?::int),(?::timestamptz),(?::timestamptz),(?::timestamptz));"),
})

@Entity
@Table(name = "v_appointment_preference", schema = "resource", catalog = "opus")
public class VAppointmentPreferenceData {
    private int userId;
    private int timespanId;
    private int egId;
    private int egInstanceNumber;
    private Date appointmentPref1StartDate;
    private Date appointmentPref2StartDate;
    private Date appointmentPref3StartDate;

    @Id
    @Column(name = "user_id", nullable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "timespan_id", nullable = true)
    public int getTimespanId() {
        return timespanId;
    }

    public void setTimespanId(int timespanId) {
        this.timespanId = timespanId;
    }

    @Id
    @Column(name = "eg_id", nullable = true)
    public int getEgId() {
        return egId;
    }

    public void setEgId(int egId) {
        this.egId = egId;
    }

    @Id
    @Column(name = "eg_instance_number", nullable = true)
    public int getEgInstanceNumber() {
        return egInstanceNumber;
    }

    public void setEgInstanceNumber(int egInstanceNumber) {
        this.egInstanceNumber = egInstanceNumber;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_pref1_start_date", nullable = true)
    public Date getAppointmentPref1StartDate() {
        return appointmentPref1StartDate;
    }

    public void setAppointmentPref1StartDate(@Nullable Date appointmentPref1StartDate) {
        this.appointmentPref1StartDate = appointmentPref1StartDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_pref2_start_date", nullable = true)
    public Date getAppointmentPref2StartDate() {
        return appointmentPref2StartDate;
    }

    public void setAppointmentPref2StartDate(@Nullable  Date appointmentPref2StartDate) {
        this.appointmentPref2StartDate = appointmentPref2StartDate;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_pref3_start_date", nullable = true)
    public Date getAppointmentPref3StartDate() {
        return appointmentPref3StartDate;
    }

    public void setAppointmentPref3StartDate(@Nullable Date appointmentPref3StartDate) {
        this.appointmentPref3StartDate = appointmentPref3StartDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VAppointmentPreferenceData that = (VAppointmentPreferenceData) o;

        if (userId != that.userId) return false;
        if (timespanId != that.timespanId) return false;
        if (egId != that.egId) return false;
        if (egInstanceNumber != that.egInstanceNumber) return false;
        if (appointmentPref1StartDate != null ? !appointmentPref1StartDate.equals(that.appointmentPref1StartDate) : that.appointmentPref1StartDate != null)
            return false;
        if (appointmentPref2StartDate != null ? !appointmentPref2StartDate.equals(that.appointmentPref2StartDate) : that.appointmentPref2StartDate != null)
            return false;
        if (appointmentPref3StartDate != null ? !appointmentPref3StartDate.equals(that.appointmentPref3StartDate) : that.appointmentPref3StartDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId;
        result = 31 * result + timespanId;
        result = 31 * result + egId;
        result = 31 * result + egInstanceNumber;
        result = 31 * result + (appointmentPref1StartDate != null ? appointmentPref1StartDate.hashCode() : 0);
        result = 31 * result + (appointmentPref2StartDate != null ? appointmentPref2StartDate.hashCode() : 0);
        result = 31 * result + (appointmentPref3StartDate != null ? appointmentPref3StartDate.hashCode() : 0);
        return result;
    }
}
