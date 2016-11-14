package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.PrefPageCom;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class PrefComTestModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(PrefComTestPresenter.class, PrefComTestPresenter.MyView.class, PrefComTestView.class, PrefComTestPresenter.MyProxy.class);
    }
}
