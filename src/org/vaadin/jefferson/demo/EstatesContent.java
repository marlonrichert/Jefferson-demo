package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.Composite;
import org.vaadin.jefferson.content.View;

import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Button;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Select;
import com.vaadin.ui.Table;

public class EstatesContent extends Composite<ComponentContainer> {
    private final View<Label> title = View.create(
            "Title", Label.class, Label.class);

    private final View<Label> name = View.create(
            "Name", Label.class, Label.class);

    private final View<AbstractSelect> tabs = View.create(
            "Tabs", AbstractSelect.class, Select.class);

    private final View<AbstractSelect> expenses = View.create(
            "Expenses", AbstractSelect.class, Table.class);

    private final View<Button> newEstate = View.create(
            "New item", Button.class, NativeButton.class);

    private final View<Button> profile = View.create(
            "Profile", Button.class, NativeButton.class);

    private final View<Button> signOut = View.create(
            "Sign out", Button.class, NativeButton.class);

    private final View<AbstractSelect> estates = View.create(
            "Estates", AbstractSelect.class, Table.class);

    private final View<Button> open = View.create(
            "Open", Button.class, NativeButton.class);

    private final View<Button> addExpense = View.create(
            "Add expense", Button.class, NativeButton.class);

    private final Composite<ComponentContainer> navigation = Composite.create(
            "Navigation", ComponentContainer.class, CssLayout.class);

    private final Composite<ComponentContainer> userActions = Composite.create(
            "User actions", ComponentContainer.class, CssLayout.class);

    private final Composite<ComponentContainer> main = Composite.create(
            "Main", ComponentContainer.class, CssLayout.class);

    private final Composite<ComponentContainer> details = Composite.create(
            "Details", ComponentContainer.class, CssLayout.class);

    private final Composite<ComponentContainer> estateInfo = Composite.create(
            "Estate info", ComponentContainer.class, CssLayout.class);

    private final Composite<ComponentContainer> expenseManagement = Composite
            .create("Expense management", ComponentContainer.class,
                    CssLayout.class);

    private final PropertiesView properties = new PropertiesView();

    public EstatesContent() {
        super("Root", ComponentContainer.class, CssLayout.class);

        setChildren(
                navigation.setChildren(
                        title, tabs, newEstate,
                        userActions.setChildren(
                                profile, signOut)),
                main.setChildren(
                        estates,
                        details.setChildren(
                                estateInfo.setChildren(
                                        name, open,
                                        properties),
                                expenseManagement.setChildren(
                                        expenses, addExpense))));
    }

    public PropertiesView getPropertiesView() {
        return properties;
    }

    public View<AbstractSelect> getEstatesView() {
        return estates;
    }

    public static class PropertiesView extends Composite<ComponentContainer> {
        public static final String STATE = "State";
        public static final String DATE = "Date";
        public static final String MAX = "Max";

        private final View<Label> max = View.create(MAX, Label.class,
                Label.class);
        private final View<Label> date = View.create(DATE, Label.class,
                Label.class);
        private final View<Label> state = View.create(STATE, Label.class,
                Label.class);

        public PropertiesView() {
            super("Properties", ComponentContainer.class,
                    HorizontalLayout.class);

            setChildren(max, date, state);
        }

        public View<Label>[] getProperties() {
            @SuppressWarnings("unchecked")
            View<Label>[] properties = new View[] { max, date, state };
            return properties;
        }
    }
}