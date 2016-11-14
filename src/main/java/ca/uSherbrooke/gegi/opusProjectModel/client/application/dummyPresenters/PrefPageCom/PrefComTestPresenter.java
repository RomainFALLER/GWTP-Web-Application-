package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.PrefPageCom;

import ca.uSherbrooke.gegi.commons.core.client.presenter.application.ApplicationPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.place.NameTokens;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.AppID;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.annotations.UseGatekeeper;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

import java.util.ArrayList;

/*
    This presenter is to test the communication between client and server to get the necessary infos
    for displaying the preference page for a student. CanReaveal() method in PrefComTestGatekeeper must
    return true for the page to be shown. You can access it by typing the name token in the URL after the #.
 */

public class PrefComTestPresenter extends Presenter<PrefComTestPresenter.MyView, PrefComTestPresenter.MyProxy> {
    interface MyView extends View {
        TextArea getTextArea();
        Button getSaveButton();
        Label getIsSavedLabel();
    }

    @NameToken(NameTokens.prefcomtest)
    @ProxyCodeSplit
    @UseGatekeeper(PrefComTestGatekeeper.class)
    interface MyProxy extends ProxyPlace<PrefComTestPresenter> {
    }

    @Inject
    DispatchAsync dispatchAsync;

    ArrayList<AppID> apps;

    public static final NestedSlot SLOT_PREF_COM_TEST = new NestedSlot();

    @Inject
    PrefComTestPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);

    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                PrefSaveAction action = new PrefSaveAction();
                dispatchAsync.execute(action, savePrefsCallBack);
            }
        });
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        PrefPageInfosAction action = new PrefPageInfosAction();
        dispatchAsync.execute(action,infosCallBack);
    }

    @Override
    public boolean useManualReveal() {
        return true;
    }

    private AsyncCallback<PrefPageInfosResult> infosCallBack = ManualRevealCallback.create(this, new AsyncCallback<PrefPageInfosResult>(){

        @Override
        public void onFailure(Throwable throwable) {
            getView().getTextArea().setText("Failed to retrieve infos from server");
        }

        @Override
        public void onSuccess(PrefPageInfosResult prefPageInfosResult) {
            String output = "";
            for (AppPrefs prefs: prefPageInfosResult.getApps()) {
                output += prefs.getAppId().getAppShortName() + " (desc = " + prefs.getAppId().getAppLongName() + ")\n";
                for (TimeBlock block: prefs.getBlocks()) {
                    output += "\tstart : " + block.getStartTime().toString() + "\n";
                    output += "\tend   : " + block.getEndTime().toString() + "\n";
                    output += "\tchoice: " + block.getChoice().toString() + "\n\n";
                }
                output += "\n";
            }
            getView().getTextArea().setText(output);
        }
    });

    private  AsyncCallback<PrefSaveResult> savePrefsCallBack = new AsyncCallback<PrefSaveResult>() {
        @Override
        public void onFailure(Throwable throwable) {
            getView().getIsSavedLabel().setText("Failed to ask server to save preferences");
        }

        @Override
        public void onSuccess(PrefSaveResult prefSaveResult) {
            if(prefSaveResult.getSaveSuccessful()) {
                getView().getIsSavedLabel().setText("Save successfull");
            }
            else{
                getView().getIsSavedLabel().setText("Server couldn't save preferences");
            }
        }
    };
}
