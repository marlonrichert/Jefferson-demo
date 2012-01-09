package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;

import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;

public class ContentRoot extends SimpleComposite {
    public TreeView tree;

    MainView main;
    public ListView list;
    SearchView search;

    private Composite<ComponentContainer> toolbar;

    public ContentRoot(AddressBookApplication app) {
        super("Address book");
        PersonContainer dataSource = app.getDataSource();

        list = new ListView(
                new PersonList(dataSource),
                new PersonForm(dataSource));
        search = new SearchView("Search contacts", app);
        tree = new TreeView(app);

        toolbar = new Toolbar(this, app);
        main = new MainView(tree, list);

        setChildren(
                toolbar,
                main);
    }

    public void showListView() {
        main.setContentView(list);
    }

    public void showSearchView() {
        main.setContentView(search);
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

        public void setContentView(View<? extends Component> contentView) {
            replaceChild(this.contentView, contentView);
            this.contentView = contentView;
        }
    }
}
