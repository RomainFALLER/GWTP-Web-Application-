package ca.uSherbrooke.gegi.opusProjectModel.server.logic.groupPage;

import com.google.inject.AbstractModule;

/**
 * Created by Olivier on 6/19/2016.
 */
public class GroupPageLogicModule extends AbstractModule {
    @Override
    protected void configure() {
        // use this line to test communication between server and client without accessing database
        //bind(GroupPageLogic.class).to(DummyGroupPageLogicImpl.class);

        // use this line for the real implementation with the server doing some requests to the database
        bind(GroupPageLogic.class).to(GroupPageLogicImpl.class);
    }
}