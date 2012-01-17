package org.vaadin.jefferson.demo.simpleaddressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;

import com.vaadin.data.Container;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Table;

public final class ContactList extends View<Table> {
    private Container data;
    private ValueChangeListener valueChangeListener;

    ContactList(Container data, ValueChangeListener valueChangeListener) {
        super("Contact list", Table.class);
        this.data = data;
        this.valueChangeListener = valueChangeListener;
    }

    @Override
    public Table createFallback() {
        return new Table();
    }

    @Override
    protected Table accept(Presentation p) {
        final Table rendition = super.accept(p);
        rendition.setContainerDataSource(data);
        rendition.setVisibleColumns(
                new String[] { SimpleAddressBook.LAST_NAME,
                        SimpleAddressBook.FIRST_NAME,
                        SimpleAddressBook.COMPANY });
        rendition.setSelectable(true);
        rendition.setImmediate(true);

        rendition.addListener(valueChangeListener);
        return rendition;
    }
}