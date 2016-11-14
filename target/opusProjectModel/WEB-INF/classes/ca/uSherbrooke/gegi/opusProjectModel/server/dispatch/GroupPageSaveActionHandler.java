package ca.uSherbrooke.gegi.opusProjectModel.server.dispatch;

import ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage.GroupPageLogic;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageSaveAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageSaveResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Created by Olivier on 7/17/2016.
 */
public class GroupPageSaveActionHandler implements ActionHandler<GroupPageSaveAction, GroupPageSaveResult> {

    @Inject
    GroupPageLogic logic;

    @Override
    public GroupPageSaveResult execute(GroupPageSaveAction groupPageSaveAction, ExecutionContext executionContext) throws ActionException {
        return logic.savePresencesAndRespos(groupPageSaveAction);
    }

    @Override
    public Class<GroupPageSaveAction> getActionType() {
        return GroupPageSaveAction.class;
    }

    @Override
    public void undo(GroupPageSaveAction groupPageSaveAction, GroupPageSaveResult groupPageSaveResult, ExecutionContext executionContext) throws ActionException {

    }
}
