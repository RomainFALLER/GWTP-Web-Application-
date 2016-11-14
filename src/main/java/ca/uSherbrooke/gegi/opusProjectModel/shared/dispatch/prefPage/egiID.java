package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage;

import java.io.Serializable;

/**
 * Created by Olivier on 5/26/2016.
 */
public class egiID implements Serializable{
    Integer egi_ID;
    Integer egi_instance_ID;
    Integer timespan_ID;

    public egiID() {}

    public Integer getEgi_ID() {

        return egi_ID;
    }

    public void setEgi_ID(Integer egi_ID) {
        this.egi_ID = egi_ID;
    }

    public Integer getEgi_instance_ID() {
        return egi_instance_ID;
    }

    public void setEgi_instance_ID(Integer egi_instance_ID) {
        this.egi_instance_ID = egi_instance_ID;
    }

    public Integer getTimespan_ID() {
        return timespan_ID;
    }

    public void setTimespan_ID(Integer timespan_ID) {
        this.timespan_ID = timespan_ID;
    }
}
