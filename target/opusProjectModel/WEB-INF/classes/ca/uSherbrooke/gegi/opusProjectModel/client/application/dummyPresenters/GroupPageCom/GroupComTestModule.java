package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.GroupPageCom;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class GroupComTestModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenter(GroupComTestPresenter.class, GroupComTestPresenter.MyView.class, GroupComTestView.class, GroupComTestPresenter.MyProxy.class);
    }
}
