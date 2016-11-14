package ca.uSherbrooke.gegi.opusProjectModel.server.dispatch;

import ca.uSherbrooke.gegi.opusProjectModel.server.service.ListeElevAccess;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TestAlgoAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TestAlgoResult;
import com.gwtplatform.dispatch.rpc.server.ExecutionContext;
import com.gwtplatform.dispatch.rpc.server.actionhandler.ActionHandler;
import com.gwtplatform.dispatch.shared.ActionException;

/**
 * Created by Olivier on 7/19/2016.
 */
public class TestAlgoActionHandler implements ActionHandler<TestAlgoAction, TestAlgoResult> {
    @Override
    public TestAlgoResult execute(TestAlgoAction testAlgoAction, ExecutionContext executionContext) throws ActionException {
        ListeElevAccess algo = new ListeElevAccess();
        algo.generateGroups();
        return new TestAlgoResult();
    }

    @Override
    public Class<TestAlgoAction> getActionType() {
        return TestAlgoAction.class;
    }

    @Override
    public void undo(TestAlgoAction testAlgoAction, TestAlgoResult testAlgoResult, ExecutionContext executionContext) throws ActionException {

    }
}
