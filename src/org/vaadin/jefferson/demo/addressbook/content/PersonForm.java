package org.vaadin.jefferson.demo.addressbook.content;

import java.util.Arrays;
import java.util.List;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.demo.addressbook.domain.Person;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;

import com.vaadin.data.Item;
import com.vaadin.data.util.BeanItem;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.data.validator.RegexpValidator;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.Component;
import com.vaadin.ui.DefaultFieldFactory;
import com.vaadin.ui.Field;
import com.vaadin.ui.Form;
import com.vaadin.ui.Layout;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class PersonForm extends View<Form> implements ClickListener {

    private Button save = new Button("Save", this);
    private Button cancel = new Button("Cancel", this);
    private Button edit = new Button("Edit", this);
    private final ComboBox cities = new ComboBox("City");

    private boolean newContactMode = false;
    private Person newPerson = null;
    private PersonContainer dataSource;

    public PersonForm(PersonContainer dataSource) {
        super("Person form", Form.class);
        this.dataSource = dataSource;
    }

    @Override
    public Form createFallback() {
        return new Form();
    }

    @Override
    protected Form accept(Presentation presentation) {
        Form rendition = super.accept(presentation);

        /*
         * Enable buffering so that commit() must be called for the form before
         * input is written to the data. (Form input is not written immediately
         * through to the underlying object.)
         */
        rendition.setWriteThrough(false);

        Layout footer = rendition.getFooter();
        footer.addComponent(save);
        footer.addComponent(cancel);
        footer.addComponent(edit);
        footer.setVisible(false);

        /* Allow the user to enter new cities */
        cities.setNewItemsAllowed(true);

        /* We do not want to use null values */
        cities.setNullSelectionAllowed(false);

        /* Add an empty city used for selecting no city */
        cities.addItem("");

        /* Populate cities select using the cities in the data container */
        PersonContainer ds = dataSource;
        for (Person person : ds.getItemIds()) {
            String city = person.getCity();
            cities.addItem(city);
        }

        /*
         * Field factory for overriding how the component for city selection is
         * created
         */
        rendition.setFormFieldFactory(new DefaultFieldFactory() {
            @Override
            public Field createField(Item item, Object propertyId,
                    Component uiContext) {
                if (propertyId.equals("city")) {
                    cities.setWidth("200px");
                    return cities;
                }

                Field field = super.createField(item, propertyId, uiContext);
                if (propertyId.equals("postalCode")) {
                    TextField tf = (TextField) field;
                    /*
                     * We do not want to display "null" to the user when the
                     * field is empty
                     */
                    tf.setNullRepresentation("");

                    /* Add a validator for postalCode and make it required */
                    tf.addValidator(new RegexpValidator("[1-9][0-9]{4}",
                            "Postal code must be a five digit number and cannot start with a zero."));
                    tf.setRequired(true);
                } else if (propertyId.equals("email")) {
                    /* Add a validator for email and make it required */
                    field.addValidator(new EmailValidator(
                            "Email must contain '@' and have full domain."));
                    field.setRequired(true);

                }

                field.setWidth("200px");
                return field;
            }
        });

        return rendition;
    }

    public void buttonClick(ClickEvent event) {
        Button source = event.getButton();

        Form form = getRendition();
        if (source == save) {
            /* If the given input is not valid there is no point in continuing */
            if (!form.isValid()) {
                return;
            }

            form.commit();
            if (newContactMode) {
                /* We need to add the new person to the container */
                Item addedItem = dataSource.addItem(newPerson);
                /*
                 * We must update the form to use the Item from our datasource
                 * as we are now in edit mode (no longer in add mode)
                 */
                setDataSource(addedItem);

                newContactMode = false;
            }
            setReadOnly(true);
        } else if (source == cancel) {
            if (newContactMode) {
                newContactMode = false;
                /* Clear the form and make it invisible */
                setDataSource(null);
            } else {
                form.discard();
            }
            setReadOnly(true);
        } else if (source == edit) {
            setReadOnly(false);
        }
    }

    public void setDataSource(Item newDataSource) {
        newContactMode = false;
        Form form = getRendition();
        if (newDataSource != null) {
            List<Object> orderedProperties = Arrays.asList(
                    PersonContainer.NATURAL_COL_ORDER);
            form.setItemDataSource(newDataSource, orderedProperties);

            setReadOnly(true);
            form.getFooter().setVisible(true);
        } else {
            form.setItemDataSource(null);
            form.getFooter().setVisible(false);
        }
    }

    public void setReadOnly(boolean readOnly) {
        getRendition().setReadOnly(readOnly);
        save.setVisible(!readOnly);
        cancel.setVisible(!readOnly);
        edit.setVisible(readOnly);
    }

    public void addContact() {
        // Create a temporary item for the form
        newPerson = new Person();
        setDataSource(new BeanItem<Person>(newPerson));
        newContactMode = true;
        setReadOnly(false);
    }
}