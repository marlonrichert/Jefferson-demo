package org.vaadin.jefferson.demo.simpleaddressbook;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.simpleaddressbook.content.ContactEditor;
import org.vaadin.jefferson.demo.simpleaddressbook.content.ContactList;
import org.vaadin.jefferson.demo.simpleaddressbook.content.Filter;
import org.vaadin.jefferson.demo.simpleaddressbook.content.SimpleAddressBook;

import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
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
    }

    @Override
    protected void style(View<?> view) {
        super.style(view);
        view.getRendition().setWidth("100%");
    }

    Component render(SimpleAddressBook view) {
        return new HorizontalSplitPanel();
    }

    Component render(ButtonView view) {
        return new Button();
    }

    void style(ButtonView view) {
        Button rendition = view.getRendition();
        String name = view.getName();
        rendition.setCaption(captions.get(name));
        rendition.setDescription(name);
        rendition.setHeight("100%");
    }

    void style(ContactEditor view) {
        Form rendition = view.getRendition();
        rendition.setSizeFull();
        rendition.getLayout().setMargin(true);
        rendition.setCaption("Contact details");
    }

    void style(ContactList view) {
        Table rendition = view.getRendition();
        rendition.setSizeFull();

        expand(view);
    }

    void style(Filter view) {
        AbstractTextField rendition = view.getRendition();
        rendition.setWidth("100%");
        rendition.setInputPrompt(view.getName());

        expand(view);
    }

    private void expand(View<?> view) {
        ComponentContainer parentRendition = view.getParent().getRendition();
        if (parentRendition instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) parentRendition).setExpandRatio(
                    view.getRendition(), 1);
        }
    }

    Component render(SimpleComposite view) {
        if (SimpleAddressBook.TOOLBAR.equals(view.getName())) {
            return new HorizontalLayout();
        }
        return new VerticalLayout();
    }

    void style(SimpleComposite view) {
        ComponentContainer rendition = view.getRendition();
        if (rendition instanceof VerticalLayout) {
            rendition.setSizeFull();
        }
    }
}
