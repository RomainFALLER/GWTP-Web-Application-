
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TutoTabsWidgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(TutoTabsWidgetPresenter.class, TutoTabsWidgetPresenter.MyView.class, TutoTabsWidgetView.class);
    }
}
