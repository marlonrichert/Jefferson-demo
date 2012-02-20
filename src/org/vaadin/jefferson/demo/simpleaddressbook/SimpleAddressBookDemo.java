package org.vaadin.jefferson.demo.simpleaddressbook;

import org.vaadin.jefferson.demo.simpleaddressbook.content.SimpleAddressBookView;
import org.vaadin.jefferson.presentation.SmartPresentation;
import org.vaadin.jefferson.presentation.SmartPresentation.Orientation;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class SimpleAddressBookDemo extends Application {

    @Override
    public void init() {
        setMainWindow(new Window("Address Book",
                new SmartPresentation(Orientation.VERTICAL).visit(
                        new SimpleAddressBookView())));
    }
}
