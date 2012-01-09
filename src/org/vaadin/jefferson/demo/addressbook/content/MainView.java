package org.vaadin.jefferson.demo.addressbook.content;

import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.SimpleComposite;

import com.vaadin.ui.Component;

public class MainView extends SimpleComposite {
    private View<?> contentView;

    public MainView(TreeView tree, View<?> contentView) {
        super("Main");
        this.contentView = contentView;

        setChildren(
                tree,
                contentView);
    }

    protected void setContentView(View<? extends Component> contentView) {
        replaceChild(this.contentView, contentView);
        this.contentView = contentView;
    }
}