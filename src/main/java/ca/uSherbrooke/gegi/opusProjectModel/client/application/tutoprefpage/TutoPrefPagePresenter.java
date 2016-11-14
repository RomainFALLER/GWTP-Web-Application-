package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage;

import ca.uSherbrooke.gegi.commons.core.client.presenter.application.ApplicationPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.ConversionString;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.home.sideMenu.SideMenuPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget.ChoiceUpdate;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget.ChoiceUpdateHandler;
import ca.uSherbrooke.gegi.opusProjectModel.client.place.NameTokens;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.impl.cldr.DateTimeFormatInfoImpl_fr_CA;
import com.google.gwt.i18n.shared.DateTimeFormatInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget.CellPrefPresenter;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.TabListItem;
import java.util.ArrayList;
import java.util.Date;

public class TutoPrefPagePresenter extends Presenter<TutoPrefPagePresenter.MyView, TutoPrefPagePresenter.MyProxy> implements ChoiceUpdateHandler {
    interface MyView extends View {
        Button getBtnSave();
        void setTimeOutLabel(String value);
        void setDateLabel(String value);
        void setAppName(String value);
        void setConfirmSaveLabel(String value);
        void EnableButtonSave(boolean enable);
        void setLockLabel(boolean value, String timeLockPref);
        TabListItem addTab(String appShortName, boolean setActive);
        void deleteTabs();
        Button getBtnHome();
    }

    @Inject
    SideMenuPresenter sideMenuPresenter;

    @Inject
    PlaceManager placeManager;

    @Inject DispatchAsync dispatchAsync;

    @Inject CellPrefPresenter tableWidget;

    ArrayList<AppPrefs> appsPrefs;
    AppPrefs activeApp;

    public static final Slot SLOT_APP_TAB_CONTENT = new Slot();
    private static final int NUMBER_DAYS_BEFORE_LOCK = 5;

    @NameToken(NameTokens.tutoprefpage)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<TutoPrefPagePresenter> {
    }

    @Override
    protected void onBind() {
        super.onBind();
        setInSlot(TutoPrefPagePresenter.SLOT_APP_TAB_CONTENT, tableWidget);
        registerHandler(getEventBus().addHandler(ChoiceUpdate.TYPE, this));
        getView().getBtnSave().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                PrefSaveAction action = new PrefSaveAction();
                action.setAppsPrefs(appsPrefs);
                dispatchAsync.execute(action, saveResultCallback);
            }
        });
    }

    private void createNewAppPrefTab(int appPrefsIndex) {
        final int index = appPrefsIndex; //click handler needs a final int

        // first tab will be the active one
        TabListItem newTab = getView().addTab(appsPrefs.get(index).getAppId().getAppShortName(), appPrefsIndex == 0);

        newTab.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                activeApp = appsPrefs.get(index);
                TutoPrefTabChange tabChangeEvt = new TutoPrefTabChange();

                Date date = appsPrefs.get(index).getBlocks().get(0).getStartTime();
                date = new Date(date.getTime() - NUMBER_DAYS_BEFORE_LOCK * 24 * 3600 * 1000 );
                SetLabels(activeApp, date);

                tabChangeEvt.setTimeBlocks(appsPrefs.get(index).getBlocks());
                tabChangeEvt.setTimeLockPref(date);
                tabChangeEvt.setTimeBlocks(activeApp.getBlocks());
                getEventBus().fireEvent(tabChangeEvt);
            }
        });
    }

    private void SetLabels(AppPrefs app, Date timeLockPref)
    {
        getView().setAppName(app.getAppId().getAppLongName());
        Date date = app.getBlocks().get(0).getStartTime();
        DateTimeFormatInfo formatFR = new DateTimeFormatInfoImpl_fr_CA();
        DateTimeFormat dateFormat = DateTimeFormat.getFormat(formatFR.dateFormatFull());
        getView().setDateLabel(ConversionString.ConversionDateInFrench(dateFormat.format(date)));
        getView().setLockLabel(timeLockPref.before(new Date()), dateFormat.format(timeLockPref));
    }


    @Inject
    TutoPrefPagePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        PrefPageInfosAction action = new PrefPageInfosAction();
        dispatchAsync.execute(action,infosCallback);
    }


    @Override
    public boolean useManualReveal() {
        return true;
    }

    private AsyncCallback<PrefPageInfosResult> infosCallback = ManualRevealCallback.create(this, new AsyncCallback<PrefPageInfosResult>(){

        @Override
        public void onFailure(Throwable throwable) {
            Window.alert("Failed to retrieve critical infos from server");
        }

        @Override
        public void onSuccess(PrefPageInfosResult prefPageInfosResult) {
            appsPrefs = prefPageInfosResult.getApps();
            activeApp = appsPrefs.get(0);

            Date date = appsPrefs.get(0).getBlocks().get(0).getStartTime();
            date = new Date(date.getTime() - NUMBER_DAYS_BEFORE_LOCK * 24 * 3600 * 1000 );

            TutoPrefTabChange evt = new TutoPrefTabChange();
            evt.setTimeBlocks(activeApp.getBlocks());
            evt.setTimeLockPref(date);
            getEventBus().fireEvent(evt);

            getView().deleteTabs();
            for(AppPrefs app : appsPrefs){
                if(!app.getAppId().isTeacher())
                    createNewAppPrefTab(appsPrefs.indexOf(app));
            }

            getView().EnableButtonSave(false);
            SetLabels(activeApp,date);
        }
    });

    private AsyncCallback<PrefSaveResult> saveResultCallback = new AsyncCallback<PrefSaveResult>() {
        @Override
        public void onFailure(Throwable throwable) {
            // fail to save
            // TO DO: Inform user that we couldn't reach the server
        }

        @Override
        public void onSuccess(PrefSaveResult prefSaveResult) {
            if(prefSaveResult.getSaveSuccessful()){
                // save success
                getView().setConfirmSaveLabel("Les préférences ont été sauvegardées");
                getView().EnableButtonSave(false);
            }
            else{
                // fail to save
                getView().setConfirmSaveLabel("Les préférences n'ont pu être sauvegardées");
            }
        }
    };

    @Override
    public void onChoiceUpdate(ChoiceUpdate event) throws RuntimeException {
        int size;

        getView().setConfirmSaveLabel("");
        getView().EnableButtonSave(true);

        if((size = activeApp.getBlocks().size()) != event.getNewChoices().size()){
            throw new RuntimeException("There is a mismatch between schedule data and schedule presentation");
        }
        for(int i = 0; i < size; i++){
            activeApp.getBlocks().get(i).setChoice(event.getNewChoices().get(i));
        }
    }

    @Override
    protected void onReset() {
        super.onReset();

        sideMenuPresenter.getView().addToApplicationPresenter();
        sideMenuPresenter.refreshList();

        getView().getBtnHome().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                PlaceRequest request = new PlaceRequest(NameTokens.getHome());
                placeManager.revealPlace(request);
            }
        });

    }


}
