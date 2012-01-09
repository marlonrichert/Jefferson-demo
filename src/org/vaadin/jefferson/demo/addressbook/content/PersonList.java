package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.demo.addressbook.domain.Person;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;

import com.vaadin.data.Container;
import com.vaadin.terminal.ExternalResource;
import com.vaadin.ui.Component;
import com.vaadin.ui.Link;
import com.vaadin.ui.Table;
import com.vaadin.ui.Table.ColumnGenerator;

@SuppressWarnings("serial")
public class PersonList extends View<Table> {
    private Container dataSource;

    public PersonList(Container dataSource) {
        super("Person list", Table.class);
        this.dataSource = dataSource;
    }

    @Override
    public Table createFallback() {
        return new Table();
    }

    @Override
    protected Table accept(Presentation presentation) {
        Table rendition = super.accept(presentation);

        rendition.setContainerDataSource(dataSource);

        rendition.setVisibleColumns(PersonContainer.NATURAL_COL_ORDER);
        rendition.setColumnHeaders(PersonContainer.COL_HEADERS_ENGLISH);

        rendition.setSelectable(true);
        rendition.setImmediate(true);

        rendition.setNullSelectionAllowed(false);

        rendition.addGeneratedColumn("email", new EmailColumn());

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