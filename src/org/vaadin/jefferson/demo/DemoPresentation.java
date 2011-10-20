package org.vaadin.jefferson.demo;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.LabelControl;
import org.vaadin.jefferson.content.SelectionControl;
import org.vaadin.jefferson.content.UIElement;

import com.vaadin.terminal.Sizeable;
import com.vaadin.ui.Component;
import com.vaadin.ui.Table;
import com.vaadin.ui.VerticalSplitPanel;

public final class DemoPresentation extends Presentation {
    public DemoPresentation() {
        define(SelectionControl.class, Table.class);

        define("main", VerticalSplitPanel.class);
        define("tabs", TabSegment.class);

        define(ButtonControl.class, "control");
        define(LabelControl.class, "control");

        define("root", "root");
        define("nav", "bar");
        define("estate-info", "bar");
        define("main", "main");
    }

    public void bar(UIElement content, Component component) {
        component.addStyleName("bar");
    }

    public void control(UIElement content, Component component) {
        component.setCaption(content.getName());
    }

    public void main(UIElement content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }

    public void root(UIElement content, Component component) {
        component.setHeight(100, Sizeable.UNITS_PERCENTAGE);
    }
}