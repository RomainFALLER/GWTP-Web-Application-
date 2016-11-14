package ca.uSherbrooke.gegi.opusProjectModel.server.dispatch;

import ca.uSherbrooke.gegi.commons.core.shared.utils.UserSessionActionException;
import ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage.PrefPageLogic;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefPageInfosAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefPageInfosResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Created by Olivier on 6/1/2016.
 */
public class PrefPageInfosActionHandler implements ActionHandler<PrefPageInfosAction, PrefPageInfosResult> {
    @Inject
    PrefPageLogic logic;

    @Override
    public PrefPageInfosResult execute(PrefPageInfosAction prefPageInfosAction, ExecutionContext executionContext) {
        try {
            return logic.getPrefInfos();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (UserSessionActionException e) {
            e.printStackTrace();
        }

        return null;
    }



    @Override
    public Class<PrefPageInfosAction> getActionType() {
        return PrefPageInfosAction.class;
    }

    @Override
    public void undo(PrefPageInfosAction prefPageInfosAction, PrefPageInfosResult prefPageInfosResult, ExecutionContext executionContext) throws ActionException {

    }
}
