package org.vaadin.jefferson.demo.simpleaddressbook;

import org.vaadin.jefferson.demo.simpleaddressbook.content.*;
import org.vaadin.jefferson.presentation.*;
import org.vaadin.jefferson.presentation.SmartPresentation.Orientation;

import com.vaadin.terminal.*;
import com.vaadin.ui.*;

public class SimpleAddressBookDemo extends Root {

    @Override
    protected void init(WrappedRequest request) {
        setCaption("Simple Address Book demo");
        setContent(new SmartPresentation(Orientation.HORIZONTAL).visit(
                new SimpleAddressBookView()));
    }
}
