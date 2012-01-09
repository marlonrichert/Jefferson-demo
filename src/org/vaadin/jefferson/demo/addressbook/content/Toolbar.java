package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Embedded;

public class Toolbar extends SimpleComposite {
    public static final String ADD_CONTACT = "Add contact";
    public static final String HELP = "Help";
    public static final String LOGO = "Logo";
    public static final String SEARCH = "Search";
    public static final String SHARE = "Share";

    View<Button> newContactButton = new ButtonView(ADD_CONTACT,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    app.addNewContact();
                }
            });
    View<Button> searchButton = new ButtonView(SEARCH,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    root.showSearchView();
                }
            });
    View<Button> shareButton = new ButtonView(SHARE,
            new ClickListener() {

                public void buttonClick(ClickEvent event) {
                    app.showShareWindow();
                }
            });
    View<Button> helpButton = new ButtonView(HELP,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    app.showHelpWindow();
                }
            });

    private View<Embedded> logo = new Logo(LOGO, Embedded.class);

    private AddressBookApplication app;
    private ContentRoot root;

    public Toolbar(ContentRoot root, AddressBookApplication app) {
        super("Toolbar");
        this.root = root;
        this.app = app;

        setChildren(
                newContactButton,
                searchButton,
                shareButton,
                helpButton,
                logo);
    }

    private static class Logo extends View<Embedded> {
        private Logo(String name, Class<Embedded> base) {
            super(name, base);
        }

        @Override
        public Embedded createFallback() {
            return new Embedded();
        }
    }
}
