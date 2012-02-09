package org.vaadin.jefferson.demo.simpleaddressbook;

import org.vaadin.jefferson.demo.simpleaddressbook.content.SimpleAddressBookView;
import org.vaadin.jefferson.presentation.RADPresentation;
import org.vaadin.jefferson.presentation.RADPresentation.Orientation;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class SimpleAddressBookDemo extends Application {

    @Override
    public void init() {
        setMainWindow(new Window("Address Book",
                new RADPresentation(Orientation.HORIZONTAL).visit(
                        new SimpleAddressBookView())));
    }
}
