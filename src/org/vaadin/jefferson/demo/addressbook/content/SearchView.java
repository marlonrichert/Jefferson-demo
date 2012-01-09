package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SelectionView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.content.TextView;
import org.vaadin.jefferson.demo.addressbook.AddressBookApplication;
import org.vaadin.jefferson.demo.addressbook.domain.PersonContainer;
import org.vaadin.jefferson.demo.addressbook.domain.SearchFilter;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractTextField;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.CheckBox;

@SuppressWarnings("serial")
public class SearchView extends SimpleComposite {
    private View<AbstractTextField> searchTerm = new TextView("Search term");

    private View<AbstractSelect> fieldToSearch = new FieldToSearch();

    private View<CheckBox> saveSearch = new SaveSearch();

    private View<AbstractTextField> searchName = new TextView("Search name");

    private View<Button> search = new ButtonView("Search", new SearchAction());

    private AddressBookApplication app;

    public SearchView(String name, final AddressBookApplication app) {
        super(name);
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
        protected CheckBox accept(Presentation presentation) {
            CheckBox rendition = super.accept(presentation);

            rendition.setValue(Boolean.TRUE);
            rendition.setImmediate(true);

            rendition.addListener(new ClickListener() {
                public void buttonClick(ClickEvent event) {
                    searchName.getRendition().setVisible(
                            getRendition().booleanValue());
                }
            });

            return rendition;
        }

        @Override
        public CheckBox createFallback() {
            return new CheckBox();
        }
    }

    private static class FieldToSearch extends SelectionView {
        private FieldToSearch() {
            super("Field to search");
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
