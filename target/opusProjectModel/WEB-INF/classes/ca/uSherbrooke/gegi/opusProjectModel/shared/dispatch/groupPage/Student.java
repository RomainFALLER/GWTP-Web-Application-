package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Olivier on 6/19/2016.
 */
public class Student implements Serializable {
    public enum Responsability{
        SCRIBE("Scribe"),
        INTENDANT("Intendant"),
        SECRETARY("Secrétaire"),
        NONE("Aucune");

        private String text;

        Responsability(String text){
            this.text=text;
        }
        public String getText() {
            return this.text;
        }
        public static Responsability fromString(String text) {
            if (text!=null) {
                for (Responsability r : Responsability.values()) {
                    if (text.equalsIgnoreCase(r.text)) {
                        return r;
                    }
                }
            }
           return null;
        }

        public static ArrayList<String> getAllResponsabilityStrings(){
            ArrayList<String> resps = new ArrayList<String>();

            for (Responsability r : Responsability.values()) {
                resps.add(r.text);
            }
            return resps;
        }

        public static int getQuantity()
        {
            int qty = 0;
            for (Responsability r : Responsability.values()){
                qty++;
            }
            //Exclude NONE because it doesn't count as a responsability:
            return qty-1;
        }
    }

    Integer id;
    String name = "member name";
    String cip = "member cip";
    Integer groupId;
    HashMap<Integer,Boolean> presences = new HashMap<Integer, Boolean>();
    Responsability resp = Responsability.NONE;

    public Student() {
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCip() {
        return cip;
    }

    public void setCip(String cip) {
        this.cip = cip;
    }

    public Responsability getResp() {

        return resp;
    }
    public String getRespAsString(){
        return resp.getText();
    }

    public void setResp(Responsability resp) {
        this.resp = resp;
    }
    public void setRespAsString(String s){
        this.resp = Responsability.fromString(s);
    }
    public static ArrayList<String> getAllResponsabilityStrings() {
        return Responsability.getAllResponsabilityStrings();
    }

    public void setPresenceEntry(int appointmentId, boolean isPresent){
        presences.put(appointmentId, isPresent);
    }

    public void clearPresencesEntries(){
        presences.clear();
    }

    public boolean isPresent(int appointmentId){
        boolean isPresent = false;
        if(presences.get(appointmentId) != null) {
            isPresent = presences.get(appointmentId);
        }
        return isPresent;
    }

    public boolean hasResponsability(){
        if (resp != Responsability.NONE) return true;
        return false;
    }
}
