package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.UIElement;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.NativeSelect;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

public class EstatesPresentation extends Presentation {
    public EstatesPresentation() {
        define(SelectionControl.class, Table.class);
        define("tabs", NativeSelect.class);

        define("main", VerticalSplitPanel.class);
        define("main", method("main"));

        define("properties", HorizontalLayout.class);

        define("root", method("root"));
        define("nav", method("bar"));
        define("estate-info", method("bar"));
        define("estates", method("fill"));
        define("expenses", method("fill"));
    }

    void bar(UIElement<?> content, Component component) {
        component.addStyleName("bar");
    }

    void fill(UIElement<?> content, Component component) {
        component.setSizeFull();
    }

    void main(UIElement<?> content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }

    void root(UIElement<?> content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }
}