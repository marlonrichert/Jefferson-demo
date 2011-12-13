package org.vaadin.jefferson.demo.addressbook.ui;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.demo.addressbook.data.Person;
import org.vaadin.jefferson.demo.addressbook.data.PersonContainer;

import com.vaadin.data.Container;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class PersonList extends View<Table> {
    private Container dataSource;

    public PersonList(String name, Container dataSource) {
        super(name, Table.class);
        this.dataSource = dataSource;
    }

    @Override
    protected void update(Table component, Presentation presentation) {
        super.update(component, presentation);

        component.setContainerDataSource(dataSource);

        component.setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
        component.setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);

        component.setSelectable(true);
        component.setImmediate(true);

        component.setNullSelectionAllowed(false);

        component.addGeneratedColumn("email", new EmailColumn());
    }

    private final static class EmailColumn implements ColumnGenerator {
        public Component generateCell(Table source, Object itemId,
                Object columnId) {
            Person person = (Person) itemId;
            Link cell = new Link();
            cell.setResource(new ExternalResource("mailto:" + person.getEmail()));
            cell.setCaption(person.getEmail());
            return cell;
        }
    }
}