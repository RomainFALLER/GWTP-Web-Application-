
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TutoMemberListWidgetModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(TutoMemberListWidgetPresenter.class, TutoMemberListWidgetPresenter.MyView.class, TutoMemberListWidgetView.class);
    }
}
