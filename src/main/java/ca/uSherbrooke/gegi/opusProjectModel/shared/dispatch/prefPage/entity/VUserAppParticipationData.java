package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity;

import javax.persistence.*;

/**
 * Created by wolfy on 13/07/16.
 */

@NamedNativeQueries(value = {
        @NamedNativeQuery(name = "get_app_label_and_description_of_active_user",
                query = "SELECT * FROM grading.v_user_app_participation WHERE timespan_label = ? AND user_id = ? ORDER BY session_label, app_label",
                resultClass = VUserAppParticipationData.class),

        @NamedNativeQuery(name = "get_teacher_app",
                query = "SELECT * FROM grading.v_user_app_participation WHERE eg_id = ? AND eg_instance_number = ? AND timespan_id = ? AND status_label = 'Professeur' ORDER BY session_label, app_label",
                resultClass = VUserAppParticipationData.class),

        @NamedNativeQuery(name = "generate_groups",
            query = "SELECT resource.generate_tuto_group()"),


        @NamedNativeQuery(name = "get_true_or_false",
                query = "SELECT resource.get_generated_egi_group_state((?::int),(?::int),(?::int))"),
})

@Entity
@Table(name = "v_user_app_participation", schema = "grading", catalog = "opus")
public class VUserAppParticipationData {
    private String sessionLabel;
    private String appLabel;
    private String appDesc;
    private String timespanLabel;
    private int userId;
    private String lastName;
    private String fisrtName;
    private String emailAddress;
    private String statusLabel;
    private int egId;
    private int egInstanceNumber;
    private int timespanId;
    private int logDataId;

    @Basic
    @Column(name = "session_label", nullable = true, length = -1)
    public String getSessionLabel() {
        return sessionLabel;
    }

    public void setSessionLabel(String sessionLabel) {
        this.sessionLabel = sessionLabel;
    }

    @Basic
    @Column(name = "app_label", nullable = true, length = -1)
    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    @Basic
    @Column(name = "app_desc", nullable = true, length = -1)
    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    @Id
    @Column(name = "timespan_label", nullable = true, length = -1)
    public String getTimespanLabel() {
        return timespanLabel;
    }

    public void setTimespanLabel(String timespanLabel) {
        this.timespanLabel = timespanLabel;
    }

    @Id
    @Column(name = "user_id", nullable = true)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "last_name", nullable = true, length = -1)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "fisrt_name", nullable = true, length = -1)
    public String getFisrtName() {
        return fisrtName;
    }

    public void setFisrtName(String fisrtName) {
        this.fisrtName = fisrtName;
    }

    @Basic
    @Column(name = "email_address", nullable = true, length = -1)
    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    @Basic
    @Column(name = "status_label", nullable = true, length = -1)
    public String getStatusLabel() {
        return statusLabel;
    }

    public void setStatusLabel(String statusLabel) {
        this.statusLabel = statusLabel;
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

    @Basic
    @Column(name = "timespan_id", nullable = true)
    public int getTimespanId() {
        return timespanId;
    }

    public void setTimespanId(int timespanId) {
        this.timespanId = timespanId;
    }

    @Basic
    @Column(name = "log_data_id", nullable = true)
    public int getLogDataId() {
        return logDataId;
    }

    public void setLogDataId(int logDataId) {
        this.logDataId = logDataId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VUserAppParticipationData that = (VUserAppParticipationData) o;

        if (userId != that.userId) return false;
        if (egId != that.egId) return false;
        if (egInstanceNumber != that.egInstanceNumber) return false;
        if (timespanId != that.timespanId) return false;
        if (logDataId != that.logDataId) return false;
        if (sessionLabel != null ? !sessionLabel.equals(that.sessionLabel) : that.sessionLabel != null) return false;
        if (appLabel != null ? !appLabel.equals(that.appLabel) : that.appLabel != null) return false;
        if (appDesc != null ? !appDesc.equals(that.appDesc) : that.appDesc != null) return false;
        if (timespanLabel != null ? !timespanLabel.equals(that.timespanLabel) : that.timespanLabel != null)
            return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (fisrtName != null ? !fisrtName.equals(that.fisrtName) : that.fisrtName != null) return false;
        if (emailAddress != null ? !emailAddress.equals(that.emailAddress) : that.emailAddress != null) return false;
        if (statusLabel != null ? !statusLabel.equals(that.statusLabel) : that.statusLabel != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = sessionLabel != null ? sessionLabel.hashCode() : 0;
        result = 31 * result + (appLabel != null ? appLabel.hashCode() : 0);
        result = 31 * result + (appDesc != null ? appDesc.hashCode() : 0);
        result = 31 * result + (timespanLabel != null ? timespanLabel.hashCode() : 0);
        result = 31 * result + userId;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (fisrtName != null ? fisrtName.hashCode() : 0);
        result = 31 * result + (emailAddress != null ? emailAddress.hashCode() : 0);
        result = 31 * result + (statusLabel != null ? statusLabel.hashCode() : 0);
        result = 31 * result + egId;
        result = 31 * result + egInstanceNumber;
        result = 31 * result + timespanId;
        result = 31 * result + logDataId;
        return result;
    }
}
