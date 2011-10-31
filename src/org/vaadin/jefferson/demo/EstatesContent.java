package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.LabelControl;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.View;

public class EstatesContent extends View {
    private static final String ROOT = "root";
    private static final String NAV = "nav";
    private static final String MAIN = "main";
    private static final String DETAILS = "details";
    private static final String ESTATE_INFO = "estate-info";
    private static final String PROPERTIES = "properties";
    private static final String EXPENSE_MANAGEMENT = "expense-management";

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
        super(ROOT);

        setChildren(
                view(NAV,
                        title, tabs, newEstate,
                        view("user-actions", profile, signOut)),
                view(MAIN,
                        estates,
                        view(DETAILS,
                                view(ESTATE_INFO,
                                        name, open,
                                        view(PROPERTIES,
                                                max, date, state)),
                                view(EXPENSE_MANAGEMENT,
                                        expenses, addExpense))));
    }

    public View getPropertiesView() {
        return getView(PROPERTIES);
    }

    public SelectionControl getEstatesControl() {
        return estates;
    }
}