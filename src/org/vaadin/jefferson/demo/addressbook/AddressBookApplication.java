package org.vaadin.jefferson.demo.addressbook;

import org.vaadin.jefferson.demo.addressbook.data.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.data.SearchFilter;
import org.vaadin.jefferson.demo.addressbook.ui.AddressBookContent;
import org.vaadin.jefferson.demo.addressbook.ui.HelpWindow;
import org.vaadin.jefferson.demo.addressbook.ui.SharingOptions;

import com.vaadin.Application;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class AddressBookApplication extends Application {

    // Lazyly created ui references
    private HelpWindow helpWindow = null;
    private SharingOptions sharingOptions = null;

    private PersonContainer dataSource = PersonContainer.createWithTestData();

    private AddressBookContent content = new AddressBookContent(this);

    @Override
    public void init() {
        buildMainLayout();
    }

    private void buildMainLayout() {
        setMainWindow(new Window("Address Book Demo application"));

        setTheme("contacts");

        getMainWindow()
                .setContent(new AddressBookPresentation().visit(content));
    }

    private HelpWindow getHelpWindow() {
        if (helpWindow == null) {
            helpWindow = new HelpWindow();
        }
        return helpWindow;
    }

    private SharingOptions getSharingOptions() {
        if (sharingOptions == null) {
            sharingOptions = new SharingOptions();
        }
        return sharingOptions;
    }

    public PersonContainer getDataSource() {
        return dataSource;
    }

    public void showHelpWindow() {
        getMainWindow().addWindow(getHelpWindow());
    }

    public void showShareWindow() {
        getMainWindow().addWindow(getSharingOptions());
    }

    public void addNewContact() {
        content.list.personForm.addContact();
        content.showListView();
    }

    public void search(SearchFilter searchFilter, boolean save) {
        String term = searchFilter.getTerm();
        if (term == null || term.equals("")) {
            getMainWindow().showNotification("Search term cannot be empty!",
                    Notification.TYPE_WARNING_MESSAGE);
            return;
        }
        if (save) {
            saveSearch(searchFilter);
        }

        // clear previous filters
        getDataSource().removeAllContainerFilters();
        // filter contacts with given filter
        getDataSource().addContainerFilter(searchFilter.getPropertyId(),
                searchFilter.getTerm(), true, false);
        content.showListView();

        getMainWindow().showNotification(
                "Searched for " + searchFilter.getPropertyId() + "=*"
                        + searchFilter.getTerm() + "*, found "
                        + getDataSource().size() + " item(s).",
                Notification.TYPE_TRAY_NOTIFICATION);
    }

    public void saveSearch(SearchFilter searchFilter) {
        String searchName = searchFilter.getSearchName();
        if (searchName == null || searchName.equals("")) {
            getMainWindow().showNotification(
                    "Please enter a name for your search!",
                    Notification.TYPE_WARNING_MESSAGE);
            return;
        }
        content.tree.addSearch(searchFilter);
    }

    public void setItemId(Object itemId) {
        if (itemId != null) {
            if (AddressBookContent.SHOW_ALL.equals(itemId)) {
                // clear previous filters
                getDataSource().removeAllContainerFilters();
                content.showListView();
            } else if (AddressBookContent.SEARCH.equals(itemId)) {
                content.showSearchView();
            } else if (itemId instanceof SearchFilter) {
                search((SearchFilter) itemId, false);
            }
        }
    }
}
