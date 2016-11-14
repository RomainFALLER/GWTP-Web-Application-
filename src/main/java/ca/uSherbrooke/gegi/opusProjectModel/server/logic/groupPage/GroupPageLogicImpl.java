package ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage;

import ca.uSherbrooke.gegi.commons.core.server.utils.UserSession;
import ca.uSherbrooke.gegi.commons.core.shared.utils.UserSessionActionException;
import ca.uSherbrooke.gegi.opusProjectModel.server.service.AppAccess;
import ca.uSherbrooke.gegi.opusProjectModel.server.service.ListeElevAccess;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.*;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.*;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Olivier on 6/19/2016.
 */
public class GroupPageLogicImpl implements GroupPageLogic {
    @Inject
    UserSession user;

    @Override
    public GroupPageInfosResult getTutoGroups() throws UserSessionActionException {
        GroupPageInfosResult monGroupInfo = new GroupPageInfosResult();
        List<AppID> unAppL = new ArrayList<>();
        List<AppTutos> mesApps = new ArrayList<>();
        Integer currentID = user.getUserId();
        String testPeriode = "Automne 2016";
        AppAccess app = new AppAccess();
        int i = 0;
        int taille = 0;
        List<VUserAppParticipationData> resultats = app.getAppsForActiveUser(testPeriode, currentID);
        taille = resultats.size();
        for (i = 0; i < taille; i++) {
            ListeElevAccess teach = new ListeElevAccess();
            List<VUserAppParticipationData> resultats2 = teach.getTeachersApp(resultats.get(i).getEgId(),resultats.get(i).getEgInstanceNumber(),resultats.get(i).getTimespanId());
            Boolean valid = teach.getValidTuto(resultats.get(i).getTimespanId(), resultats.get(i).getEgId(),resultats.get(i).getEgInstanceNumber());
            unAppL.add(i, new AppID());
            unAppL.get(i).setAppShortName(resultats.get(i).getSessionLabel().toUpperCase() + "-" + resultats.get(i).getAppLabel().toUpperCase());
            unAppL.get(i).setAppLongName(resultats.get(i).getAppDesc());
            mesApps.add(i, new AppTutos());
            mesApps.get(i).setAppID(unAppL.get(i));
            mesApps.get(i).setValidGroups(valid);
            System.out.println("***********////"+ resultats2.size());
            int taille2 = resultats2.size();
            for (int j=0;j<taille2;j++) {
                mesApps.get(i).getTeachers().add(j, new Teacher());
                mesApps.get(i).getTeachers().get(j).setName(resultats2.get(j).getFisrtName() + " " + resultats2.get(j).getLastName());
                mesApps.get(i).getTeachers().get(j).setEmailAdd(resultats2.get(j).getEmailAddress());

                if (resultats2.get(j).getUserId() == currentID)
                {
                    unAppL.get(i).setTeacher(true);
                }
            }
            fillTutos(resultats.get(i).getEgId(), resultats.get(i).getEgInstanceNumber(), resultats.get(i).getTimespanId(), mesApps.get(i));
            monGroupInfo.getApps().add(mesApps.get(i));
        }
        return monGroupInfo;
    }

    @Override
    public GroupPageSaveResult savePresencesAndRespos(GroupPageSaveAction dataToSave)
    {
        ListeElevAccess access = new ListeElevAccess();
        int nbTutos = dataToSave.getApp().getTutos().size();
        List<TutoGroup> tutogroup = dataToSave.getApp().getTutos();
        for (int j = 0;j<nbTutos;j++)
        {
            int nbUsers = tutogroup.get(j).getStudents().size();
            List<Student> student = tutogroup.get(j).getStudents();
            for (int k = 0; k<nbUsers;k++)
            {
                int nbOuvFerm = tutogroup.get(j).getTutoAppointments().size();
                for (int q=0; q<nbOuvFerm;q++) {
                    System.out.println("******////******" + student.get(k).getRespAsString());
                    List<TutoAppointment> appointments = tutogroup.get(j).getTutoAppointments();
                    String responsabilite;
                    if (student.get(k).getResp() == Student.Responsability.INTENDANT)
                    {
                        responsabilite = "Intendant";
                    } else if (student.get(k).getResp() == Student.Responsability.SCRIBE)
                    {
                        responsabilite = "Scribe";
                    } else if (student.get(k).getResp() == Student.Responsability.SECRETARY)
                    {
                        responsabilite = "Secrétaire";
                    } else
                    {
                        responsabilite = null;
                    }
                    access.setResponsabilites(student.get(k).getId(), student.get(k).getGroupId(),responsabilite);
                    access.setPresences(student.get(k).getId(), appointments.get(q).getAppointmentId(), student.get(k).isPresent(appointments.get(q).getAppointmentId()));
                }
            }
        }
        return null;
    }


