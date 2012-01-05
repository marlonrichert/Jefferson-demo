package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.View;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.domain.SearchFilter;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Tree;

public final class TreeView extends View<Tree> {
    /**
     * 
     */
    private final AddressBookApplication app;

    TreeView(AddressBookApplication app) {
        super("Tree", Tree.class);
        this.app = app;
    }

    @Override
    protected boolean setRendition(Tree rendition) {
        if (!super.setRendition(rendition)) {
            return false;
        }

        rendition.addItem(AddressBookContent.SHOW_ALL);
        rendition.addItem(AddressBookContent.SEARCH);

        rendition.setChildrenAllowed(AddressBookContent.SHOW_ALL, false);

        /*
         * We want items to be selectable but do not want the user to be able to
         * de-select an item.
         */
        rendition.setSelectable(true);
        rendition.setNullSelectionAllowed(false);

        rendition.addListener(new ValueChangeListener() {

            public void valueChange(ValueChangeEvent event) {
                app.setItemId(event.getProperty().getValue());
            }
        });

        return true;
    }

    public void addSearch(SearchFilter searchFilter) {
        Tree rendition = getRendition();
        rendition.addItem(searchFilter);
        rendition.setParent(searchFilter, AddressBookContent.SEARCH);

        // mark the saved search as a leaf (cannot have children)
        rendition.setChildrenAllowed(searchFilter, false);

        // make sure "Search" is expanded
        rendition.expandItem(AddressBookContent.SEARCH);

        // select the saved search
        rendition.setValue(searchFilter);
    }
}