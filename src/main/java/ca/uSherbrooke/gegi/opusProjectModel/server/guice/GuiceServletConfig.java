/*
 * Copyright 2015, 2016 Département de Génie Électrique et Génie Informatique (GEGI) de l'Université de Sherbrooke (UdeS).
 * Tous droits réservés / All rights reserved.
 */

package ca.uSherbrooke.gegi.opusProjectModel.server.guice;


import ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage.GroupPageLogicModule;
import ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage.PrefPageLogicModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
    	Injector injector = Guice.createInjector(
				new ServerModule(),
				new ca.uSherbrooke.gegi.commons.core.server.guice.DispatchServletModule(),
				new DispatchServletModule(),
                new PrefPageLogicModule(),
				new GroupPageLogicModule());

		return injector;
    }
}