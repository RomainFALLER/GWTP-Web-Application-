package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity;

import javax.persistence.*;
import java.util.Date;




@Entity
@Table(name = "educational_goal", schema = "grading", catalog = "opus")
public class EducationalGoalData {
    private Integer logDataId;
    private Integer egId;
    private String label;
    private String shortDescription;
    private String description;
    private Integer administrativeValue;
    private Date validityStart;
    private Date validityEnd;
    private Date registration;

    @Basic
    @Column(name = "log_data_id", nullable = false)
    public Integer getLogDataId() {
        return logDataId;
    }

    public void setLogDataId(Integer logDataId) {
        this.logDataId = logDataId;
    }

    @Id
    @Column(name = "eg_id", nullable = false)
    public Integer getEgId() {
        return egId;
    }

    public void setEgId(Integer egId) {
        this.egId = egId;
    }

    @Basic
    @Column(name = "label", nullable = false, length = -1)
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    @Basic
    @Column(name = "short_description", nullable = true, length = -1)
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    @Basic
    @Column(name = "description", nullable = true, length = -1)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "administrative_value", nullable = true)
    public Integer getAdministrativeValue() {
        return administrativeValue;
    }

    public void setAdministrativeValue(Integer administrativeValue) {
        this.administrativeValue = administrativeValue;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "validity_start", nullable = false)
    public Date getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(Date validityStart) {
        this.validityStart = validityStart;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "validity_end", nullable = true)
    public Date getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "registration", nullable = true)
    public Date getRegistration() {
        return registration;
    }

    public void setRegistration(Date registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EducationalGoalData that = (EducationalGoalData) o;

        if (logDataId != null ? !logDataId.equals(that.logDataId) : that.logDataId != null) return false;
        if (egId != null ? !egId.equals(that.egId) : that.egId != null) return false;
        if (label != null ? !label.equals(that.label) : that.label != null) return false;
        if (shortDescription != null ? !shortDescription.equals(that.shortDescription) : that.shortDescription != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;
        if (administrativeValue != null ? !administrativeValue.equals(that.administrativeValue) : that.administrativeValue != null)
            return false;
        if (validityStart != null ? !validityStart.equals(that.validityStart) : that.validityStart != null)
            return false;
        if (validityEnd != null ? !validityEnd.equals(that.validityEnd) : that.validityEnd != null) return false;
        if (registration != null ? !registration.equals(that.registration) : that.registration != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = logDataId != null ? logDataId.hashCode() : 0;
        result = 31 * result + (egId != null ? egId.hashCode() : 0);
        result = 31 * result + (label != null ? label.hashCode() : 0);
        result = 31 * result + (shortDescription != null ? shortDescription.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (administrativeValue != null ? administrativeValue.hashCode() : 0);
        result = 31 * result + (validityStart != null ? validityStart.hashCode() : 0);
        result = 31 * result + (validityEnd != null ? validityEnd.hashCode() : 0);
        result = 31 * result + (registration != null ? registration.hashCode() : 0);
        return result;
    }
}
