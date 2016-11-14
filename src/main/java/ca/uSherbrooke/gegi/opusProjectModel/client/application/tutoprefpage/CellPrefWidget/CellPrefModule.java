
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CellPrefModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(CellPrefPresenter.class, CellPrefPresenter.MyView.class, CellPrefView.class);
    }
}
