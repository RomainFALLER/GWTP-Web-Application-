/*
 * Copyright 2015, 2016 Département de Génie Électrique et Génie Informatique (GEGI) de l'Université de Sherbrooke (UdeS).
 * Tous droits réservés / All rights reserved.
 */

package ca.uSherbrooke.gegi.opusProjectModel.client.application.home;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.gwtplatform.mvp.client.ViewImpl;
import org.gwtbootstrap3.client.ui.Button;

public class HomePageView extends ViewImpl implements HomePagePresenter.MyView {

    private final Widget widget;

    public interface Binder extends UiBinder<Widget, HomePageView> {
    }

    public interface HomeCss extends CssResource {
    }

    @UiField Button btnPref;
    @UiField Button btnTuto;

    @Inject
    public HomePageView(final Binder binder) {
        widget = binder.createAndBindUi(this);
    }

    @Override
    public Widget asWidget() {
        return widget;
    }

    public Button getBtnPref() {return btnPref;}
    public Button getBtnTuto() {return btnTuto;}
}