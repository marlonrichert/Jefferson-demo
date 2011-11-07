package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.LabelControl;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.View;

public class EstatesContent extends View {
    private static final String PROPERTIES = "properties";

    private final LabelControl title = new LabelControl("title");
    private final SelectionControl tabs = new SelectionControl("tabs");
    private final ButtonControl newEstate = new ButtonControl("new-item");
    private final ButtonControl profile = new ButtonControl("profile");
    private final ButtonControl signOut = new ButtonControl("sign-out");
    private final SelectionControl estates = new SelectionControl("estates");

    private final LabelControl name = new LabelControl("name");
    private final ButtonControl open = new ButtonControl("open");
    private final SelectionControl expenses = new SelectionControl("expenses");
    private final ButtonControl addExpense = new ButtonControl("add-expense");

    private final LabelControl max = new LabelControl("max");
    private final LabelControl date = new LabelControl("date");
    private final LabelControl state = new LabelControl("state");

    public EstatesContent() {
        super("root");

        setChildren(
                view("nav",
                        title, tabs, newEstate,
                        view("user-actions", profile, signOut)),
                view("main",
                        estates,
                        view("details",
                                view("estate-info",
                                        name, open,
                                        view(PROPERTIES,
                                                max, date, state)),
                                view("expense-management",
                                        expenses, addExpense))));
    }

    public View getPropertiesView() {
        return getView(PROPERTIES);
    }

    public SelectionControl getEstatesControl() {
        return estates;
    }
}