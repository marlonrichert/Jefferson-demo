package org.vaadin.jefferson.demo.addressbook;

import org.vaadin.jefferson.demo.addressbook.content.*;
import org.vaadin.jefferson.demo.addressbook.domain.*;
import org.vaadin.jefferson.presentation.*;
import org.vaadin.jefferson.presentation.SmartPresentation.Orientation;

import com.vaadin.terminal.*;
import com.vaadin.ui.*;

@SuppressWarnings("serial")
public class AddressBookDemo extends Root {
    private PersonContainer dataSource = PersonContainer.createWithTestData();

    @Override
    protected void init(WrappedRequest request) {
        setCaption("Address Book demo");
        setContent(new SmartPresentation(Orientation.VERTICAL).visit(
                new AddressBookView(dataSource)));
    }
}
