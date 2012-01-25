package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.domain.Person;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.data.util.BeanItem;
import com.vaadin.ui.ComponentContainer;

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

        personList.setListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                personForm.setDataSource(
                        new BeanItem<Person>(personList.getSelection()[0]));
            }
        });

        return rendition;
    }
}
