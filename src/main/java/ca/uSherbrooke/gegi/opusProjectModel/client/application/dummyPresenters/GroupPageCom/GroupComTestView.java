package ca.uSherbrooke.gegi.opusProjectModel.client.application.dummyPresenters.GroupPageCom;

import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewImpl;

import javax.inject.Inject;


public class GroupComTestView extends ViewImpl implements GroupComTestPresenter.MyView {
    interface Binder extends UiBinder<Widget, GroupComTestView> {
    }

    @UiField
    TextArea textArea;
    @UiField
    Button testButton;

    @Inject
    GroupComTestView(Binder uiBinder) {
        initWidget(uiBinder.createAndBindUi(this));

    }

    @Override
    public TextArea getTextArea() {
        return textArea;
    }

    public Button getTestButton() {
        return testButton;
    }
}
