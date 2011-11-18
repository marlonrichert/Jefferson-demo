package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.View;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

public class EstatesPresentation extends Presentation {
    public EstatesPresentation() {
        define(AbstractSelect.class, Table.class);
        define("Tabs", NativeSelect.class);

        define("Main", VerticalSplitPanel.class);
        define("Main", method("main"));

        define("Properties", HorizontalLayout.class);

        define("Root", method("root"));
        define("Navigation", method("bar"));
        define("Estate info", method("bar"));
        define("Estates", method("fill"));
        define("Expense", method("fill"));
    }

    void bar(View<?> content, Component component) {
        component.addStyleName("bar");
    }

    void fill(View<?> content, Component component) {
        component.setSizeFull();
    }

    void main(View<?> content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }

    void root(View<?> content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }
}