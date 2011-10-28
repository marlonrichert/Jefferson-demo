package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.LabelControl;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.View;

public class EstatesContent extends View {
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

    private final View properties = new View("properties");
    private final View details = new View("details");
    private final View nav = new View("nav");
    private final View main = new View("main");
    private final View userActions = new View("user-actions");
    private final View expenseManagement = new View("expense-management");
    private final View estateInfo = new View("estate-info");

    public EstatesContent() {
        super("root");

        setChildren(nav, main);

        nav.setChildren(title, tabs, newEstate, userActions);
        userActions.setChildren(profile, signOut);

        main.setChildren(estates, details);
        details.setChildren(estateInfo, expenseManagement);

        estateInfo.setChildren(name, open, properties);
        properties.setChildren(max, date, state);

        expenseManagement.setChildren(expenses, addExpense);
    }

    public View getPropertiesView() {
        return properties;
    }

    public SelectionControl getEstatesControl() {
        return estates;
    }
}