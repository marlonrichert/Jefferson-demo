package org.vaadin.jefferson.demo.addressbook.ui;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.data.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.data.SearchFilter;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Tree;

public class AddressBookContent extends SimpleComposite {
    public static final String HELP = "Help";
    public static final String LOGO = "Logo";
    public static final String SEARCH = "Search";
    public static final String SHARE = "Share";

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

    public TreeView tree = new TreeView("Tree", Tree.class);

    MainView main;
    public ListView list;
    SearchView search;

    private View<Embedded> logo = new View<Embedded>(LOGO, Embedded.class);

    private Composite<ComponentContainer> toolbar = new SimpleComposite(
            "Toolbar", newContactButton, searchButton, shareButton,
            helpButton, logo);

    private AddressBookApplication app;

    public AddressBookContent(AddressBookApplication app) {
        super("Address book");
        this.app = app;
        PersonContainer dataSource = app.getDataSource();

        list = new ListView("List",
                new PersonList("Person list", dataSource),
                new PersonForm("Person form", dataSource));
        search = new SearchView("Search contacts", app);

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

    public final class TreeView extends View<Tree> {
        private TreeView(String name, Class<Tree> base) {
            super(name, base);
        }

        @Override
        protected boolean setRendition(Tree rendition) {
            if (!super.setRendition(rendition)) {
                return false;
            }

            rendition.addListener(new ValueChangeListener() {

                public void valueChange(ValueChangeEvent event) {
                    app.setItemId(event.getProperty().getValue());
                }
            });

            return true;
        }

        public void addSearch(SearchFilter searchFilter) {
            Tree rendition = tree.getRendition();
            rendition.addItem(searchFilter);
            rendition.setParent(searchFilter, NavigationTree.SEARCH);

            // mark the saved search as a leaf (cannot have children)
            rendition.setChildrenAllowed(searchFilter, false);

            // make sure "Search" is expanded
            rendition.expandItem(NavigationTree.SEARCH);

            // select the saved search
            rendition.setValue(searchFilter);
        }
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
