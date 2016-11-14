package ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage;

import com.gwtplatform.dispatch.rpc.shared.Result;

/**
 * Created by Olivier on 7/17/2016.
 */
public class GroupPageSaveResult implements Result {
    boolean saveSuccess = false;

    public GroupPageSaveResult() {
    }

    public boolean isSaveSuccess() {
        return saveSuccess;
    }

    public void setSaveSuccess(boolean saveSuccess) {
        this.saveSuccess = saveSuccess;
    }
}
