package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.SimpleComposite;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Table;

public class ListView extends SimpleComposite {
    public PersonForm personForm;
    private PersonList personList;

    public ListView(PersonList personList, PersonForm personForm) {
        super("List");
        this.personList = personList;
        this.personForm = personForm;

        setChildren(personList, personForm);
    }

    @Override
    protected ComponentContainer accept(Presentation presentation) {
        ComponentContainer rendition = super.accept(presentation);

        personList.getRendition().addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                Table table = personList.getRendition();
                personForm.setDataSource(table.getItem(table.getValue()));
            }
        });

        return rendition;
    }
}
