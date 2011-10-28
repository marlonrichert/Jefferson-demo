package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.UIElement;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

public final class EstatesPresentation extends Presentation {
    public EstatesPresentation() {
        define(SelectionControl.class, Table.class);

        define("main", VerticalSplitPanel.class);
        define("main", "main");

        define("properties", HorizontalLayout.class);

        define("tabs", TabSegment.class);
        define("root", "root");
        define("nav", "bar");
        define("estate-info", "bar");
        define("estates", "fill");
        define("expenses", "fill");
    }

    public void bar(UIElement content, Component component) {
        component.addStyleName("bar");
    }

    public void fill(UIElement content, Component component) {
        component.setSizeFull();
    }

    public void main(UIElement content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }

    public void root(UIElement content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }
}