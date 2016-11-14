package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.PrefPageCom;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;


public class PrefComTestView extends ViewImpl implements PrefComTestPresenter.MyView {
    interface Binder extends UiBinder<Widget, PrefComTestView> {
    }

    @UiField
    TextArea textArea;

    @UiField
    Button saveButton;

    @UiField
    Label isSavedLabel;

    @Inject
    PrefComTestView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public TextArea getTextArea() {
        return textArea;
    }

    public Button getSaveButton() {
        return saveButton;
    }

    public Label getIsSavedLabel() {
        return isSavedLabel;
    }
}
