package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.content.TextControl;

import com.vaadin.ui.AbstractTextField;

public class SearchName extends TextControl {

    private boolean visible = true;

    public SearchName() {
        super("Search name");
    }

    @Override
    protected AbstractTextField accept(Presentation presentation) {
        AbstractTextField rendition = super.accept(presentation);
        setVisible(visible);
        return rendition;
    }

    public void setVisible(boolean visible) {
        AbstractTextField rendition = getRendition();
        if (rendition != null) {
            rendition.setVisible(visible);
        }
        this.visible = visible;
    }
}
