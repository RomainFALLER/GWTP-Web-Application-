package ca.uSherbrooke.gegi.opusProjectModel.server.service;

import ca.uSherbrooke.gegi.commons.core.client.accessRestriction.BootstrapperImpl;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.*;
import com.google.inject.Inject;

import javax.annotation.Nullable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by wolfy on 07/07/16.
 */
public class ListeElevAccess {

    EntityManagerFactory emf;

    @Inject
    public ListeElevAccess() {
    }

    public void getStatutUser(Integer actualUserID) {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            //Query query = em.createNamedQuery("get_user_statu");
           //query.setParameter(1,actualUserID);
            et.commit();
            //return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VUserTutoratParticipationData> getListeElev(Integer apt_id) {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_users_by_groups");
            query.setParameter(1,apt_id);
            et.commit();
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VUserAppParticipationData> getTeachersApp(Integer egi_id, Integer egi_instance_id, Integer timespan_id)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_teacher_app");
            query.setParameter(1,egi_id);
            query.setParameter(2,egi_instance_id);
            query.setParameter(3,timespan_id);
            et.commit();
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public List<VAppointmentPresenceData> getPresences(Integer user_id)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_presences");
            query.setParameter(1,user_id);
            et.commit();
            return query.getResultList();
        } finally {
            em.close();
        }
    }


    public void setPresences(Integer userId, Integer aptId, Boolean present)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("set_presences");
            query.setParameter(1,userId);
            query.setParameter(2,aptId);
            query.setParameter(3,present);

            query.getResultList();
            et.commit();

        } finally {
            em.close();
        }
    }

    public void setResponsabilites(Integer userId, Integer groupId,@Nullable String resp)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("set_resp");
            query.setParameter(1,userId);
            query.setParameter(2,groupId);
            query.setParameter(3,resp);

            query.getResultList();
            et.commit();

        } finally {
            em.close();
        }
    }

    public void generateGroups()
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("generate_groups");
            query.getResultList();
            et.commit();
        } finally {
            em.close();
        }
    }

    public Boolean getValidTuto(Integer timespan_id, Integer egi_id, Integer egi_instance_id)
    {
        EntityManager em = Persistence.createEntityManagerFactory("opusProjectModel").createEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        try {
            Query query = em.createNamedQuery("get_true_or_false");
            query.setParameter(2,egi_id);
            query.setParameter(3,egi_instance_id);
            query.setParameter(1,timespan_id);
            et.commit();
            return ((Boolean) query.getSingleResult());
        } finally {
            em.close();
        }
    }

}
