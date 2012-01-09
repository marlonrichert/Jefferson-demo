package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.domain.SearchFilter;

import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class ContentRoot extends SimpleComposite {
    private final TreeView tree;
    private final MainView main;
    private final ListView list;
    private final SearchView search;

    private PersonContainer dataSource;
    private PersonForm personForm;

    public ContentRoot(PersonContainer dataSource) {
        super("Address book");
        this.dataSource = dataSource;

        search = new SearchView(this);

        personForm = new PersonForm(this);

        tree = new TreeView(this);
        list = new ListView(
                new PersonList(this),
                personForm);

        main = new MainView(tree, list);

        setChildren(
                new Toolbar(this),
                main);
    }

    protected PersonContainer getDataSource() {
        return dataSource;
    }

    protected void addNewContact() {
        showListView();
        personForm.addContact();
    }

    protected void search(SearchFilter searchFilter, boolean save) {
        String term = searchFilter.getTerm();
        Window window = getRendition().getWindow();
        if (term == null || term.equals("")) {
            window.showNotification("Search term cannot be empty!",
                    Notification.TYPE_WARNING_MESSAGE);
            return;
        }
        if (save) {
            saveSearch(searchFilter);
        }

        // clear previous filters
        dataSource.removeAllContainerFilters();

        // filter contacts with given filter
        dataSource.addContainerFilter(searchFilter.getPropertyId(),
                searchFilter.getTerm(), true, false);

        showListView();

        window.showNotification(
                "Searched for " + searchFilter.getPropertyId() + "=*"
                        + searchFilter.getTerm() + "*, found "
                        + dataSource.size() + " item(s).",
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    private void saveSearch(SearchFilter searchFilter) {
        String searchName = searchFilter.getSearchName();
        if (searchName == null || searchName.equals("")) {
            getRendition().getWindow().showNotification(
                    "Please enter a name for your search!",
                    Notification.TYPE_WARNING_MESSAGE);
            return;
        }
        tree.addSearch(searchFilter);
    }

    protected void showListView() {
        main.setContentView(list);
    }

    protected void showSearchView() {
        main.setContentView(search);
    }
}
