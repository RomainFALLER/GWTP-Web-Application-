
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.Teacher;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.*;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import java.util.ArrayList;

public class TutoTabsWidgetView extends ViewImpl implements TutoTabsWidgetPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, TutoTabsWidgetView> {
    }

    public interface TutoTabsCss extends CssResource {
        String titleProf();
        String teacherFlowPanel();
        String saveButtonEnable();
        String saveButtonDisable();

        @ClassName("glyphicon-floppy-disk")
        String glyphiconFloppyDisk();
    }

    @UiField TutoTabsCss tutoTabsCss;
    @UiField NavTabs tutoSelectTabs;
    @UiField TabPane tutoTabContentPanel;
    @UiField Grid teachersLabelsGrid;
    @UiField Button saveButton;
    @UiField Heading saveConfirmLabel;

    @UiField
    HTMLPanel panel;
    Heading emptyGroupMsg;

    @Inject
    TutoTabsWidgetView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
        bindSlot(TutoTabsWidgetPresenter.TUTO_TAB_CONTENT_SLOT, tutoTabContentPanel);
        emptyGroupMsg = new Heading(HeadingSize.H1, "Les groupes de tutorat n'ont pas encore été générés pour cet APP.");
        emptyGroupMsg.setVisible(false);
        panel.add(emptyGroupMsg);
    }

    public TabListItem addTab(String tutoId, boolean setActive){
        TabListItem newTab = new TabListItem(tutoId);
        newTab.setDataTarget("tutoTabContentPanel");
        tutoSelectTabs.add(newTab);
        newTab.setActive(setActive);
        return newTab;
    }

    public void deleteExistingTabs(){
        tutoSelectTabs.clear();
    }

    public void setupTeachersLabels(ArrayList<Teacher> teachers){
        // if the list of teachers is empty, we warn the user
        if(teachers.size() < 1){
            teachersLabelsGrid.resize(1, 1);
            teachersLabelsGrid.setWidth("100%");

            teachersLabelsGrid.setWidget(0, 0, new Heading(HeadingSize.H4,"Les tuteurs n'ont pas encore été déterminé pour cet APP"));
        }
        else{
            teachersLabelsGrid.resize(1, teachers.size());
            teachersLabelsGrid.setWidth("100%");

            for(int i = 0; i < teachers.size(); i++){
                teachersLabelsGrid.setWidget(0, i, createTeacherLabelsPanel(teachers.get(i)));
            }
        }
    }

    public void showEmptyGroupLabel(){
        emptyGroupMsg.setVisible(true);
    }

    public void hideEmptyGroupLabel(){
        emptyGroupMsg.setVisible(false);
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Heading getSaveConfirmLabel() {
        return saveConfirmLabel;
    }

    public void disableSaveButton(){
        saveButton.setEnabled(false);
        saveButton.addStyleName(tutoTabsCss.saveButtonDisable());
        saveButton.removeStyleName(tutoTabsCss.saveButtonEnable());
    }

    public void enableSaveButton(){
        saveButton.setEnabled(true);
        saveButton.addStyleName(tutoTabsCss.saveButtonEnable());
        saveButton.removeStyleName(tutoTabsCss.saveButtonDisable());
    }

    private FlowPanel createTeacherLabelsPanel(Teacher teacher){
        FlowPanel flowPanel = new FlowPanel();
        flowPanel.setStyleName(getCss().teacherFlowPanel());

        FlowPanel namePanel = new FlowPanel();
        namePanel.add(new Heading(HeadingSize.H4,"Nom : "));
        namePanel.add(new Heading(HeadingSize.H5,teacher.getName()));

        FlowPanel emailPanel = new FlowPanel();
        emailPanel.add(new Heading(HeadingSize.H4,"Courriel : "));
        emailPanel.add(new Heading(HeadingSize.H5,teacher.getEmailAdd()));

//        FlowPanel officePanel = new FlowPanel();
//        officePanel.add(new Heading(HeadingSize.H4,"Bureau : "));
//        officePanel.add(new Heading(HeadingSize.H5,teacher.getOffice()));

        flowPanel.add(namePanel);
        flowPanel.add(emailPanel);
//        flowPanel.add(officePanel);

        return flowPanel;
    }

    private TutoTabsCss getCss() {
        return tutoTabsCss;
    }
}
