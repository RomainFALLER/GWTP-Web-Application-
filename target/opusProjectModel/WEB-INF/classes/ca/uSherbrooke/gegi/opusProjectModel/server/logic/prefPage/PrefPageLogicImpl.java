package ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage;

import ca.uSherbrooke.gegi.commons.core.server.utils.UserSession;
import ca.uSherbrooke.gegi.commons.core.shared.utils.UserSessionActionException;
import ca.uSherbrooke.gegi.opusProjectModel.server.service.AppAccess;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.*;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.VAppointmentPreferenceData;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.VTutoratEgiData;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.entity.VUserAppParticipationData;
import com.google.inject.Inject;

import java.util.*;


public class PrefPageLogicImpl implements PrefPageLogic {

@Inject
UserSession user;


    @Override
    public PrefSaveResult savePrefChoices(PrefSaveAction psa) throws UserSessionActionException {
        PrefSaveResult res = new PrefSaveResult();
        String testPeriode = "Automne 2016";
        Integer currentID = user.getUserId();
        AppAccess app = new AppAccess();
        int i = 0;
        int j = 0;
        int taille = 0;
        int taille2 = 0;
        Date pref1 = null;
        Date pref2 = null;
        Date pref3 = null;
        System.out.println("*****************////////" + pref1 + pref2 + pref3);
        ArrayList<String> labelsTutos = new ArrayList<>();
        String label = "";

        List<VUserAppParticipationData> resultats = app.getAppsForActiveUser(testPeriode, currentID);
        taille = resultats.size();
        for (i = 0; i < taille; i++) {
            List<VAppointmentPreferenceData> choices = app.getChoices(resultats.get(i).getEgId(), currentID);
            for (j=0;j < psa.getAppsPrefs().get(i).getBlocks().size(); j++) {
                    if (psa.getAppsPrefs().get(i).getBlocks().get(j).getChoice() == TimeBlock.BlockChoice.FIRST_CHOICE) {
                        pref1 = psa.getAppsPrefs().get(i).getBlocks().get(j).getStartTime();
                    } else if (psa.getAppsPrefs().get(i).getBlocks().get(j).getChoice() == TimeBlock.BlockChoice.SECOND_CHOICE) {
                        pref2 = psa.getAppsPrefs().get(i).getBlocks().get(j).getStartTime();
                    } else if (psa.getAppsPrefs().get(i).getBlocks().get(j).getChoice() == TimeBlock.BlockChoice.THIRD_CHOICE) {
                        pref3 = psa.getAppsPrefs().get(i).getBlocks().get(j).getStartTime();
                    } else if (psa.getAppsPrefs().get(i).getBlocks().get(j).getChoice() == TimeBlock.BlockChoice.NO_CHOICE) {
                    }
            }
            app.setChoice(currentID, resultats.get(i).getTimespanId(), resultats.get(i).getEgId(),resultats.get(i).getEgInstanceNumber(), pref1,pref2, pref3);
            pref1 = null;
            pref2 = null;
            pref3 = null;
        }
            res.setSaveSuccessful(true);
        return res;
    }

