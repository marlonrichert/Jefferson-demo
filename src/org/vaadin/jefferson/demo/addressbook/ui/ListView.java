package org.vaadin.jefferson.demo.addressbook.ui;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.SimpleComposite;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Table;

public class ListView extends SimpleComposite {
    public PersonForm personForm;
    private PersonList personList;

    public ListView(String name, PersonList personList, PersonForm personForm) {
        super(name);
        this.personList = personList;
        this.personForm = personForm;

        setChildren(personList, personForm);
    }

    @Override
    protected void update(ComponentContainer rendition,
            Presentation presentation) {
        super.update(rendition, presentation);

        personList.getRendition().addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                Table table = personList.getRendition();
                personForm.setDataSource(table.getItem(table.getValue()));
            }
        });
    }
}
