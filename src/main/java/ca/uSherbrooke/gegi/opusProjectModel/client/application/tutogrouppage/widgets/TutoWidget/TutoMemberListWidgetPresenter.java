
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget.TutoTabChangeEvent;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.AppWidget.TutoTabChangeEventHandler;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.Student;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TutoAppointment;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TutoGroup;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SelectionCell;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import org.gwtbootstrap3.client.ui.Button;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TutoMemberListWidgetPresenter extends PresenterWidget<TutoMemberListWidgetPresenter.MyView> {
    interface MyView extends View {
        CellTable<Student> getTable();
        TutoMemberListWidgetView.TutoMemberListWidgetCss getCss();
        void setupLabelsTuto(ArrayList<TutoAppointment> tutoAppointments);
        Button getBtnRandomize();
        void showAllWidgets();
        void hideAllWidgets();
    }

    private final String STUDENT_NAME_COLUMN_TITLE = "nom";
    private final String STUDENT_CIP_COLUMN_TITLE = "cip";
    private final String STUDENT_RESPONSABILITY_COLUMN_TITLE = "Responsabilité";
    private final String STUDENT_PRESENCE_COLUMN_TITLE = "Présence";
    private final String OPENING_TUTO_TITLE = "Tutorat d'ouverture";
    private final String CLOSING_TUTO_TITLE = "Tutorat de fermeture";

    private ArrayList<Student> rows = new ArrayList<Student>();

    CellTable<Student> table;

    TutoGroup tutogroup;

    @Inject
    TutoMemberListWidgetPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);
    }

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getEventBus().addHandler(TutoTabChangeEvent.TYPE, tabChangeEventHandler));
        table = getView().getTable();
        getView().getBtnRandomize().setVisible(false);
        getView().getBtnRandomize().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent clickEvent) {
                RandomizeResponsabilities();
            }
        });
    }

    void RandomizeResponsabilities(){
        int studentsQuantity = rows.size();
        Random randomizer = new Random();
        ArrayList<Integer> randomList = new ArrayList<Integer>();

        if(studentsQuantity < Student.Responsability.getQuantity()){
            Window.alert("Il n'y a pas assez d'étudiants pour assigner les responsabilités au hasard.");
            return;
        }

        //clear responsabilities:
        for( Student s : rows ){
            s.setResp(Student.Responsability.NONE);
        }

        for( Student.Responsability resp : Student.Responsability.values() ) {
            while(true) {
                int chosenStudent = randomizer.nextInt(studentsQuantity);
                if (rows.get(chosenStudent).getResp() == Student.Responsability.NONE) {
                    rows.get(chosenStudent).setResp(resp);
                    break;
                }
            }

        }

        table.redraw();

        PresenceOrRespoUpdatedEvent event = new PresenceOrRespoUpdatedEvent();
        getEventBus().fireEvent(event);
    }

    private final TutoTabChangeEventHandler tabChangeEventHandler = new TutoTabChangeEventHandler() {
        @Override
        public void onTutoTabChange(TutoTabChangeEvent event) {
            if(!event.areTheGroupsValid()){
                getView().hideAllWidgets(); // if the groups are not valid we don't want to show any widget
                return;
            }

            getView().showAllWidgets(); // make sure widgets are visible
            tutogroup = event.getTutoGroup();
            setRowsData(event.getTutoGroup().getStudents());

            // we must rebuild the table every time user change tab so we destroy it first
            while(table.getColumnCount() > 0){
                table.removeColumn(0);
            }

           if(event.isUserATeacher()) {
                buildTeachersTable();
            }
            else{
                buildStudentTable();
            }
            //buildTeachersTable();
            //buildStudentTable();

            getView().setupLabelsTuto(event.getTutoGroup().getTutoAppointments());
        }
    };

    private void setRowsData(ArrayList<Student> rows){
        this.rows = rows;
        table.setRowCount(rows.size());
        table.setRowData(0, this.rows);
        table.redrawHeaders();
        table.redrawFooters();
        table.redraw();
    }

    private void buildStudentTable(){
        //hide randomize button
        getView().getBtnRandomize().setVisible(false);

        TextColumn<Student> nameColumn = new TextColumn<Student>() {
            @Override
            public String getValue(Student student) {
                return student.getName();
            }
        };

        TextColumn<Student> cipColumn = new TextColumn<Student>() {
            @Override
            public String getValue(Student student) {
                return student.getCip();
            }
        };

        TextColumn<Student> responsabilityColumn = new TextColumn<Student>() {
            @Override
            public String getValue(Student student) {
                if(student.hasResponsability()) {
                    return student.getRespAsString();
                }
                else return "";
            }
        };

        table.addColumn(nameColumn, STUDENT_NAME_COLUMN_TITLE);
        table.addColumn(cipColumn, STUDENT_CIP_COLUMN_TITLE);
        table.addColumn(responsabilityColumn, STUDENT_RESPONSABILITY_COLUMN_TITLE);
        table.addStyleName(getView().getCss().fontSize());

        table.setRowCount(rows.size(), true);
        table.setRowData(0, rows);

        table.setRowStyles(new RowStyles<Student>() {
            @Override
            public String getStyleNames(Student student, int i) {
                if(i%2 != 0)
                    return getView().getCss().rowStyleGreen();
                else
                    return getView().getCss().rowStyleWhite();
            }
        });
    }
    private void buildTeachersTable(){
        //show randomize button
        getView().getBtnRandomize().setVisible(true);

        TextColumn<Student> nameColumn = new TextColumn<Student>() {
            @Override
            public String getValue(Student student) {
                return student.getName();
            }
        };

        TextColumn<Student> cipColumn = new TextColumn<Student>() {
            @Override
            public String getValue(Student student) {
                return student.getCip();
            }
        };

        int attendanceColumnsNumber = tutogroup.getTutoAppointments().size();
        List<AttendanceColumn> attendanceColumns = new ArrayList<AttendanceColumn>();
        //List<Column<Student,Boolean>> attendanceColumns = new ArrayList<Column<Student,Boolean>>();
        for(int i = 0; i < attendanceColumnsNumber; i++) {
            final int appointmentId = tutogroup.getTutoAppointments().get(i).getAppointmentId();
            final AttendanceColumn attendanceColumn = new AttendanceColumn(new CheckboxCell(false, false)) {
                @Override
                public Boolean getValue(Student student) {
                    return student.isPresent(appointmentId);
                }
            };
            attendanceColumn.setAppointmentId(appointmentId);
            attendanceColumn.setFieldUpdater(new FieldUpdater<Student, Boolean>() {
                @Override
                public void update(int i, Student student, Boolean aBoolean) {
                    // update presence hashmap
                    student.setPresenceEntry(attendanceColumn.getAppointmentId(), aBoolean);

                    // notify listening presenters that there was an update
                    PresenceOrRespoUpdatedEvent event = new PresenceOrRespoUpdatedEvent();
                    getEventBus().fireEvent(event);
                }
            });
            attendanceColumns.add(attendanceColumn);
        }

        SelectionCell responsabilityCell = new SelectionCell(Student.getAllResponsabilityStrings());
        Column<Student,String> responsabilityColumn = new Column<Student, String>(responsabilityCell) {
            @Override
            public String getValue(Student student) {
                return student.getRespAsString();
            }
        };

        responsabilityColumn.setFieldUpdater(new FieldUpdater<Student, String>() {
            @Override
            public void update(int i, Student student, String s) {
                student.setRespAsString(s);
                if(student.getResp() != Student.Responsability.NONE) {
                    for (Student otherstudent : rows) {
                        if (student.getResp() == otherstudent.getResp() && otherstudent != student) {
                            otherstudent.setResp(Student.Responsability.NONE);
                            table.redraw();
                        }
                    }

                }

                PresenceOrRespoUpdatedEvent event = new PresenceOrRespoUpdatedEvent();
                getEventBus().fireEvent(event);
            }
        });


        table.addColumn(nameColumn, STUDENT_NAME_COLUMN_TITLE);
        table.addColumn(cipColumn, STUDENT_CIP_COLUMN_TITLE);

        ArrayList<String> attendanceColumnsTitles = new ArrayList<>();
        if(attendanceColumns.size() == 1){
            attendanceColumnsTitles.add(OPENING_TUTO_TITLE);
        }
        else if(attendanceColumns.size() == 2){
            attendanceColumnsTitles.add(OPENING_TUTO_TITLE);
            attendanceColumnsTitles.add(CLOSING_TUTO_TITLE);
        }
        else if(attendanceColumns.size() > 2){
            for(int i = 0; i < attendanceColumns.size()-1; i++){
                attendanceColumnsTitles.add(OPENING_TUTO_TITLE + " " + (i+1));
            }
            attendanceColumnsTitles.add(CLOSING_TUTO_TITLE);
        }


        int columnIterator=0;
        for (Column<Student,Boolean> column : attendanceColumns){
            table.addColumn(column, attendanceColumnsTitles.get(columnIterator));
            columnIterator++;
        }

        table.addColumn(responsabilityColumn, STUDENT_RESPONSABILITY_COLUMN_TITLE);
        table.addStyleName(getView().getCss().fontSize());

        table.setRowCount(rows.size(), true);
        table.setRowData(0, rows);

        table.setRowStyles(new RowStyles<Student>() {
            @Override
            public String getStyleNames(Student student, int i) {
                if(i%2 != 0)
                    return getView().getCss().rowStyleGreen();
                else
                    return getView().getCss().rowStyleWhite();
            }
        });
    }
}
