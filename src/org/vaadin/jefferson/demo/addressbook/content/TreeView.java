package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.domain.SearchFilter;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.Tree;

public final class TreeView extends View<Tree> {
    public static final Object SHOW_ALL = "Show all";
    public static final Object SEARCH = "Search";

    private final AddressBookApplication app;

    TreeView(AddressBookApplication app) {
        super("Tree", Tree.class);
        this.app = app;
    }

    @Override
    public Tree createFallback() {
        return new Tree();
    }

    @Override
    protected Tree accept(Presentation presentation) {
        Tree rendition = super.accept(presentation);

        rendition.addItem(SHOW_ALL);
        rendition.addItem(SEARCH);

        rendition.setChildrenAllowed(SHOW_ALL, false);

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

        return rendition;
    }

    public void addSearch(SearchFilter searchFilter) {
        Tree rendition = getRendition();
        rendition.addItem(searchFilter);
        rendition.setParent(searchFilter, SEARCH);

        // mark the saved search as a leaf (cannot have children)
        rendition.setChildrenAllowed(searchFilter, false);

        // make sure "Search" is expanded
        rendition.expandItem(SEARCH);

        // select the saved search
        rendition.setValue(searchFilter);
    }
}