package ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage;

import ca.uSherbrooke.gegi.commons.core.shared.utils.UserSessionActionException;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageInfosResult;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageSaveAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageSaveResult;

/**
 * Created by Olivier on 6/19/2016.
 */
public interface GroupPageLogic {
    GroupPageInfosResult getTutoGroups() throws UserSessionActionException;
    GroupPageSaveResult savePresencesAndRespos(GroupPageSaveAction dataToSave);
}
