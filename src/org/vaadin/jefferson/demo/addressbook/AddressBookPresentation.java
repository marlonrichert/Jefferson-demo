package org.vaadin.jefferson.demo.addressbook;

import static org.vaadin.jefferson.demo.addressbook.AddressBookContent.*;

import org.vaadin.jefferson.Presentation;

import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;

public class AddressBookPresentation extends Presentation {
    private AbstractSplitPanel main;
    private Embedded logo;

    public AddressBookPresentation() {
        define(ADDRESS_BOOK, VerticalLayout.class);
        define(ADDRESS_BOOK, method("addressBook", AbstractOrderedLayout.class));

        define(MAIN, HorizontalSplitPanel.class);
        define(MAIN, method("main", AbstractSplitPanel.class));

        define(LIST, VerticalSplitPanel.class);
        define(LIST, method("list", AbstractSplitPanel.class));

        define(TOOLBAR, HorizontalLayout.class);
        define(TOOLBAR, method("toolbar", AbstractOrderedLayout.class));

        define(LOGO, method("logo", Embedded.class));

        define(PERSON_LIST, method("personList", Table.class));

        define(ADD_CONTACT, method("newContact", Button.class));
        define(HELP, method("help", Button.class));
        define(SEARCH, method("search", Button.class));
        define(SHARE, method("share", Button.class));
    }

    void addressBook(AbstractOrderedLayout layout) {
        layout.setSizeFull();
        layout.setExpandRatio(main, 1);
    }

    void main(AbstractSplitPanel panel) {
        main = panel;
        main.setSplitPosition(200, Sizeable.UNITS_PIXELS);
        main.setSizeFull();
    }

    void list(AbstractSplitPanel panel) {
        panel.addStyleName("view");
        panel.setSplitPosition(40);
        panel.setSizeFull();
    }

    void logo(Embedded embedded) {
        logo = embedded;
        logo.setSource(new ThemeResource("images/logo.png"));
        logo.setCaption(null);
    }

    void searchContacts(Panel panel) {
        panel.addStyleName("view");
        panel.setSizeFull();
        panel.setContent(new FormLayout());
    }

    void personList(Table table) {
        table.setSizeFull();
        table.setColumnCollapsingAllowed(true);
        table.setColumnReorderingAllowed(true);
    }

    void toolbar(AbstractOrderedLayout toolbar) {
        toolbar.setMargin(true);
        toolbar.setSpacing(true);
        toolbar.setWidth("100%");
        toolbar.setComponentAlignment(logo, Alignment.MIDDLE_RIGHT);
        toolbar.setExpandRatio(logo, 1);
    }

    void search(Button button) {
        button.setIcon(new ThemeResource("icons/32/folder-add.png"));
    }

    void share(Button button) {
        button.setIcon(new ThemeResource("icons/32/users.png"));
    }

    void help(Button button) {
        button.setIcon(new ThemeResource("icons/32/help.png"));
    }

    void newContact(Button button) {
        button.setIcon(new ThemeResource("icons/32/document-add.png"));
    }
}
