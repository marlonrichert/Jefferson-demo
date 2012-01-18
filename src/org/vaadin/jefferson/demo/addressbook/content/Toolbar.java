package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.SimpleComposite;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Label;
import com.vaadin.ui.Window;

public class Toolbar extends SimpleComposite {
    public static final String ADD_CONTACT = "Add contact";
    public static final String HELP = "Help";
    public static final String LOGO = "Logo";
    public static final String SEARCH = "Search";
    public static final String SHARE = "Share";

    private View<Button> newContactButton = new ButtonControl(ADD_CONTACT,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    root.addNewContact();
                }
            });
    private View<Button> searchButton = new ButtonControl(SEARCH,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    root.showSearchView();
                }
            });
    private View<Button> shareButton = new ButtonControl(SHARE,
            new ClickListener() {

                public void buttonClick(ClickEvent event) {
                    showShareWindow();
                }
            });
    private View<Button> helpButton = new ButtonControl(HELP,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    showHelpWindow();
                }
            });

    private View<Embedded> logo = new Logo(LOGO, Embedded.class);
    private AddressBook root;

    public Toolbar(AddressBook root) {
        super("Toolbar");
        this.root = root;

        setChildren(
                newContactButton,
                searchButton,
                shareButton,
                helpButton,
                logo);
    }

    protected void showHelpWindow() {
        getRendition().getWindow().addWindow(new HelpWindow());
    }

    protected void showShareWindow() {
        getRendition().getWindow().addWindow(new SharingOptions());
    }

    private static class HelpWindow extends Window {
        private static final String HELP_HTML_SNIPPET = "This is "
                + "an application built during <strong><a href=\""
                + "http://dev.vaadin.com/\">Vaadin</a></strong> "
                + "tutorial. Hopefully it doesn't need any real help.";

        public HelpWindow() {
            setCaption("Address Book help");
            addComponent(new Label(HELP_HTML_SNIPPET, Label.CONTENT_XHTML));
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
