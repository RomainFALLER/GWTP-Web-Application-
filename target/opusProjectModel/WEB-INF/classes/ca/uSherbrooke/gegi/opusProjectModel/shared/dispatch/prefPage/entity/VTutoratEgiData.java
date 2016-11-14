package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wolfy on 09/07/16.
 */
@NamedNativeQuery(name = "get_timesblock",
        query = "SELECT DISTINCT * FROM resource.v_tutorat_egi WHERE eg_id = ? AND eg_instance_number = ? AND timespan_id = ? ORDER BY start_date ",
        resultClass = VTutoratEgiData.class)


@Entity
@Table(name = "v_tutorat_egi", schema = "resource", catalog = "opus")
public class VTutoratEgiData {
    private int aptId;
    private String tutoratLabel;
    private String tutoratType;
    private String roomLabel;
    private Date startDate;
    private Date endDate;
    private int egId;
    private int egInstanceNumber;
    private int timespanId;

    @Id
    @Column(name = "apt_id", nullable = true)
    public int getAptId() {
        return aptId;
    }

    public void setAptId(int aptId) {
        this.aptId = aptId;
    }

    @Basic
    @Column(name = "tutorat_label", nullable = true, length = -1)
    public String getTutoratLabel() {
        return tutoratLabel;
    }

    public void setTutoratLabel(String tutoratLabel) {
        this.tutoratLabel = tutoratLabel;
    }

    @Basic
    @Column(name = "tutorat_type", nullable = true, length = -1)
    public String getTutoratType() {
        return tutoratType;
    }

    public void setTutoratType(String tutoratType) {
        this.tutoratType = tutoratType;
    }

    @Basic
    @Column(name = "room_label", nullable = true, length = -1)
    public String getRoomLabel() {
        return roomLabel;
    }

    public void setRoomLabel(String roomLabel) {
        this.roomLabel = roomLabel;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_date", nullable = true)
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_date", nullable = true)
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Basic
    @Column(name = "eg_id", nullable = true)
    public int getEgId() {
        return egId;
    }

    public void setEgId(int egId) {
        this.egId = egId;
    }

    @Basic
    @Column(name = "eg_instance_number", nullable = true)
    public int getEgInstanceNumber() {
        return egInstanceNumber;
    }

    public void setEgInstanceNumber(int egInstanceNumber) {
        this.egInstanceNumber = egInstanceNumber;
    }

    @Basic
    @Column(name = "timespan_id", nullable = true)
    public int getTimespanId() {
        return timespanId;
    }

    public void setTimespanId(int timespanId) {
        this.timespanId = timespanId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VTutoratEgiData that = (VTutoratEgiData) o;

        if (aptId != that.aptId) return false;
        if (egId != that.egId) return false;
        if (egInstanceNumber != that.egInstanceNumber) return false;
        if (timespanId != that.timespanId) return false;
        if (tutoratLabel != null ? !tutoratLabel.equals(that.tutoratLabel) : that.tutoratLabel != null) return false;
        if (tutoratType != null ? !tutoratType.equals(that.tutoratType) : that.tutoratType != null) return false;
        if (roomLabel != null ? !roomLabel.equals(that.roomLabel) : that.roomLabel != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = aptId;
        result = 31 * result + (tutoratLabel != null ? tutoratLabel.hashCode() : 0);
        result = 31 * result + (tutoratType != null ? tutoratType.hashCode() : 0);
        result = 31 * result + (roomLabel != null ? roomLabel.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + egId;
        result = 31 * result + egInstanceNumber;
        result = 31 * result + timespanId;
        return result;
    }
}
