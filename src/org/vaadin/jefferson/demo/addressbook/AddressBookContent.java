package org.vaadin.jefferson.demo.addressbook;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.data.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.data.SearchFilter;
import org.vaadin.jefferson.demo.addressbook.ui.ListView;
import org.vaadin.jefferson.demo.addressbook.ui.NavigationTree;
import org.vaadin.jefferson.demo.addressbook.ui.PersonForm;
import org.vaadin.jefferson.demo.addressbook.ui.PersonList;
import org.vaadin.jefferson.demo.addressbook.ui.SearchView;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Tree;

class AddressBookContent extends SimpleComposite {
    static final String ADD_CONTACT = "Add contact";
    static final String ADDRESS_BOOK = "Address book";
    static final String HELP = "Help";
    static final String LIST = "List";
    static final String LOGO = "Logo";
    static final String MAIN = "Main";
    static final String PERSON_FORM = "Person form";
    static final String PERSON_LIST = "Person list";
    static final String SEARCH = "Search";
    static final String SHARE = "Share";
    static final String TOOLBAR = "Toolbar";
    static final String TREE = "Tree";

    View<Button> newContactButton = new ButtonView(ADD_CONTACT,
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

    TreeView tree = new TreeView(TREE, Tree.class);

    MainView main;
    ListView list;
    SearchView search;

    private Composite<ComponentContainer> toolbar = new SimpleComposite(
            TOOLBAR);

    private View<Embedded> logo = new View<Embedded>(LOGO, Embedded.class);

    private AddressBookApplication app;

    public AddressBookContent(AddressBookApplication app) {
        super(ADDRESS_BOOK);
        this.app = app;
        PersonContainer dataSource = app.getDataSource();

        list = new ListView(LIST,
                new PersonList(PERSON_LIST, dataSource),
                new PersonForm(PERSON_FORM, dataSource));
        search = new SearchView(app);

        main = new MainView(tree, list);

        setChildren(
                toolbar.setChildren(
                        newContactButton, searchButton, shareButton,
                        helpButton, logo),
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
        protected void update(Tree component, Presentation presentation) {
            super.update(component, presentation);
            component.addListener(new ValueChangeListener() {

                public void valueChange(ValueChangeEvent event) {
                    app.setItemId(event.getProperty().getValue());
                }
            });
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
            super(MAIN);
            this.contentView = contentView;

            setChildren(
                    tree,
                    contentView);
        }

        public void setContentView(View<?> contentView) {
            if (replaceChild(contentView, contentView)) {
                getRendition().replaceComponent(
                        this.contentView.getRendition(),
                        contentView.getRendition());
                this.contentView = contentView;
            }
        }
    }
}
