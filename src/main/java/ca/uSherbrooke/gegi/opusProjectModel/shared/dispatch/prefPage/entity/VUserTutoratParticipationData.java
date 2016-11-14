package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity;

import javax.persistence.*;

/**
 * Created by wolfy on 20/07/16.
 */

@NamedNativeQuery(name = "get_users_by_groups",
        query = "SELECT * FROM public.v_user_tutorat_participation WHERE appointment_id = ?",
        resultClass = VUserTutoratParticipationData.class)

@Entity
@Table(name = "v_user_tutorat_participation", schema = "public", catalog = "opus")
public class VUserTutoratParticipationData {
    private int appointmentId;
    private int groupId;
    private int userId;
    private String administrativeUserId;
     private String nom;
    private String prenom;
    private String responsability;

    @Basic
    @Column(name = "appointment_id", nullable = true)
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    @Basic
    @Column(name = "group_id", nullable = true)
    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
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
    @Column(name = "administrative_user_id", nullable = true, length = -1)
    public String getAdministrativeUserId() {
        return administrativeUserId;
    }

    public void setAdministrativeUserId(String administrativeUserId) {
        this.administrativeUserId = administrativeUserId;
    }

    @Basic
    @Column(name = "prenom", nullable = true, length = -1)
    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    @Basic
    @Column(name = "nom", nullable = true, length = -1)
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Basic
    @Column(name = "responsability", nullable = true, length = -1)
    public String getResponsability() {
        return responsability;
    }

    public void setResponsability(String responsability) {
        this.responsability = responsability;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VUserTutoratParticipationData that = (VUserTutoratParticipationData) o;

        if (appointmentId != that.appointmentId) return false;
        if (groupId != that.groupId) return false;
        if (userId != that.userId) return false;
        if (administrativeUserId != null ? !administrativeUserId.equals(that.administrativeUserId) : that.administrativeUserId != null)
            return false;
        if (prenom != null ? !prenom.equals(that.prenom) : that.prenom != null) return false;
        if (nom != null ? !nom.equals(that.nom) : that.nom != null) return false;
        if (responsability != null ? !responsability.equals(that.responsability) : that.responsability != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = appointmentId;
        result = 31 * result + groupId;
        result = 31 * result + userId;
        result = 31 * result + (administrativeUserId != null ? administrativeUserId.hashCode() : 0);
        result = 31 * result + (prenom != null ? prenom.hashCode() : 0);
        result = 31 * result + (nom != null ? nom.hashCode() : 0);
        result = 31 * result + (responsability != null ? responsability.hashCode() : 0);
        return result;
    }
}
