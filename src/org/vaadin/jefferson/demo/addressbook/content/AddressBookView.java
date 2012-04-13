package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.content.*;
import org.vaadin.jefferson.demo.addressbook.domain.*;

import com.vaadin.ui.*;

public class AddressBookView extends SimpleComposite {
    private final SearchView search = new SearchView(this);
    private final TreeView tree;
    private final MainView main;
    private final ListView list;
    private PersonForm personForm;

    private PersonContainer dataSource;

    public AddressBookView(PersonContainer dataSource) {
        super("Address book");
        this.dataSource = dataSource;

        setChildren(
                new Toolbar(this),
                main = new MainView(
                        tree = new TreeView(this),
                        list = new ListView(
                                new PersonList(this),
                                personForm = new PersonForm(this))));
    }

    protected void showListView() {
        main.setContentView(list);
    }

    protected void showSearchView() {
        main.setContentView(search);
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
        if (term == null || term.equals("")) {
            Root.getCurrentRoot().showNotification(
                    "Search term cannot be empty!",
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

        Root.getCurrentRoot().showNotification(
                "Searched for " + searchFilter.getPropertyId() + "=*"
                        + searchFilter.getTerm() + "*, found "
                        + dataSource.size() + " item(s).",
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    private void saveSearch(SearchFilter searchFilter) {
        String searchName = searchFilter.getSearchName();
        if (searchName == null || searchName.equals("")) {
            getRendition().getRoot().showNotification(
                    "Please enter a name for your search!",
                    Notification.TYPE_WARNING_MESSAGE);
            return;
        }
        tree.addSearch(searchFilter);
    }
}
