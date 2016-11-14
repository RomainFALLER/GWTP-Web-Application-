/*
 * Copyright 2015, 2016 Département de Génie Électrique et Génie Informatique (GEGI) de l'Université de Sherbrooke (UdeS).
 * Tous droits réservés / All rights reserved.
 */

package ca.uSherbrooke.gegi.opusProjectModel.server.guice;

import ca.uSherbrooke.gegi.opusProjectModel.server.dispatch.*;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageInfosAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageSaveAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TestAlgoAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefPageInfosAction;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.PrefSaveAction;
import com.gwtplatform.dispatch.rpc.server.guice.HandlerModule;

public class ServerModule extends HandlerModule {
    @Override
    protected void configureHandlers() {
        bindHandler(PrefSaveAction.class, PrefSaveActionHandler.class);
        bindHandler(PrefPageInfosAction.class, PrefPageInfosActionHandler.class);
        bindHandler(GroupPageInfosAction.class, GroupPageInfosActionHandler.class);
        bindHandler(GroupPageSaveAction.class, GroupPageSaveActionHandler.class);
        bindHandler(TestAlgoAction.class, TestAlgoActionHandler.class);
    }
}