    public void setResponsa()
    {
        ListeElevAccess listeUsers = new ListeElevAccess();
        //listeUsers.setPresences();
    }

    public void fillTutos(Integer egi, Integer egi_instance, Integer timespand_id, AppTutos monAppetTutos) {
        AppAccess app = new AppAccess();
        List<VTutoratEgiData> resultats = app.getTimesBlockForActiveUser(egi, egi_instance, timespand_id);
        ArrayList<String> labelsTutos = new ArrayList<>();
        String label = "";
        int taille = resultats.size();
        for (int j = 0; j < taille; j++) {
            label = resultats.get(j).getTutoratLabel();
            if (!labelsTutos.contains(label)) {
                labelsTutos.add(j, label);
                monAppetTutos.getTutos().add(j, new TutoGroup());
                fillTutoUsersList(resultats.get(j).getAptId(), monAppetTutos.getTutos().get(j));
                monAppetTutos.getTutos().get(j).setTutoId(label);
                fillTutoAppointments(monAppetTutos.getTutos().get(j), resultats.get(j));

            } else
            {
                fillTutoAppointments(monAppetTutos.getTutos().get(labelsTutos.indexOf(label)), resultats.get(j));
            }
        }
    }


    public void fillTutoAppointments(TutoGroup monGroupe,VTutoratEgiData resultats)
    {
        TutoAppointment tutoA = new TutoAppointment();
        tutoA.setRoomId(resultats.getRoomLabel());
        tutoA.setStartTime(resultats.getStartDate());
        tutoA.setEndTime(resultats.getEndDate());
        tutoA.setAppointmentId(resultats.getAptId());
        tutoA.setLabel(resultats.getTutoratType());
        monGroupe.getTutoAppointments().add(tutoA);
    }

    public void fillTutoUsersList(Integer apt_id, TutoGroup monGroup) {
        ListeElevAccess listeUsers = new ListeElevAccess();
       List<VUserTutoratParticipationData> resultats = listeUsers.getListeElev(apt_id);
        int taille = resultats.size();
        for (int k = 0; k<taille;k++)
        {
            List<VAppointmentPresenceData> presences = listeUsers.getPresences(resultats.get(k).getUserId());
            int taillePresences = presences.size();
            Student student = new  Student();
            student.setCip(resultats.get(k).getAdministrativeUserId());
            student.setGroupId(resultats.get(k).getGroupId());
            for (int i = 0; i<taillePresences;i++)
            {
            student.setPresenceEntry(presences.get(i).getAppointmentId(),presences.get(i).isPresent());
            }
            System.out.println("***** TEST **** " + resultats.get(k).getResponsability());
            if (resultats.get(k).getResponsability() == null)
            {
                student.setResp(Student.Responsability.NONE);
            } else
            {
                student.setRespAsString(resultats.get(k).getResponsability());
            }
            student.setName(resultats.get(k).getPrenom() + " " +  resultats.get(k).getNom());
            student.setId(resultats.get(k).getUserId());
            monGroup.getStudents().add(student);

        }
    }


    public void fillPresenceUser()
    {
        ListeElevAccess listeUsers = new ListeElevAccess();
        List<VUserTutoratParticipationData> resultats = listeUsers.getListeElev(1);

    }
}