    @Override
    public PrefPageInfosResult getPrefInfos() throws UserSessionActionException {
        int i = 0;
        int taille = 0;
        int taille2 = 0;
        int taille3 = 0;
        AppAccess app = new AppAccess();
        PrefPageInfosResult res = new PrefPageInfosResult();
        String testPeriode = "Automne 2016";
        Integer currentID = user.getUserId();
        //System.out.print("USERID SESSION -->" + testID);
        List<VUserAppParticipationData> resultats = app.getAppsForActiveUser(testPeriode, currentID);
        taille = resultats.size();
        List<AppID> unAppL = new ArrayList<>();
        List<egiID> unEgiL = new ArrayList<>();
        List<AppPrefs> unePrefL = new ArrayList<>();
        /**for (VUserAppParticipationData s : resultats) {
            System.out.println("***** ID: " + s.getEgId() + " --> LABEL: " + s.getAppLabel() + " ********* " + s.getSessionLabel() + " ********* " + s.getAppDesc());
        }**/
        for (i = 0; i < taille; i++) {
            unAppL.add(i,new AppID());
            unEgiL.add(i,new egiID());
            unePrefL.add(i,new AppPrefs());
            unEgiL.get(i).setEgi_ID(resultats.get(i).getEgId());
            unEgiL.get(i).setEgi_instance_ID(resultats.get(i).getEgInstanceNumber());
            unEgiL.get(i).setTimespan_ID(resultats.get(i).getTimespanId());
            if (resultats.get(i).getStatusLabel().equals("Professeur"))
            {
            unAppL.get(i).setTeacher(true);
            }
            unAppL.get(i).setAppShortName(resultats.get(i).getSessionLabel().toUpperCase() + "-" + resultats.get(i).getAppLabel().toUpperCase());
            unAppL.get(i).setAppLongName(resultats.get(i).getAppDesc());
            List<VTutoratEgiData> resultats2 = app.getTimesBlockForActiveUser(unEgiL.get(i).getEgi_ID(), unEgiL.get(i).getEgi_instance_ID(), unEgiL.get(i).getTimespan_ID());
            List<VAppointmentPreferenceData> choices = app.getChoices(unEgiL.get(i).getEgi_ID(),currentID);
            for (VAppointmentPreferenceData r : choices) {System.out.println("***** DATE PREMIER CHOIX: " + r.getAppointmentPref1StartDate());System.out.println("***** DATE DEUXIEME CHOIX: " + r.getAppointmentPref2StartDate());System.out.println("***** DATE TROISIEME CHOIX: " + r.getAppointmentPref3StartDate());}
            //for (VTutoratEgiData r : resultats2) {System.out.println("***** ID: " + resultats2.get(0).getEgId() + " --> DATE: " + resultats2.get(0).getStartDate() + " ********* " + r.getEndDate() + r.getAptId() + "TAILLE TAB: " + resultats2.size());}
            ArrayList <TimeBlock> unblockL = new ArrayList<>();
            taille2 = resultats2.size();
            taille3 = choices.size();
            int j = 0;
            int k = 0;
            System.out.println("SIZE CHOICES RESULT LIST: " + choices.size());
            ArrayList<String> labelsTutos = new ArrayList<>();
            String label = "";
           for (j = 0; j < taille2;j++) {
               label = resultats2.get(j).getTutoratLabel();
               if (!labelsTutos.contains(label)) {
                   labelsTutos.add(j, label);
                   unblockL.add(j, new TimeBlock());
               unblockL.get(j).setStartTime(resultats2.get(j).getStartDate());
               unblockL.get(j).setEndTime(resultats2.get(j).getEndDate());
               for (k = 0; k < taille3; k++) {
                   //System.out.println(choices.get(k).getAppointmentPref1StartDate() + " == " + unblockL.get(j).getStartTime());
                   if (choices.get(k).getAppointmentPref1StartDate() == null) {
                       choices.get(k).setAppointmentPref1StartDate(new Date(0));
                   }
                   if (choices.get(k).getAppointmentPref2StartDate() == null) {
                       choices.get(k).setAppointmentPref2StartDate(new Date(0));
                   }
                   if (choices.get(k).getAppointmentPref3StartDate() == null) {
                       choices.get(k).setAppointmentPref3StartDate(new Date(0));
                   }
                   if (choices.get(k).getAppointmentPref1StartDate().compareTo(unblockL.get(j).getStartTime()) == 0) {
                       unblockL.get(j).setChoice(TimeBlock.BlockChoice.FIRST_CHOICE);
                   } else if (choices.get(k).getAppointmentPref2StartDate().compareTo(unblockL.get(j).getStartTime()) == 0) {
                       unblockL.get(j).setChoice(TimeBlock.BlockChoice.SECOND_CHOICE);
                   } else if (choices.get(k).getAppointmentPref3StartDate().compareTo(unblockL.get(j).getStartTime()) == 0) {
                       unblockL.get(j).setChoice(TimeBlock.BlockChoice.THIRD_CHOICE);
                   } else {
                       unblockL.get(j).setChoice(TimeBlock.BlockChoice.NO_CHOICE);
                   }
               }
               unePrefL.get(i).getBlocks().add(unblockL.get(j));
           }
            }
            unePrefL.get(i).setAppId(unAppL.get(i));
            unePrefL.get(i).setEgiId(unEgiL.get(i));
            res.getApps().add(unePrefL.get(i));
        }
            System.out.println("***** Successful " + "*********");
            return res;
        }
}

