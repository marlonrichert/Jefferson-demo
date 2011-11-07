package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.LabelControl;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.View;

public class EstatesContent extends View {
    private final LabelControl title = new LabelControl("Title");
    private final SelectionControl tabs = new SelectionControl("Tabs");
    private final ButtonControl newEstate = new ButtonControl("New item");
    private final ButtonControl profile = new ButtonControl("Profile");
    private final ButtonControl signOut = new ButtonControl("Sign out");
    private final SelectionControl estates = new SelectionControl("Estates");

    private final LabelControl name = new LabelControl("Name");
    private final ButtonControl open = new ButtonControl("Open");
    private final SelectionControl expenses = new SelectionControl("Expenses");
    private final ButtonControl addExpense = new ButtonControl("Add expense");

    private final PropertiesView propertiesView = new PropertiesView();

    public EstatesContent() {
        super("Root");

        setChildren(
                new View("Navigation",
                        title, tabs, newEstate,
                        new View("user-actions",
                                profile, signOut)),
                new View("Main",
                        estates,
                        new View("Details",
                                new View("Estate info",
                                        name, open,
                                        propertiesView),
                                new View("Expense management",
                                        expenses, addExpense))));
    }

    public PropertiesView getPropertiesView() {
        return propertiesView;
    }

    public SelectionControl getEstatesControl() {
        return estates;
    }

    public static class PropertiesView extends View {
        public static final String STATE = "State";
        public static final String DATE = "Date";
        public static final String MAX = "Max";

        private final LabelControl max = new LabelControl(MAX);
        private final LabelControl date = new LabelControl(DATE);
        private final LabelControl state = new LabelControl(STATE);

        public PropertiesView() {
            super("Properties");

            setChildren(max, date, state);
        }

        public LabelControl[] getProperties() {
            return new LabelControl[] { max, date, state };
        }
    }
}