package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.ConversionString;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.*;

import javax.inject.Inject;


public class TutoPrefPageView extends ViewImpl implements TutoPrefPagePresenter.MyView {
    interface Binder extends UiBinder<Widget, TutoPrefPageView> {
    }
    public interface TutoPrefCss extends CssResource {
        String styleButtonEnable();
        String button();
        String labelDateTimeOut();
        String labelTimeOut();
    }

    @UiField TutoPrefCss tutoPrefCss;

    @Inject
    TutoPrefPageView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
        bindSlot(TutoPrefPagePresenter.SLOT_APP_TAB_CONTENT, prefGrid);
    }

    @UiField NavTabs appSelectTabs;
    @UiField TabPane prefGrid;
    @UiField Heading appName;
    @UiField Heading dateLabel;
    @UiField Heading timeOutLabel;
    @UiField Button btnSave;
    @UiField Button btnHome;
    @UiField Heading labelConfirmSave;
    @UiField Heading lockLabel;

    public Button getBtnSave() {
        return btnSave;
    }

    public TabListItem addTab(String appShortName, boolean setActive){

        TabListItem thetab = new TabListItem(appShortName);
        thetab.setDataTarget("prefGrid");
        appSelectTabs.add(thetab);
        thetab.setActive(setActive);

        return thetab;
    }

    public void setAppName(String value) { appName.setText(value); }
    public void setDateLabel(String value) { dateLabel.setText(value); }
    public void setTimeOutLabel(String value) { timeOutLabel.setText(value); }
    public void setConfirmSaveLabel(String value) { labelConfirmSave.setText(value); }
    public void setLockLabel(boolean value, String timeToLock)
    {
        if(!value){
            lockLabel.setText("Verrouillage des préférences le \n");
            lockLabel.setStyleName(getCss().labelDateTimeOut());
            timeOutLabel.setText(ConversionString.ConversionDateInFrench(timeToLock));

        }
        else {
            lockLabel.setText("Préférences verrouillé");
            lockLabel.setStyleName(getCss().labelTimeOut());
            timeOutLabel.setText("");
        }
    }

    public TutoPrefCss getCss() {return tutoPrefCss;}
    public Button getBtnHome() {return btnHome;}

    public void EnableButtonSave(boolean enable)
    {
        if(enable)
        {
            getBtnSave().setEnabled(true);
            getBtnSave().addStyleName(getCss().button());
            getBtnSave().removeStyleName(getCss().styleButtonEnable());
        }
        else
        {
            getBtnSave().setEnabled(false);
            getBtnSave().addStyleName(getCss().styleButtonEnable());
            getBtnSave().removeStyleName(getCss().button());
        }
    }

    public void deleteTabs()
    {
        appSelectTabs.clear();
    }

}
