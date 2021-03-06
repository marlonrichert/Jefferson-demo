package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.demo.addressbook.domain.Person;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;

import com.vaadin.data.Container;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class PersonList extends SelectionControl<Person> {
    private Container dataSource;

    public PersonList(AddressBookView root) {
        super("Person list", Person.class);
        dataSource = root.getDataSource();
    }

    @Override
    public Table createFallback() {
        return new Table();
    }

    @Override
    protected AbstractSelect accept(Presentation presentation) {
        AbstractSelect rendition = super.accept(presentation);

        rendition.setContainerDataSource(dataSource);
        rendition.setNullSelectionAllowed(false);

        if (rendition instanceof Table) {
            Table table = (Table) rendition;
            table.setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
            table.setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);
            table.addGeneratedColumn("email", new EmailColumn());
        }

        return rendition;
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