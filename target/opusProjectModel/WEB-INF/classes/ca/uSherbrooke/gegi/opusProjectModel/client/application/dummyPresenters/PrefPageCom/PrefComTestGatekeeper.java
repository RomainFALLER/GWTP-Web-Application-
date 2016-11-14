package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.PrefPageCom;

import com.gwtplatform.mvp.client.proxy.Gatekeeper;

/**
 * Created by Olivier on 5/27/2016.
 */
public class PrefComTestGatekeeper implements Gatekeeper {
    @Override
    public boolean canReveal() {
        return true;
    }
}
