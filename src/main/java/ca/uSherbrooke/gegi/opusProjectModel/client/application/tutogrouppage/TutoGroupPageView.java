package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.*;

import javax.inject.Inject;


public class TutoGroupPageView extends ViewImpl implements TutoGroupPagePresenter.MyView {
    interface Binder extends UiBinder<Widget, TutoGroupPageView> {
    }

    public interface TutoGroupPageCss extends CssResource {
        String labelTitle();
    }

    @UiField NavTabs appSelectTabs;
    @UiField TabPane tabContent;
    @UiField Heading appName;
    @UiField TutoGroupPageCss tutoGroupPageCss;
    @UiField Button btnHome;

    @Inject
    TutoGroupPageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(TutoGroupPagePresenter.APP_GROUP_TAB_CONTENT, tabContent);
    }


    public void deleteTabs()
    {
        appSelectTabs.clear();
    }
    public void setAppNameLabel(String value) { appName.setText(value); }
    public Button getBtnHome() {return btnHome;}

    public TabListItem addTab(String appShortName, boolean setActive){

        TabListItem thetab = new TabListItem(appShortName);
        thetab.setDataTarget("tabContent");
        appSelectTabs.add(thetab);
        thetab.setActive(setActive);

        return thetab;
    }
}
