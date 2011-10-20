package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.Control;
import org.vaadin.jefferson.content.LabelControl;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.View;

public class DemoContent extends View {
    private final Control title = new LabelControl("title");
    private final Control tabs = new SelectionControl("tabs");
    private final Control newEstate = new ButtonControl("new-item");
    private final Control profile = new ButtonControl("profile");
    private final Control signOut = new ButtonControl("sign-out");
    private final Control estates = new SelectionControl("estates");

    private final Control name = new LabelControl("name");
    private final Control open = new ButtonControl("open");
    private final Control expenses = new SelectionControl("expenses");
    private final Control addExpense = new ButtonControl("add-expense");

    private final Control max = new LabelControl("max");
    private final Control date = new LabelControl("date");
    private final Control state = new LabelControl("state");

    private final View properties = new View("properties");
    private final View details = new View("details");
    private final View nav = new View("nav");
    private final View main = new View("main");
    private final View userActions = new View("user-actions");
    private final View expenseManagement = new View("expense-management");
    private final View estateInfo = new View("estate-info");

    public DemoContent() {
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
}