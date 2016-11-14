package ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage;

import ca.uSherbrooke.gegi.commons.core.shared.utils.UserSessionActionException;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefPageInfosResult;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefSaveAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefSaveResult;

/**
 * Created by Olivier on 5/26/2016.
 */
public interface PrefPageLogic {
    PrefSaveResult savePrefChoices(PrefSaveAction psa) throws UserSessionActionException;
    PrefPageInfosResult getPrefInfos() throws IllegalAccessException, InstantiationException, UserSessionActionException;
}
