package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

/**
 * Created by Olivier on 6/4/2016.
 */
public class PrefCheckboxCell extends CheckboxCell {

    //-------------------------  CODE COPIED DIRECTLY FROM CheckboxCell CLASS -----------------------
    private static final SafeHtml INPUT_CHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked/>");
    private static final SafeHtml INPUT_UNCHECKED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\"/>");
    //-----------------------------------------------------------------------------------------------

    private static final SafeHtml INPUT_CHECKED_DISABLED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" checked disabled=\"disabled\"/>");
    private static final SafeHtml INPUT_UNCHECKED_DISABLED = SafeHtmlUtils.fromSafeConstant("<input type=\"checkbox\" tabindex=\"-1\" disabled=\"disabled\"/>");

    CellTable<PrefTableRow> table;
    boolean enabled = true;

    public PrefCheckboxCell(boolean dependsOnSelection, boolean handlesSelection, CellTable<PrefTableRow> cellOwner){
        super(dependsOnSelection, handlesSelection);
        table = cellOwner;
    }

    @Override
    public void onBrowserEvent(Context context, Element parent, Boolean value, NativeEvent event, ValueUpdater<Boolean> valueUpdater) {
        super.onBrowserEvent(context, parent, value, event, valueUpdater);
        table.redraw();
    }

    @Override
    public void render(Context context, Boolean value, SafeHtmlBuilder sb) {

        //-------------------------  CODE COPIED DIRECTLY FROM CheckboxCell CLASS -----------------------
        Object key = context.getKey();
        Boolean viewData = (Boolean)this.getViewData(key);
        if(viewData != null && viewData.equals(value)) {
            this.clearViewData(key);
            viewData = null;
        }
        //-----------------------------------------------------------------------------------------------

        if(!enabled){
            if(value != null && (viewData != null?viewData:value).booleanValue()) {
                sb.append(INPUT_CHECKED_DISABLED);
            } else {
                sb.append(INPUT_UNCHECKED_DISABLED);
            }
        }
        else{
            if(value != null && (viewData != null?viewData:value).booleanValue()) {
                sb.append(INPUT_CHECKED);
            } else {
                sb.append(INPUT_UNCHECKED);
            }
        }

    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
