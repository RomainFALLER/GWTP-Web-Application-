package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage;

import ca.uSherbrooke.gegi.commons.core.client.presenter.application.ApplicationPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.home.sideMenu.SideMenuPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget.PresenceOrRespoUpdatedEvent;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget.PresenceOrRespoUpdatedHandler;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget.TutoTabsWidgetPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.place.NameTokens;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.TabListItem;

import java.util.ArrayList;

public class TutoGroupPagePresenter extends Presenter<TutoGroupPagePresenter.MyView, TutoGroupPagePresenter.MyProxy> {
    interface MyView extends View {
        TabListItem addTab(String appShortName, boolean setActive);
        void setAppNameLabel(String value);
        void deleteTabs();
        Button getBtnHome();
    }

    @NameToken(NameTokens.tutogrouppage)
    @ProxyStandard
    interface MyProxy extends ProxyPlace<TutoGroupPagePresenter> {
    }

    @Inject
    SideMenuPresenter sideMenuPresenter;

    @Inject
    PlaceManager placeManager;

    public static final Slot APP_GROUP_TAB_CONTENT = new Slot();

    private ArrayList<AppTutos> apps = new ArrayList<AppTutos>();

    @Inject DispatchAsync dispatchAsync;
    @Inject TutoTabsWidgetPresenter tutoTabsWidget;

    @Inject
    TutoGroupPagePresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
    }

    @Override
    protected void onBind() {
        super.onBind();
        setInSlot(APP_GROUP_TAB_CONTENT, tutoTabsWidget);
    }

    @Override
    public boolean useManualReveal() {
        return true;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        GroupPageInfosAction action = new GroupPageInfosAction();
        dispatchAsync.execute(action, pageInfosCallback);
    }

    private AsyncCallback<GroupPageInfosResult> pageInfosCallback = ManualRevealCallback.create(this, new AsyncCallback<GroupPageInfosResult>(){
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert("Failed to retrieve critical infos from server");
        }

        @Override
        public void onSuccess(final GroupPageInfosResult groupPageInfosResult) {
            TabListItem tab;
            apps = groupPageInfosResult.getApps();
            getView().deleteTabs();
            for(final AppTutos app: apps){
                // first tab is always the active one
                if(apps.indexOf(app) == 0){
                    tab = getView().addTab(app.getAppID().getAppShortName(), true);
                    // send event to make tutoTabsWidget create his own tabs for the first time
                    TutoGroupAppTabChangeEvent evt = new TutoGroupAppTabChangeEvent();
                    evt.setApp(app);
                    getEventBus().fireEvent(evt);
                }
                else{
                    tab = getView().addTab(app.getAppID().getAppShortName(), false);
                }

                tab.addClickHandler(new ClickHandler() {
                    @Override
                    public void onClick(ClickEvent clickEvent) {
                        // the tutoTabsWidget will create his tuto tabs correctly when clicking on an app tab
                        TutoGroupAppTabChangeEvent evt = new TutoGroupAppTabChangeEvent();
                        getView().setAppNameLabel(app.getAppID().getAppLongName());
                        evt.setApp(app);
                        getEventBus().fireEvent(evt);
                    }
                });

            }
            getView().setAppNameLabel(apps.get(0).getAppID().getAppLongName());
        }
    });

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
