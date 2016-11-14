
package ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.CellPrefWidget;

import ca.uSherbrooke.gegi.opusProjectModel.client.application.tutoprefpage.TutoPrefPagePresenter;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.gwt.CellTable;

public class CellPrefView extends ViewImpl implements CellPrefPresenter.MyView {
    interface Binder extends UiBinder<HTMLPanel, CellPrefView> {
    }

    public interface CellPrefCss extends CssResource {
        String glyphiconTime();
        String rowStyleWhite();
        String rowStyleGreen();
        String fontSize();
    }

    @UiField  CellPrefCss tutoCellPrefCss;

    @UiField HTMLPanel panel;
    @UiField CellTable<PrefTableRow> table;

    @Inject
    CellPrefView(Binder binder) {
        initWidget(binder.createAndBindUi(this));
        bindSlot(TutoPrefPagePresenter.SLOT_APP_TAB_CONTENT, panel);
    }

    public CellPrefCss getCss() {return tutoCellPrefCss;}
    public CellTable<PrefTableRow> getTable() {
        return table;
    }
}
