
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutogrouppage.widgets.TutoWidget;

import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.Student;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.groupPage.TutoAppointment;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Heading;
import org.gwtbootstrap3.client.ui.constants.HeadingSize;
import org.gwtbootstrap3.client.ui.gwt.FlowPanel;

import java.util.ArrayList;

public class TutoMemberListWidgetView extends ViewImpl implements TutoMemberListWidgetPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, TutoMemberListWidgetView> {
    }

    public interface TutoMemberListWidgetCss extends CssResource {
        String rowStyleWhite();
        String rowStyleGreen();
        String fontSize();
//        String titleProf();
        String titleTuto();
    }

    @UiField TutoMemberListWidgetCss tutoMemberListWidgetCss;
    @UiField HTMLPanel panel;
    @UiField CellTable<Student> table;
    @UiField Grid tutoLabelsGrid;
    @UiField Button btnRandomize;

    public Button getBtnRandomize() {
        return btnRandomize;
    }

    @Inject
    TutoMemberListWidgetView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
    }

    public void showAllWidgets(){
        panel.setVisible(true);
    }

    public void hideAllWidgets(){
        panel.setVisible(false);
    }

    public CellTable<Student> getTable() {
        return table;
    }
    public TutoMemberListWidgetCss getCss() {return tutoMemberListWidgetCss;}

    public void setupLabelsTuto(ArrayList<TutoAppointment> tutoAppointments)
    {
        tutoLabelsGrid.resize(1,tutoAppointments.size());
        tutoLabelsGrid.setWidth("100%");

        for(int i = 0; i < tutoAppointments.size(); i++)
        {
            tutoLabelsGrid.setWidget(0, i, makeTutoLabelsLayout(tutoAppointments.get(i)));
        }
    }

    private FlowPanel makeTutoLabelsLayout(TutoAppointment tutoAppointment)
    {
        FlowPanel Tuto = new FlowPanel();

        Heading title = new Heading(HeadingSize.H3, tutoAppointment.getLabel() );
        title.setStyleName(getCss().titleTuto());

        DateTimeFormat dateFormat = DateTimeFormat.getFormat("EEE, MMM d, yyyy");

        FlowPanel datePanel = new FlowPanel();
        datePanel.add(new Heading(HeadingSize.H4,"Date : "));
        datePanel.add(new Heading(HeadingSize.H5,dateFormat.format(tutoAppointment.getStartTime())));

        DateTimeFormat timeFormat = DateTimeFormat.getFormat("h:mm a");

        FlowPanel timePanel = new FlowPanel();
        timePanel.add(new Heading(HeadingSize.H4,"Heure : "));
        timePanel.add(new Heading(HeadingSize.H5,timeFormat.format(tutoAppointment.getStartTime())));
        timePanel.add(new Heading(HeadingSize.H5," - "));
        timePanel.add(new Heading(HeadingSize.H5,timeFormat.format(tutoAppointment.getEndTime())));

        FlowPanel roomPanel = new FlowPanel();
        roomPanel.add(new Heading(HeadingSize.H4,"Local : "));
        roomPanel.add(new Heading(HeadingSize.H5,tutoAppointment.getRoomId()));

        Tuto.add(title);
        Tuto.add(datePanel);
        Tuto.add(timePanel);
        Tuto.add(roomPanel);

        return Tuto;
    }
}
