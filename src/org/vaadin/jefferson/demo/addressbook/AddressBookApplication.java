package org.vaadin.jefferson.demo.addressbook;

import org.vaadin.jefferson.demo.addressbook.data.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.data.SearchFilter;
import org.vaadin.jefferson.demo.addressbook.ui.HelpWindow;
import org.vaadin.jefferson.demo.addressbook.ui.NavigationTree;
import org.vaadin.jefferson.demo.addressbook.ui.SharingOptions;

import com.vaadin.Application;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.HorizontalLayout;
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
                .setContent(new AddressBookPresentation().render(content));
    }

    private HorizontalLayout createToolbar() {
        HorizontalLayout lo = new HorizontalLayout();

        lo.setMargin(true);
        lo.setSpacing(true);

        lo.setStyleName("toolbar");

        lo.setWidth("100%");

        Embedded em = new Embedded("", new ThemeResource("images/logo.png"));
        lo.addComponent(em);
        lo.setComponentAlignment(em, Alignment.MIDDLE_RIGHT);
        lo.setExpandRatio(em, 1);

        return lo;
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

    void showHelpWindow() {
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
            if (NavigationTree.SHOW_ALL.equals(itemId)) {
                // clear previous filters
                getDataSource().removeAllContainerFilters();
                content.showListView();
            } else if (NavigationTree.SEARCH.equals(itemId)) {
                content.showSearchView();
            } else if (itemId instanceof SearchFilter) {
                search((SearchFilter) itemId, false);
            }
        }
    }
}
