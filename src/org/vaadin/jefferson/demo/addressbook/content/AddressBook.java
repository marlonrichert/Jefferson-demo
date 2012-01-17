package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.domain.SearchFilter;

import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

public class AddressBook extends SimpleComposite {
    private final SearchView search = new SearchView(this);
    private final TreeView tree;
    private final MainView main;
    private final ListView list;
    private PersonForm personForm;

    private PersonContainer dataSource;

    public AddressBook(PersonContainer dataSource) {
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
}
