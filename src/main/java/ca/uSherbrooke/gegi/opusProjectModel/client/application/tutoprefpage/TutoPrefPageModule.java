package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget.CellPrefModule;
import com.google.gwt.dev.cfg.ResourceLoader;
import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TutoPrefPageModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new CellPrefModule());

        bindPresenter(TutoPrefPagePresenter.class, TutoPrefPagePresenter.MyView.class, TutoPrefPageView.class, TutoPrefPagePresenter.MyProxy.class);
    }
}
