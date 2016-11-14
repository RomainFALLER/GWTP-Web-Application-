package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

/**
 * Created by Olivier on 6/1/2016.
 */
public class PrefTableRow{
    public enum PrefId{
        PREF1,
        PREF2,
        PREF3
    }

    private String timeRangeStr;
    private boolean pref1 = false;
    private boolean pref2 = false;
    private boolean pref3 = false;

    public PrefTableRow(String timeRangeStr, boolean pref1, boolean pref2, boolean pref3)
    {
        this.timeRangeStr = timeRangeStr;
        this.pref1 = pref1;
        this.pref2 = pref2;
        this.pref3 = pref3;
    }

    public String getTimeRangeStr() {
        return timeRangeStr;
    }

    public void setTimeRangeStr(String timeRangeStr) {
        this.timeRangeStr = timeRangeStr;
    }

    public boolean getPref1() {
        return pref1;
    }

    public void setPref1(boolean pref1) {
        this.pref1 = pref1;
        if(pref1){
            setPref2(false);
            setPref3(false);
        }
    }

    public boolean getPref2() {
        return pref2;
    }

    public void setPref2(boolean pref2) {
        this.pref2 = pref2;
        if(pref2){
            setPref1(false);
            setPref3(false);
        }
    }

    public boolean getPref3() {
        return pref3;
    }

    public void setPref3(boolean pref3) {
        this.pref3 = pref3;
        if(pref3){
            setPref1(false);
            setPref2(false);
        }
    }
}
