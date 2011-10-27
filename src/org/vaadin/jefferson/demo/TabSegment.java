package org.vaadin.jefferson.demo;

import java.util.Iterator;
import java.util.LinkedList;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;

public class TabSegment extends HorizontalLayout {

    private final LinkedList<Button> buttons = new LinkedList<Button>();

    public TabSegment() {
        addStyleName("segment");
        addButton(new Button("Open"));
        addButton(new Button("Ongoing"));
        buttons.getFirst().addStyleName("down");
    }

    public TabSegment addButton(Button b) {
        buttons.add(b);
        addComponent(b);
        b.addListener(new Button.ClickListener() {
            public void buttonClick(ClickEvent event) {
                for (Button button : buttons) {
                    button.removeStyleName("down");
                }
                event.getButton().addStyleName("down");
            }
        });
        updateButtonStyles();
        return this;
    }

    private void updateButtonStyles() {
        int i = 0;
        Component c = null;
        for (Iterator<Component> iterator = getComponentIterator(); iterator
                .hasNext();) {
            c = iterator.next();
            c.removeStyleName("first");
            c.removeStyleName("last");
            if (i == 0) {
                c.addStyleName("first");
            }
            i++;
        }
        if (c != null) {
            c.addStyleName("last");
        }
    }
}