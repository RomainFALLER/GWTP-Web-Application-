package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage;

import com.gwtplatform.dispatch.rpc.shared.Result;

/**
 * Created by Olivier on 5/28/2016.
 */
public class PrefSaveResult implements Result {
    Boolean isSaveSuccessful = false;

    public PrefSaveResult(){}

    public Boolean getSaveSuccessful() {
        return isSaveSuccessful;
    }

    public void setSaveSuccessful(Boolean saveSuccessful) {
        isSaveSuccessful = saveSuccessful;
    }
}
