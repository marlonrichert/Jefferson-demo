package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;

public class AddressBookContent extends SimpleComposite {
    public static final String HELP = "Help";
    public static final String LOGO = "Logo";
    public static final String SEARCH = "Search";
    public static final String SHARE = "Share";

    public static final Object SHOW_ALL = "Show all";

    View<Button> newContactButton = new ButtonView("Add contact",
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    app.addNewContact();
                }
            });
    View<Button> searchButton = new ButtonView(SEARCH,
            new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    showSearchView();
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

    public TreeView tree;

    MainView main;
    public ListView list;
    SearchView search;

    private View<Embedded> logo = new View<Embedded>(LOGO, Embedded.class);

    private Composite<ComponentContainer> toolbar = new SimpleComposite(
            "Toolbar", newContactButton, searchButton, shareButton,
            helpButton, logo);

    AddressBookApplication app;

    public AddressBookContent(AddressBookApplication app) {
        super("Address book");
        this.app = app;
        PersonContainer dataSource = app.getDataSource();

        list = new ListView(
                new PersonList(dataSource),
                new PersonForm(dataSource));
        search = new SearchView("Search contacts", app);

        tree = new TreeView(app);
        main = new MainView(tree, list);

        setChildren(
                toolbar,
                main);
    }

    public void showListView() {
        setContentView(list);
    }

    public void showSearchView() {
        setContentView(search);
    }

    private void setContentView(View<?> v) {
        main.setContentView(v);
    }

    public static class MainView extends SimpleComposite {
        private View<?> contentView;

        public MainView(TreeView tree, View<?> contentView) {
            super("Main");
            this.contentView = contentView;

            setChildren(
                    tree,
                    contentView);
        }

        public void setContentView(View<?> contentView) {
            if (replaceChild(this.contentView, contentView)) {
                getRendition().replaceComponent(
                        this.contentView.getRendition(),
                        contentView.getRendition());
                this.contentView = contentView;
            }
        }
    }
}
