package ca.uSherbrooke.gegi.opusProjectModel.server.dispatch;

import ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage.GroupPageLogic;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageInfosAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageInfosResult;
import com.google.inject.Inject;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Created by Olivier on 6/19/2016.
 */
public class GroupPageInfosActionHandler implements ActionHandler<GroupPageInfosAction, GroupPageInfosResult> {

    @Inject
    GroupPageLogic logic;

    @Override
    public GroupPageInfosResult execute(GroupPageInfosAction groupPageInfosAction, ExecutionContext executionContext) throws ActionException {
        return logic.getTutoGroups();
    }

    @Override
    public Class<GroupPageInfosAction> getActionType() {
        return GroupPageInfosAction.class;
    }

    @Override
    public void undo(GroupPageInfosAction groupPageInfosAction, GroupPageInfosResult groupPageInfosResult, ExecutionContext executionContext) throws ActionException {

    }
}
