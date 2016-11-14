package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

import com.google.gwt.user.cellview.client.Column;

/**
 * Created by Olivier on 6/7/2016.
 */
public abstract class PrefColumn extends Column<PrefTableRow, Boolean> {
    private boolean enabled; // to know if the checkboxes of a column are enabled or disabled

    public PrefColumn(PrefCheckboxCell cell) {
        super(cell);
        enabled = cell.isEnabled();
    }

    public boolean isEnabled() {
        return enabled;
    }
}
