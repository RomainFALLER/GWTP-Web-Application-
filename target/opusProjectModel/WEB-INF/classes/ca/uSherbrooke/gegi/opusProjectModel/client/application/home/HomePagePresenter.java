/*
 * Copyright 2015, 2016 Département de Génie Électrique et Génie Informatique (GEGI) de l'Université de Sherbrooke (UdeS).
 * Tous droits réservés / All rights reserved.
 */

package ca.uSherbrooke.gegi.opusProjectModel.client.application.home;

import javax.inject.Inject;

import ca.uSherbrooke.gegi.commons.core.client.presenter.application.ApplicationPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.home.sideMenu.SideMenuPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget.CellPrefPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.place.NameTokens;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.AppTutos;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.GroupPageInfosResult;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import org.gwtbootstrap3.client.ui.Button;

public class HomePagePresenter extends Presenter<HomePagePresenter.MyView, HomePagePresenter.MyProxy> {

    @Inject SideMenuPresenter sideMenuPresenter;
    @Inject CellPrefPresenter cellPrefPresenter;

    boolean IsOnlyATeacher = false;

	public interface MyView extends View {
        Button getBtnTuto();
        Button getBtnPref();
    }

    @ProxyStandard
    @NameToken(NameTokens.home)
	/*@UseGatekeeper(AuthenticationGatekeeper.class)*/
    public interface MyProxy extends ProxyPlace<HomePagePresenter> {
    }

    @Inject
    ApplicationPresenter applicationPresenter;

    @Inject
    PlaceManager placeManager;

    @Inject
    public HomePagePresenter(EventBus eventBus, MyView view, MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
    }

    @Override
    protected void onReset() {
        super.onReset();

        sideMenuPresenter.getView().addToApplicationPresenter();
        sideMenuPresenter.refreshList();

        if(IsOnlyATeacher){
            PlaceRequest request = new PlaceRequest(NameTokens.getTutogrouppage());
            placeManager.revealPlace(request);
        }
        else {

            getView().getBtnPref().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    PlaceRequest request = new PlaceRequest(NameTokens.getTutoprefpage());
                    placeManager.revealPlace(request);
                }
            });

            getView().getBtnTuto().addClickHandler(new ClickHandler() {
                @Override
                public void onClick(ClickEvent clickEvent) {
                    PlaceRequest request = new PlaceRequest(NameTokens.getTutogrouppage());
                    placeManager.revealPlace(request);
                }
            });
        }
    }


    private AsyncCallback<GroupPageInfosResult> pageInfosCallback = ManualRevealCallback.create(this, new AsyncCallback<GroupPageInfosResult>(){
        @Override
        public void onFailure(Throwable throwable) {Window.alert("Failed to retrieve critical infos from server");}

        @Override
        public void onSuccess(final GroupPageInfosResult groupPageInfosResult) {

            for(AppTutos app : groupPageInfosResult.getApps())
            {
                if(!app.getAppID().isTeacher())
                {
                    IsOnlyATeacher = false;
                }
            }
        }
    });
}