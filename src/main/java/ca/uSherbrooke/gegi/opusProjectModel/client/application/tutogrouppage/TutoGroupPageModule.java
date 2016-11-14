package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget.TutoTabsWidgetModule;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget.TutoMemberListWidgetModule;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TutoGroupPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new TutoTabsWidgetModule());
        install(new TutoMemberListWidgetModule());

        bindPresenter(TutoGroupPagePresenter.class, TutoGroupPagePresenter.MyView.class, TutoGroupPageView.class, TutoGroupPagePresenter.MyProxy.class);
    }
}
