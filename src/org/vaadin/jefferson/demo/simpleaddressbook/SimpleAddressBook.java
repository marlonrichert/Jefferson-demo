package org.vaadin.jefferson.demo.simpleaddressbook;

import org.vaadin.jefferson.Presentation;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class SimpleAddressBook extends Application {

    @Override
    public void init() {
        setMainWindow(new Window("Address Book",
                new Presentation().visit(new ContentRoot())));
    }
}
