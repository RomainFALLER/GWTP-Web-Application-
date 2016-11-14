package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.GroupPageCom;

import ca.uSherbrooke.gegi.commons.core.client.presenter.application.ApplicationPresenter;
import ca.uSherbrooke.gegi.opusProjectModel.client.place.NameTokens;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.*;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.TextArea;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rpc.shared.DispatchAsync;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.proxy.ManualRevealCallback;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;

public class GroupComTestPresenter extends Presenter<GroupComTestPresenter.MyView, GroupComTestPresenter.MyProxy> {
    interface MyView extends View {
        TextArea getTextArea();
        Button getTestButton();
    }

    @NameToken(NameTokens.groupcomtest)
    @ProxyCodeSplit
    interface MyProxy extends ProxyPlace<GroupComTestPresenter> {
    }

    public static final NestedSlot SLOT_GROUPCOMTEST = new NestedSlot();

    @Inject
    DispatchAsync dispatchAsync;

    @Inject
    GroupComTestPresenter(
            EventBus eventBus,
            MyView view,
            MyProxy proxy) {
        super(eventBus, view, proxy, ApplicationPresenter.SLOT_CONTENT);
    }

    @Override
    protected void onBind() {
        super.onBind();
        getView().getTestButton().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                TestAlgoAction algoAction = new TestAlgoAction();
                dispatchAsync.execute(algoAction, new AsyncCallback<TestAlgoResult>() {
                    @Override
                    public void onFailure(Throwable throwable) {
                        Window.alert("failed to communicate with server");
                    }

                    @Override
                    public void onSuccess(TestAlgoResult testAlgoResult) {
                        Window.alert("Le serveur a lancé la génération des groupes.");
                    }
                });
            }
        });
    }

    @Override
    public boolean useManualReveal() {
        return true;
    }

    @Override
    public void prepareFromRequest(PlaceRequest request) {
        super.prepareFromRequest(request);
        GroupPageInfosAction action = new GroupPageInfosAction();
        dispatchAsync.execute(action, infosCallBack);
    }

    private AsyncCallback<GroupPageInfosResult> infosCallBack = ManualRevealCallback.create(this, new AsyncCallback<GroupPageInfosResult>() {
        @Override
        public void onFailure(Throwable throwable) {
            getView().getTextArea().setText("Failed to retrieve infos from server");
        }

        @Override
        public void onSuccess(GroupPageInfosResult groupPageInfosResult) {
            String output = "";
            for(AppTutos app: groupPageInfosResult.getApps()){
                output += app.getAppID().getAppShortName() + ": " + app.getAppID().getAppLongName() + "\n";
                output += "professeurs: \n";
                for(Teacher teacher: app.getTeachers()){
                    output += "\t" + teacher.getName() + ", " + teacher.getEmailAdd() + "\n";
                }
                output += "tutorats: \n";
                for(TutoGroup group: app.getTutos()){
                    output += "\ttutorat: " + group.getTutoId() + "\n";
                    output += "\trendez-vous:\n";
                    int i = 0;
                    for(TutoAppointment appointment : group.getTutoAppointments()){
                        output += "\t\ttutorat " + i + ": local = " + appointment.getRoomId() + ", heure = " + appointment.getStartTime().toString() + "\n";
                        i++;
                    }
                    output += "\tliste d'étudiants:\n";
                    for(Student student: group.getStudents()){
                        output += "\t\t" + student.getName() + "(cip: " + student.getCip() + "), responsabilité: " + student.getResp().toString() + "\n";
                    }
                    output += "\n\n";
                }
            }
            getView().getTextArea().setText(output);
        }
    });
}
