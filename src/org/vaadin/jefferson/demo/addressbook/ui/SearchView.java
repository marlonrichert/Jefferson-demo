package org.vaadin.jefferson.demo.addressbook.ui;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SelectionView;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.data.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.data.SearchFilter;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SearchView extends Composite<ComponentContainer> {
    private View<AbstractTextField> searchTerm = new View<AbstractTextField>(
            "Search term", AbstractTextField.class, new TextField());

    private View<AbstractSelect> fieldToSearch = new FieldToSearch();

    private View<CheckBox> saveSearch = new SaveSearch();

    private View<AbstractTextField> searchName = new View<AbstractTextField>(
            "Search name", AbstractTextField.class, new TextField());

    private View<Button> search = new ButtonView("Search", new SearchAction());

    private AddressBookApplication app;

    public SearchView(String name, final AddressBookApplication app) {
        super(name, ComponentContainer.class, new Panel());
        this.app = app;

        setChildren(searchTerm, fieldToSearch, saveSearch, searchName, search);
    }

    private class SearchAction implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            app.search(new SearchFilter(
                    fieldToSearch.getRendition().getValue(),
                    searchTerm.getRendition().getValue().toString(),
                    searchName.getRendition().getValue().toString()),
                    saveSearch.getRendition().booleanValue());
        }
    }

    private class SaveSearch extends View<CheckBox> {
        private SaveSearch() {
            super("Save search", CheckBox.class);
        }

        @Override
        protected boolean setRendition(CheckBox rendition) {
            if (!super.setRendition(rendition)) {
                return false;
            }
            rendition.setValue(Boolean.TRUE);
            rendition.setImmediate(true);

            rendition.addListener(new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    searchName.getRendition().setVisible(
                            getRendition().booleanValue());
                }
            });

            return true;
        }
    }

    private static class FieldToSearch extends SelectionView {
        private FieldToSearch() {
            super("Field to search");
        }

        @Override
        protected boolean setRendition(AbstractSelect rendition) {
            if (!super.setRendition(rendition)) {
                return false;
            }
            for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {
                rendition.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
                rendition.setItemCaption(
                        PersonContainer.NATURAL_COL_ORDER[i],
                        PersonContainer.COL_HEADERS_ENGLISH[i]);
            }
            rendition.setValue("lastName");
            rendition.setNullSelectionAllowed(false);

            return true;
        }
    }
}
