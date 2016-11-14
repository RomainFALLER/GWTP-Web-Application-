package ca.uSherbrooke.gegi.opusProjectModel.server.logic.prefPage;

import com.google.inject.AbstractModule;

/**
 * Created by Olivier on 5/31/2016.
 */
public class PrefPageLogicModule extends AbstractModule {
    @Override
    protected void configure() {

        // use this line to test communication between server and client without accessing database
        //bind(PrefPageLogic.class).to(DummyPrefPageLogicImpl.class);

        // use this line for the real implementation with the server doing some requests to the database
        bind(PrefPageLogic.class).to(PrefPageLogicImpl.class);
    }
}
