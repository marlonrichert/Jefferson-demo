package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.*;
import org.vaadin.jefferson.content.*;
import org.vaadin.jefferson.demo.addressbook.domain.*;

import com.vaadin.data.Property.ValueChangeEvent;
import com.vaadin.data.Property.ValueChangeListener;
import com.vaadin.ui.*;
import com.vaadin.ui.Button.ClickEvent;

@SuppressWarnings("serial")
public class SearchView extends SimpleComposite {
    private TextControl searchTerm = new TextControl("Search term");
    private FieldToSearch fieldToSearch = new FieldToSearch();
    private SaveSearch saveSearch = new SaveSearch();
    private SearchName searchName = new SearchName();
    private ButtonControl search = new ButtonControl("Search");
    private AddressBookView root;

    public SearchView(AddressBookView root) {
        super("Search contacts");
        this.root = root;

        setChildren(searchTerm, fieldToSearch, saveSearch, searchName, search);

        search.setListener(new SearchAction());
    }

    private class SearchAction implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            root.search(new SearchFilter(
                    fieldToSearch.getSelection()[0],
                    searchTerm.getText(),
                    searchName.getText()),
                    saveSearch.isChecked());
        }
    }

    private class SaveSearch extends Control<CheckBox, ValueChangeListener> {
        private SaveSearch() {
            super("Save search", CheckBox.class, ValueChangeListener.class);

            setListener(new ValueChangeListener() {
                public void valueChange(ValueChangeEvent event) {
                    searchName.setVisible(getRendition().getValue()
                            .booleanValue());
                }
            });
        }

        @Override
        protected CheckBox accept(Presentation presentation) {
            CheckBox rendition = super.accept(presentation);
            rendition.setValue(Boolean.TRUE);
            return rendition;
        }

        @Override
        public CheckBox createFallback() {
            return new CheckBox();
        }

        public boolean isChecked() {
            CheckBox rendition = getRendition();
            if (rendition == null) {
                return false;
            }
            return Boolean.valueOf("" + rendition.getValue()).booleanValue();
        }
    }

    private static class FieldToSearch
            extends SelectionControl<String> {
        private FieldToSearch() {
            super("Field to search", String.class);
        }

        @Override
        protected AbstractSelect accept(Presentation presentation) {
            AbstractSelect rendition = super.accept(presentation);

            for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {
                rendition.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
                rendition.setItemCaption(
                        PersonContainer.NATURAL_COL_ORDER[i],
                        PersonContainer.COL_HEADERS_ENGLISH[i]);
            }
            rendition.setValue("lastName");
            rendition.setNullSelectionAllowed(false);

            return rendition;
        }
    }
}
