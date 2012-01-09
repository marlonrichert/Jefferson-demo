package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;

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

    private View<Button> newContactButton = new ButtonView(ADD_CONTACT,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    root.addNewContact();
                }
            });
    private View<Button> searchButton = new ButtonView(SEARCH,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    root.showSearchView();
                }
            });
    private View<Button> shareButton = new ButtonView(SHARE,
            new ClickListener() {

                public void buttonClick(ClickEvent event) {
                    showShareWindow();
                }
            });
    private View<Button> helpButton = new ButtonView(HELP,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    showHelpWindow();
                }
            });

    private View<Embedded> logo = new Logo(LOGO, Embedded.class);
    private ContentRoot root;

    public Toolbar(ContentRoot root) {
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
}
