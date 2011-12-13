package org.vaadin.jefferson.demo.addressbook.ui;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
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
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Panel;
import com.vaadin.ui.TextField;

@SuppressWarnings("serial")
public class SearchView extends Composite<ComponentContainer> {

    private View<AbstractTextField> tf = new View<AbstractTextField>(
            "Search term", AbstractTextField.class, TextField.class);

    private View<AbstractSelect> fieldToSearch = new FieldToSearch();

    private View<CheckBox> saveSearch = new SaveSearch("Save search",
            CheckBox.class);

    private View<AbstractTextField> searchName = new View<AbstractTextField>(
            "Search name", AbstractTextField.class, TextField.class);

    private View<Button> search = new ButtonView("Search", new SearchAction());

    private AddressBookApplication app;

    public SearchView(final AddressBookApplication app) {
        super("Search", ComponentContainer.class, Panel.class);
        this.app = app;

        setChildren(tf, fieldToSearch, saveSearch, searchName, search);
    }

    @Override
    protected void update(ComponentContainer rendition,
            Presentation presentation) {
        super.update(rendition, presentation);
        saveSearch.getRendition().addListener(new ClickListener() {
            public void buttonClick(ClickEvent event) {
                searchName.getRendition().setVisible(
                        saveSearch.getRendition().booleanValue());
            }
        });
    }

    private final class SearchAction implements Button.ClickListener {
        public void buttonClick(ClickEvent event) {
            app.search(new SearchFilter(
                    fieldToSearch.getRendition().getValue(),
                    tf.getRendition().getValue().toString(),
                    searchName.getRendition().getValue().toString()),
                    saveSearch.getRendition().booleanValue());
        }
    }

    private final static class SaveSearch extends View<CheckBox> {
        private SaveSearch(String name, Class<CheckBox> base) {
            super(name, base);
        }

        @Override
        protected void update(final CheckBox component,
                Presentation presentation) {
            super.update(component, presentation);
            component.setValue(Boolean.TRUE);
            component.setImmediate(true);
        }
    }

    private final static class FieldToSearch extends View<AbstractSelect> {
        private FieldToSearch() {
            super("Field to search", AbstractSelect.class, NativeSelect.class);
        }

        @Override
        protected void update(AbstractSelect component,
                Presentation presentation) {
            super.update(component, presentation);
            for (int i = 0; i < PersonContainer.NATURAL_COL_ORDER.length; i++) {
                component.addItem(PersonContainer.NATURAL_COL_ORDER[i]);
                component.setItemCaption(
                        PersonContainer.NATURAL_COL_ORDER[i],
                        PersonContainer.COL_HEADERS_ENGLISH[i]);
            }
            component.setValue("lastName");
            component.setNullSelectionAllowed(false);
        }
    }
}
