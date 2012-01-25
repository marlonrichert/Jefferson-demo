package org.vaadin.jefferson.demo.simpleaddressbook;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.simpleaddressbook.content.ContactEditor;
import org.vaadin.jefferson.demo.simpleaddressbook.content.ContactList;
import org.vaadin.jefferson.demo.simpleaddressbook.content.FilterControl;
import org.vaadin.jefferson.demo.simpleaddressbook.content.SimpleAddressBook;
import org.vaadin.jefferson.demo.simpleaddressbook.domain.Contact;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Form;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;

public class SimpleAddressBookPresentation extends Presentation {

    private Map<String, String> captions = new HashMap<String, String>();

    public SimpleAddressBookPresentation() {
        captions.put(SimpleAddressBook.ADD_CONTACT, "+");
        captions.put(SimpleAddressBook.REMOVE_CONTACT, "-");
        captions.put(Contact.LAST_NAME, "Last name");
        captions.put(Contact.FIRST_NAME, "First name");
        captions.put(Contact.COMPANY, "Company");
    }

    void render(SimpleAddressBook view) {
        setRendition(view, new HorizontalSplitPanel());
    }

    void render(ButtonControl view) {
        setRendition(view, new Button());
    }

    void style(ButtonControl view) {
        Button rendition = getRendition(view);
        String name = view.getName();
        rendition.setCaption(captions.get(name));
        rendition.setDescription(name);
        rendition.setHeight("100%");
    }

    void style(ContactEditor view) {
        Form rendition = getRendition(view);
        rendition.setSizeFull();
        rendition.getLayout().setMargin(true);
        rendition.setCaption("Contact details");
    }

    void style(ContactList view) {
        AbstractSelect rendition = getRendition(view);
        rendition.setSizeFull();
        expand(rendition);

        if (rendition instanceof Table) {
            Table table = (Table) rendition;
            table.setVisibleColumns(new Object[] {
                    Contact.LAST_NAME, Contact.FIRST_NAME, Contact.COMPANY,
            });
            table.setColumnHeader(
                    Contact.LAST_NAME, captions.get(Contact.LAST_NAME));
            table.setColumnHeader(
                    Contact.FIRST_NAME, captions.get(Contact.FIRST_NAME));
            table.setColumnHeader(
                    Contact.COMPANY, captions.get(Contact.COMPANY));
        }
    }

    void style(FilterControl view) {
        AbstractTextField rendition = getRendition(view);
        rendition.setWidth("100%");
        rendition.setInputPrompt(captions.get(view.getName()));

        expand(rendition);
    }

    void render(SimpleComposite view) {
        setRendition(view,
                SimpleAddressBook.TOOLBAR.equals(view.getName())
                        ? new HorizontalLayout()
                        : new VerticalLayout());
    }

    void style(SimpleComposite view) {
        ComponentContainer rendition = getRendition(view);
        String name = view.getName();
        if (SimpleAddressBook.NAVIGATION.equals(name)) {
            rendition.setSizeFull();
        } else if (SimpleAddressBook.TOOLBAR.equals(name)) {
            rendition.setWidth("100%");
        }
    }
}
