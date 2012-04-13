package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.*;
import org.vaadin.jefferson.content.*;

import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label.ContentMode;

public class Toolbar extends SimpleComposite {
    public static final String ADD_CONTACT = "Add contact";
    public static final String HELP = "Help";
    public static final String LOGO = "Logo";
    public static final String SEARCH = "Search";
    public static final String SHARE = "Share";

    private ButtonControl newContactButton = new ButtonControl(ADD_CONTACT);
    private ButtonControl searchButton = new ButtonControl(SEARCH);
    private ButtonControl shareButton = new ButtonControl(SHARE);
    private ButtonControl helpButton = new ButtonControl(HELP);

    private View<Embedded> logo = new Logo(LOGO, Embedded.class);
    private AddressBookView root;

    public Toolbar(AddressBookView root) {
        super("Toolbar");
        this.root = root;

        setChildren(
                newContactButton,
                searchButton,
                shareButton,
                helpButton,
                logo);

        newContactButton.setListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                Toolbar.this.root.addNewContact();
            }
        });

        searchButton.setListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                Toolbar.this.root.showSearchView();
            }
        });

        shareButton.setListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                showShareWindow();
            }
        });

        helpButton.setListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                showHelpWindow();
            }
        });
    }

    protected void showHelpWindow() {
        getRendition().getRoot().addWindow(new HelpWindow());
    }

    protected void showShareWindow() {
        getRendition().getRoot().addWindow(new SharingOptions());
    }

    private static class HelpWindow extends Window {
        private static final String HELP_HTML_SNIPPET = "This is "
                + "an application built during <strong><a href=\""
                + "http://dev.vaadin.com/\">Vaadin</a></strong> "
                + "tutorial. Hopefully it doesn't need any real help.";

        public HelpWindow() {
            setCaption("Address Book help");
            addComponent(new Label(HELP_HTML_SNIPPET, ContentMode.XHTML));
        }
    }

    private static class SharingOptions extends Window {
        public SharingOptions() {
            /*
             * Make the window modal, which will disable all other components
             * while it is visible
             */
            setModal(true);

            /* Make the sub window 50% the size of the browser window */
            setWidth("50%");
            /*
             * Center the window both horizontally and vertically in the browser
             * window
             */
            center();

            setCaption("Sharing options");
            addComponent(new Label(
                    "With these setting you can modify contact sharing "
                            + "options. (non-functional, example of modal dialog)"));
            addComponent(new CheckBox("Gmail"));
            addComponent(new CheckBox(".Mac"));
            Button close = new Button("OK");
            close.addListener(new Button.ClickListener() {
                public void buttonClick(ClickEvent event) {
                    SharingOptions.this.close();
                }
            });
            addComponent(close);
        }
    }
}
