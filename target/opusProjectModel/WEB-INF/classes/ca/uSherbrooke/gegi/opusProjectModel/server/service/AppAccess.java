package ca.uSherbrooke.gegi.opusProjectModel.server.service;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.VAppointmentPreferenceData;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.VTutoratEgiData;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.VUserAppParticipationData;
import com.google.inject.Inject;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by wolfy on 03/06/16.
 */
public class AppAccess {

    EntityManagerFactory emf;

    @Inject
    public AppAccess() {

    }

    public List<VUserAppParticipationData> getAppsForActiveUser(String periode, Integer actualUserID) {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_app_label_and_description_of_active_user");
            query.setParameter(1,periode);
            query.setParameter(2,actualUserID);
            et.commit();
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VTutoratEgiData> getTimesBlockForActiveUser(Integer egi_id, Integer egi_instance_id, Integer timespan_id)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_timesblock");
            query.setParameter(1,egi_id);
            query.setParameter(2,egi_instance_id);
            query.setParameter(3,timespan_id);
            et.commit();
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VAppointmentPreferenceData> getChoices(Integer egi_id, Integer actualUserID)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_choices");
            query.setParameter(1,egi_id);
            query.setParameter(2,actualUserID);
            et.commit();
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public void setChoice(Integer actualUserID, Integer current_timespan_id, Integer current_eg_id, Integer current_eg_instance,@Nullable  Date pref1_start,@Nullable Date pref2_start,@Nullable Date pref3_start)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("set_choices");
            //Query query = em.createNativeQuery("UPDATE resource.appointment_preference SET appointment_pref1_start_date = (?::timestamptz) ,appointment_pref2_start_date =  (?::timestamptz), appointment_pref3_start_date =  (?::timestamptz) WHERE user_id = ? AND timespan_id = ? AND eg_id = ?");
            //em.merge(new V);
            query.setParameter(1,actualUserID);
            query.setParameter(2,current_timespan_id);
            query.setParameter(3,current_eg_id);
            query.setParameter(4,current_eg_instance);
            query.setParameter(5,pref1_start);
            query.setParameter(6,pref2_start);
            query.setParameter(7,pref3_start);

            query.getResultList();
            et.commit();

        } finally {
            em.close();
        }
    }


}
