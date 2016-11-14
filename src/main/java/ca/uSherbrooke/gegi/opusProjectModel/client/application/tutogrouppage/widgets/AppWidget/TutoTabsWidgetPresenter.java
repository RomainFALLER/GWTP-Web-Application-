
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.TutoGroupAppTabChangeEvent;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.TutoGroupAppTabChangeEventHandler;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget.PresenceOrRespoUpdatedEvent;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget.PresenceOrRespoUpdatedHandler;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget.TutoMemberListWidgetPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.presenter.slots.Slot;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.TabListItem;

import java.util.ArrayList;

public class TutoTabsWidgetPresenter extends PresenterWidget<TutoTabsWidgetPresenter.MyView> {
    interface MyView extends View {
        TabListItem addTab(String tutoId, boolean setActive);
        void deleteExistingTabs();
        void setupTeachersLabels(ArrayList<Teacher> teachers);
        void showEmptyGroupLabel();
        void hideEmptyGroupLabel();
        Button getSaveButton();
        Heading getSaveConfirmLabel();
        void disableSaveButton();
        void enableSaveButton();
    }

    public static final Slot TUTO_TAB_CONTENT_SLOT = new Slot();
    private AppTutos activeTabApp = new AppTutos();

    @Inject
    TutoMemberListWidgetPresenter memberListWidget;
    @Inject
    DispatchAsync dispatchAsync;

    @Inject
    TutoTabsWidgetPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

    }

    @Override
    protected void onBind() {
        super.onBind();
        setInSlot(TUTO_TAB_CONTENT_SLOT, memberListWidget);
        registerHandler(getEventBus().addHandler(TutoGroupAppTabChangeEvent.TYPE,appTabChangeHandler));
        registerHandler(getEventBus().addHandler(PresenceOrRespoUpdatedEvent.TYPE, updateHandler));
        getView().getSaveButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                GroupPageSaveAction action = new GroupPageSaveAction();
                action.setApp(activeTabApp);
                dispatchAsync.execute(action, saveCallback);
            }
        });
    }

    private final TutoGroupAppTabChangeEventHandler appTabChangeHandler = new TutoGroupAppTabChangeEventHandler(){

        @Override
        public void onTutoGroupAppTabChange(final TutoGroupAppTabChangeEvent event) {
            TabListItem tab;

            getView().deleteExistingTabs(); // clear all existing tabs to replace them with the correct ones
            getView().hideEmptyGroupLabel(); // make sure the label is hidden

            activeTabApp = event.getApp();
            getView().setupTeachersLabels(activeTabApp.getTeachers());

            getView().getSaveConfirmLabel().setVisible(false);

            if(activeTabApp.getAppID().isTeacher()){
                getView().getSaveButton().setVisible(true);
            }
            else {
                getView().getSaveButton().setVisible(false);
            }

            if(activeTabApp.getValidGroups()){
                for(final TutoGroup group: activeTabApp.getTutos()){
                    // first tab is always the active one
                    if(activeTabApp.getTutos().indexOf(group) == 0){
                        tab = getView().addTab(group.getTutoId(), true);
                        TutoTabChangeEvent evt = new TutoTabChangeEvent();
                        evt.setUserATeacher(activeTabApp.getAppID().isTeacher());
                        evt.setTutoGroup(group);
                        evt.setTeachers(activeTabApp.getTeachers());
                        getEventBus().fireEvent(evt);
                    }
                    else{
                        tab = getView().addTab(group.getTutoId(), false);
                    }

                    tab.addClickHandler(new ClickHandler() {
                        @Override
                        public void onClick(ClickEvent clickEvent) {
                            TutoTabChangeEvent evt = new TutoTabChangeEvent();
                            evt.setTutoGroup(group);
                            evt.setUserATeacher(activeTabApp.getAppID().isTeacher());
                            evt.setTeachers(activeTabApp.getTeachers());
                            getEventBus().fireEvent(evt);
                        }
                    });
                }
            }
            else {
                getView().showEmptyGroupLabel();
                TutoTabChangeEvent evt = new TutoTabChangeEvent();
                evt.setValidGroupsFlag(false);
                getEventBus().fireEvent(evt);
            }
        }
    };

    private AsyncCallback<GroupPageSaveResult> saveCallback = new AsyncCallback<GroupPageSaveResult>() {
        @Override
        public void onFailure(Throwable throwable) {
            Window.alert("L'application n'a pas été en mesure de communiquer avec le serveur pour faire une demande de sauvegarde");
        }

        @Override
        public void onSuccess(GroupPageSaveResult groupPageSaveResult) {
            if(groupPageSaveResult.isSaveSuccess()){
                getView().disableSaveButton();
                getView().getSaveConfirmLabel().setVisible(true);
            }
            else {
                Window.alert("Le serveur n'a pas été en mesure de sauvegarder les informations.");
            }
        }
    };

    private PresenceOrRespoUpdatedHandler updateHandler = new PresenceOrRespoUpdatedHandler() {
        @Override
        public void onPresenceOrRespoUpdated(PresenceOrRespoUpdatedEvent event) {
            getView().enableSaveButton();
            getView().getSaveConfirmLabel().setVisible(false);
        }
    };

}
