/*
 * Copyright 2015, 2016 Département de Génie Électrique et Génie Informatique (GEGI) de l'Université de Sherbrooke (UdeS).
 * Tous droits réservés / All rights reserved.
 */

package ca.uSherbrooke.gegi.opusProjectModel.client.application;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.GroupPageCom.GroupComTestModule;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.PrefPageCom.PrefComTestModule;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.home.HomeModule;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.TutoGroupPageModule;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.TutoPrefPageModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class OpusProjectModelModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new TutoGroupPageModule());
        install(new TutoPrefPageModule());
        install(new HomeModule());
        install(new PrefComTestModule());
        install(new GroupComTestModule());
    }
}