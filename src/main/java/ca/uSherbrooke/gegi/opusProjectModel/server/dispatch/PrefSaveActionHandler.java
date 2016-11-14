package ca.uSherbrooke.gegi.opusProjectModel.server.dispatch;

import ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage.PrefPageLogic;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefSaveAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefSaveResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Created by Olivier on 5/28/2016.
 */
public class PrefSaveActionHandler implements ActionHandler<PrefSaveAction,PrefSaveResult>{
    @Inject
    PrefPageLogic logic;

    @Override
    public PrefSaveResult execute(PrefSaveAction prefSaveAction, ExecutionContext executionContext) throws ActionException {
        return logic.savePrefChoices(prefSaveAction);
    }

    @Override
    public Class<PrefSaveAction> getActionType() {
        return PrefSaveAction.class;
    }

    @Override
    public void undo(PrefSaveAction prefSaveAction, PrefSaveResult prefSaveResult, ExecutionContext executionContext) throws ActionException {

    }
}
