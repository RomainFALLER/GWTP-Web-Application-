
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.TutoPrefTabChange;
import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.TutoPrefTabChangeHandler;
import ca.uSherbrooke.gegi.opusProjectModel.shared.dispatch.prefPage.TimeBlock;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.user.cellview.client.RowStyles;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import org.gwtbootstrap3.client.ui.gwt.CellTable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CellPrefPresenter extends PresenterWidget<CellPrefPresenter.MyView> {
    public interface MyView extends View {
        CellTable<PrefTableRow> getTable();
        CellPrefView.CellPrefCss getCss();
    }

    private enum ColumnID {
        SCHEDULE,
        FIRST_CHOICE,
        SECOND_CHOICE,
        THIRD_CHOICE
    }

    private final FieldUpdater<PrefTableRow, Boolean> pref1FieldUpdater = new FieldUpdater<PrefTableRow, Boolean>() {
        @Override
        public void update(int i, PrefTableRow prefTableRow, Boolean aBoolean) {
            updatePrefs(i, PrefTableRow.PrefId.PREF1, aBoolean);
        }
    };

    private final FieldUpdater<PrefTableRow, Boolean> pref2FieldUpdater = new FieldUpdater<PrefTableRow, Boolean>() {
        @Override
        public void update(int i, PrefTableRow prefTableRow, Boolean aBoolean) {
            updatePrefs(i, PrefTableRow.PrefId.PREF2, aBoolean);
        }
    };

    private final FieldUpdater<PrefTableRow, Boolean> pref3FieldUpdater = new FieldUpdater<PrefTableRow, Boolean>() {
        @Override
        public void update(int i, PrefTableRow prefTableRow, Boolean aBoolean) {
            updatePrefs(i, PrefTableRow.PrefId.PREF3, aBoolean);
        }
    };

    private final String SCHEDULE_COLUMN_TITLE = "Horaires";
    private final String FIRST_CHOICE_COLUMN_TITLE = "Premier choix";
    private final String SECOND_CHOICE_COLUMN_TITLE = "Deuxième choix";
    private final String THIRD_CHOICE_COLUMN_TITLE = "Troisième choix";

    // The list of data to display.
    List<PrefTableRow> rows = new ArrayList<PrefTableRow>();

    CellTable<PrefTableRow> table;

    @Override
    protected void onBind() {
        super.onBind();
        registerHandler(getEventBus().addHandler(TutoPrefTabChange.TYPE,tutoPrefTabChangeHandler));
        table = getView().getTable();
        buildPrefTable();
    }

    @Inject
    CellPrefPresenter(EventBus eventBus, MyView view) {
        super(eventBus, view);

    }

    public void setPrefTable(List<TimeBlock> timeBlocks)
    {
        List<PrefTableRow> rows = new ArrayList<PrefTableRow>();
        for (TimeBlock block: timeBlocks)
        {
            rows.add(convertTimeBlockToPrefTableRow(block));
        }

        table.setRowCount(rows.size());
        setRowsData(rows);
    }

    private final TutoPrefTabChangeHandler tutoPrefTabChangeHandler = new TutoPrefTabChangeHandler() {
        @Override
        public void onTutoPrefTabChange(TutoPrefTabChange event) {
            setPrefTable(event.getTimeBlock());

            if(event.getTimeLockPref().before(new Date()))
               disableColumn(ColumnID.FIRST_CHOICE);
            else
                enableColumn(ColumnID.FIRST_CHOICE);
        }
    };

    private PrefTableRow convertTimeBlockToPrefTableRow(TimeBlock timeBlock)
    {
        PrefTableRow row = new PrefTableRow(parseTimeStamp(timeBlock.getStartTime()) + " - " + parseTimeStamp(timeBlock.getEndTime()),false,false,false);
        TimeBlock.BlockChoice choice = timeBlock.getChoice();

        if(choice == TimeBlock.BlockChoice.FIRST_CHOICE)
        {
            row.setPref1(true);
        }
        else if(choice == TimeBlock.BlockChoice.SECOND_CHOICE)
        {
            row.setPref2(true);
        }
        else if(choice == TimeBlock.BlockChoice.THIRD_CHOICE)
        {
            row.setPref3(true);
        }
        return row;
    }

    private String parseTimeStamp(Date time)
    {
        // A custom date format
        DateTimeFormat fmt = DateTimeFormat.getFormat("hh: mm: ss a");
        // prints Monday, December 17, 2007 in the default locale
        GWT.log(fmt.format(time), null);
        String hour = fmt.format(time).toString();
        return hour;
    }

    private void setRowsData(List<PrefTableRow> rows)
    {
        // first we got to set the new data
        this.rows = rows;
        table.setRowData(0, this.rows);

        // enable or disable columns depending on current choices
        boolean firstChoiceSelected = false;
        boolean secondChoiceSelected = false;

        for(PrefTableRow row : rows){
            if(row.getPref1() == true){
                firstChoiceSelected = true;
            }
            if(row.getPref2() == true){
                secondChoiceSelected = true;
            }
        }

        if(!firstChoiceSelected && !secondChoiceSelected){
            disableColumn(ColumnID.SECOND_CHOICE);
        }
        else if(firstChoiceSelected && !secondChoiceSelected){
            enableColumn(ColumnID.SECOND_CHOICE);
            disableColumn(ColumnID.THIRD_CHOICE);
        }
        else if(firstChoiceSelected && secondChoiceSelected){
            enableColumn(ColumnID.THIRD_CHOICE);
        }
        else{ // (!firstChoiceSelected && secondChoiceSelected)
            // shouldn't happen
        }

        // redraw the table to show new data to the user
        table.redrawFooters();
        table.redrawHeaders();
        table.redraw();
    }

    private void buildPrefTable() {
        // Add the columns.
        table.addStyleName(getView().getCss().fontSize());
        table.addColumn(this.scheduleColumn, SCHEDULE_COLUMN_TITLE);
        table.getHeader(0).setHeaderStyleNames(getView().getCss().glyphiconTime());

        insertChoiceColumn(ColumnID.FIRST_CHOICE, true);
        insertChoiceColumn(ColumnID.SECOND_CHOICE, true);
        insertChoiceColumn(ColumnID.THIRD_CHOICE, true);

        // Set the total row count. This isn't strictly necessary, but it affects
        // paging calculations, so its good habit to keep the row count up to date.
        table.setRowCount(rows.size(), true);

        // Push the data into the widget.
        table.setRowData(0, rows);

        table.setRowStyles(new RowStyles<PrefTableRow>() {
            @Override
            public String getStyleNames(PrefTableRow prefTableRow, int i) {
                if(i%2 != 0)
                    return getView().getCss().rowStyleGreen();
                else
                    return getView().getCss().rowStyleWhite();
            }
        });

    }

    // This method will disable the checkboxes of the specified column and all the ones after that by design. Note that
    // it will rebuild each column that needs to be disabled.
    private void disableColumn(ColumnID columnID){
        PrefColumn col;
        switch (columnID){
            case FIRST_CHOICE:
                col = (PrefColumn)table.getColumn(ColumnID.FIRST_CHOICE.ordinal());
                if(col.isEnabled()) {
                    removeChoiceColumn(ColumnID.FIRST_CHOICE);
                    insertChoiceColumn(ColumnID.FIRST_CHOICE, false);
                }
            case SECOND_CHOICE:
                col = (PrefColumn)table.getColumn(ColumnID.SECOND_CHOICE.ordinal());
                if(col.isEnabled()) {
                    removeChoiceColumn(ColumnID.SECOND_CHOICE);
                    insertChoiceColumn(ColumnID.SECOND_CHOICE, false);
                }
            case THIRD_CHOICE:
                col = (PrefColumn)table.getColumn(ColumnID.THIRD_CHOICE.ordinal());
                if(col.isEnabled()) {
                    removeChoiceColumn(ColumnID.THIRD_CHOICE);
                    insertChoiceColumn(ColumnID.THIRD_CHOICE, false);
                }
                break;
        }
    }

    // This method will enable the checkboxes of the specified column as well as all the previous choice columns by design.
    // Note that for that it needs to rebuild all affected columns.
    private void enableColumn(ColumnID columnID){
        PrefColumn col;
        switch (columnID){
            case THIRD_CHOICE:
                col = (PrefColumn)table.getColumn(ColumnID.THIRD_CHOICE.ordinal());
                if(!col.isEnabled()){
                    removeChoiceColumn(ColumnID.THIRD_CHOICE);
                    insertChoiceColumn(ColumnID.THIRD_CHOICE, true);
                }
            case SECOND_CHOICE:
                col = (PrefColumn)table.getColumn(ColumnID.SECOND_CHOICE.ordinal());
                if(!col.isEnabled()) {
                    removeChoiceColumn(ColumnID.SECOND_CHOICE);
                    insertChoiceColumn(ColumnID.SECOND_CHOICE, true);
                }
            case FIRST_CHOICE:
                col = (PrefColumn)table.getColumn(ColumnID.FIRST_CHOICE.ordinal());
                if(!col.isEnabled()) {
                    removeChoiceColumn(ColumnID.FIRST_CHOICE);
                    insertChoiceColumn(ColumnID.FIRST_CHOICE, true);
                }
                break;
        }
    }

    private void removeChoiceColumn(ColumnID columnID){
        // we can only remove one column at a time and must replace it before removing another because we want the table
        // to always have 4 columns in total. If it's not the case and we remove a column, we would remove the wrong one.
        if(table.getColumnCount() > 3){
            table.removeColumn(columnID.ordinal());
        }
    }

    private void insertChoiceColumn(ColumnID columnID, boolean enableCheckboxes){
        // The table must have 4 columns so it makes no sense to insert one if it already has 4.
        int nbOfColumns = table.getColumnCount();
        if(nbOfColumns <= 3){
            PrefCheckboxCell cell = new PrefCheckboxCell(true, false, table);
            cell.setEnabled(enableCheckboxes);
            switch (columnID){
                case FIRST_CHOICE:
                    if(nbOfColumns > 0) {
                        PrefColumn newColumn = new PrefColumn(cell) {
                            @Override
                            public Boolean getValue(PrefTableRow prefTableRow) {
                                return prefTableRow.getPref1();
                            }
                        };
                        newColumn.setFieldUpdater(pref1FieldUpdater);
                        table.insertColumn(1, newColumn, FIRST_CHOICE_COLUMN_TITLE);
                    }
                    break;
                case SECOND_CHOICE:
                    if(nbOfColumns > 1){
                        PrefColumn newColumn = new PrefColumn(cell) {
                            @Override
                            public Boolean getValue(PrefTableRow prefTableRow) {
                                return prefTableRow.getPref2();
                            }
                        };
                        newColumn.setFieldUpdater(pref2FieldUpdater);
                        table.insertColumn(2, newColumn, SECOND_CHOICE_COLUMN_TITLE);
                    }
                    break;
                case THIRD_CHOICE:
                    if(nbOfColumns > 2) {
                        PrefColumn newColumn = new PrefColumn(cell) {
                            @Override
                            public Boolean getValue(PrefTableRow prefTableRow) {
                                return prefTableRow.getPref3();
                            }
                        };
                        newColumn.setFieldUpdater(pref3FieldUpdater);
                        table.insertColumn(3, newColumn, THIRD_CHOICE_COLUMN_TITLE);
                    }
                    break;
            }
        }
    }

    private TextColumn<PrefTableRow> scheduleColumn = new TextColumn<PrefTableRow>() {
        @Override
        public String getValue(PrefTableRow prefTableRow) {
            return prefTableRow.getTimeRangeStr();
        }
    };

    private void updatePrefs(int rowIndex, PrefTableRow.PrefId prefId, boolean newValue){
        PrefTableRow row = rows.get(rowIndex);
        switch (prefId){
            case PREF1:
                if(newValue == true){
                    resetPref1();
                    row.setPref1(newValue);
                    enableColumn(ColumnID.SECOND_CHOICE);
                }
                else {
                    row.setPref1(newValue);
                    resetPref2();
                    resetPref3();
                    disableColumn(ColumnID.SECOND_CHOICE);
                }
                break;
            case PREF2:
                if(newValue == true){
                    boolean doCheckNextAvail = false;
                    resetPref2();
                    if(row.getPref1()){
                        doCheckNextAvail = true;
                    }
                    row.setPref2(newValue);
                    enableColumn(ColumnID.THIRD_CHOICE);
                    if(doCheckNextAvail){
                        checkNextAvailableBlockPref1();
                    }
                }
                else {
                    rows.get(rowIndex).setPref2(newValue);
                    resetPref3();
                    disableColumn(ColumnID.THIRD_CHOICE);
                }
                break;
            case PREF3:
                boolean doCheckNextAvailPref1 = false;
                boolean doCheckNextAvailPref2 = false;
                if(newValue == true){
                    resetPref3();
                    if(row.getPref1()){
                        doCheckNextAvailPref1 = true;
                    }
                    else if(row.getPref2()){
                        doCheckNextAvailPref2 = true;
                    }
                }
                row.setPref3(newValue);

                if(doCheckNextAvailPref1){
                    checkNextAvailableBlockPref1();
                }
                else if(doCheckNextAvailPref2){
                    checkNextAvailableBlockPref2();
                }
                break;
        }
        fireChoiceUpdate();
    }

    // uncheck all checkboxes in the first choice column
    private void resetPref1(){
        for(PrefTableRow row : rows){
            row.setPref1(false);
        }
    }

    // uncheck all checkboxes in the second choice column
    private void resetPref2(){
        for(PrefTableRow row : rows){
            row.setPref2(false);
        }
    }

    // uncheck all checkboxes in the third choice column
    private void resetPref3(){
        for(PrefTableRow row : rows){
            row.setPref3(false);
        }
    }

    private void checkNextAvailableBlockPref1(){
        for(PrefTableRow row: rows){
            if(!row.getPref2() && !row.getPref3()){
                row.setPref1(true);
                return;
            }
        }
    }

    private void checkNextAvailableBlockPref2(){
        for(PrefTableRow row: rows){
            if(!row.getPref1() && !row.getPref3()){
                row.setPref2(true);
                return;
            }
        }
    }

    private void fireChoiceUpdate(){
        ChoiceUpdate evt = new ChoiceUpdate();
        for(PrefTableRow row : rows){
            if(row.getPref1()){
                evt.addNewChoice(TimeBlock.BlockChoice.FIRST_CHOICE);
            }
            else if(row.getPref2()){
                evt.addNewChoice(TimeBlock.BlockChoice.SECOND_CHOICE);
            }
            else if(row.getPref3()){
                evt.addNewChoice(TimeBlock.BlockChoice.THIRD_CHOICE);
            }
            else {
                evt.addNewChoice(TimeBlock.BlockChoice.NO_CHOICE);
            }
        }
        getEventBus().fireEvent(evt);
    }
}
