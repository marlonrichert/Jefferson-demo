package org.vaadin.jefferson.demo.addressbook;

import org.vaadin.jefferson.demo.addressbook.content.ContentRoot;
import org.vaadin.jefferson.demo.addressbook.content.HelpWindow;
import org.vaadin.jefferson.demo.addressbook.content.SharingOptions;
import org.vaadin.jefferson.demo.addressbook.content.TreeView;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.domain.SearchFilter;

import com.vaadin.Application;
import com.vaadin.ui.Window;
import com.vaadin.ui.Window.Notification;

@SuppressWarnings("serial")
public class AddressBookApplication extends Application {

    // Lazyly created ui references
    private HelpWindow helpWindow = null;
    private SharingOptions sharingOptions = null;

    private PersonContainer dataSource = PersonContainer.createWithTestData();

    private ContentRoot content = new ContentRoot(this);

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
        content.showListView();
        content.list.personForm.addContact();
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
            if (TreeView.SHOW_ALL.equals(itemId)) {
                // clear previous filters
                getDataSource().removeAllContainerFilters();
                content.showListView();
            } else if (TreeView.SEARCH.equals(itemId)) {
                content.showSearchView();
            } else if (itemId instanceof SearchFilter) {
                search((SearchFilter) itemId, false);
            }
        }
    }
}
