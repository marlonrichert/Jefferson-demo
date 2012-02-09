package org.vaadin.jefferson.demo.addressbook;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.jefferson.Composite;
import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonControl;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.content.AddressBookView;
import org.vaadin.jefferson.demo.addressbook.content.ListView;
import org.vaadin.jefferson.demo.addressbook.content.MainView;
import org.vaadin.jefferson.demo.addressbook.content.PersonForm;
import org.vaadin.jefferson.demo.addressbook.content.PersonList;
import org.vaadin.jefferson.demo.addressbook.content.SearchView;
import org.vaadin.jefferson.demo.addressbook.content.Toolbar;
import org.vaadin.jefferson.demo.addressbook.content.TreeView;

import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.AbstractSplitPanel;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.ComponentContainer;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Embedded;
import com.vaadin.ui.Form;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.HorizontalSplitPanel;
import com.vaadin.ui.Layout;
import com.vaadin.ui.NativeButton;
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.ChameleonTheme;

public class AddressBookPresentation extends Presentation {
    private static final Map<String, String> icons = new HashMap<String, String>() {
        {
            put(Toolbar.ADD_CONTACT, "icons/32/document-add.png");
            put(Toolbar.LOGO, "images/logo.png");
            put(Toolbar.SEARCH, "icons/32/folder-add.png");
            put(Toolbar.SHARE, "icons/32/users.png");
            put(Toolbar.HELP, "icons/32/help.png");
        }
    };

    @Override
    protected void style(View<?> view) {
        super.style(view);
        Component rendition = getRendition(view);

        if (rendition instanceof Embedded) {
            ((Embedded) rendition).setSource(new ThemeResource(icons.get(view
                    .getName())));
        }

        rendition.setCaption(null);

        Component parentRendition = getRendition(view.getParent());
        if (parentRendition instanceof AbstractOrderedLayout) {
            AbstractOrderedLayout layout = (AbstractOrderedLayout) parentRendition;
            layout.setComponentAlignment(rendition, Alignment.MIDDLE_RIGHT);
            layout.setExpandRatio(rendition, 1);
        }
    }

    void render(AddressBookView view) {
        setRendition(view, new VerticalLayout());
    }

    void style(AddressBookView view) {
        super.style(view);
        getRendition(view).setSizeFull();
    }

    void render(ButtonControl view) {
        setRendition(view, view.getParent() instanceof Toolbar
                ? new Button()
                : new NativeButton());
    }

    void style(ButtonControl view) {
        super.style(view);
        Button rendition = getRendition(view);
        rendition.setCaption(view.getName());

        Composite<?> parent = view.getParent();

        if (parent instanceof Toolbar) {
            rendition.setIcon(new ThemeResource(icons.get(view.getName())));
            rendition.addStyleName(ChameleonTheme.BUTTON_BORDERLESS);
        }

        Component parentRendition = getRendition(parent);
        if (parentRendition instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) parentRendition).setExpandRatio(
                    rendition, 0);
        }
    }

    void render(ListView view) {
        setRendition(view, new VerticalSplitPanel());
    }

    void style(ListView view) {
        super.style(view);
        ComponentContainer rendition = getRendition(view);

        if (rendition instanceof AbstractSplitPanel) {
            ((AbstractSplitPanel) rendition).setSplitPosition(40);
        }

        rendition.setSizeFull();
    }

    void render(MainView view) {
        setRendition(view, new HorizontalSplitPanel());
    }

    void style(MainView view) {
        super.style(view);
        Component rendition = getRendition(view);

        if (rendition instanceof AbstractSplitPanel) {
            ((AbstractSplitPanel) rendition).setSplitPosition(
                    200, Sizeable.UNITS_PIXELS);
        }

        rendition.setSizeFull();

        Component parentRendition = getRendition(view.getParent());
        if (parentRendition instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) parentRendition).setExpandRatio(
                    rendition, 1);
        }
    }

    void render(PersonForm view) {
        Form rendition = new Form();
        rendition.setFooter(new HorizontalLayout());
        setRendition(view, rendition);
    }

    void style(PersonForm view) {
        Form rendition = getRendition(view);
        Layout footer = rendition.getFooter();
        if (footer instanceof AbstractOrderedLayout) {
            ((HorizontalLayout) footer).setSpacing(true);
        }
    }

    void style(PersonList view) {
        super.style(view);
        AbstractSelect rendition = getRendition(view);

        rendition.addStyleName(ChameleonTheme.TABLE_BORDERLESS);
        rendition.addStyleName(ChameleonTheme.TABLE_STRIPED);
        rendition.addStyleName("strong");

        rendition.setSizeFull();

        if (rendition instanceof Table) {
            Table table = (Table) rendition;
            table.setColumnCollapsingAllowed(true);
            table.setColumnReorderingAllowed(true);
        }
    }

    void render(SearchView view) {
        Panel rendition = new Panel();
        rendition.setContent(new FormLayout());
        setRendition(view, rendition);
    }

    void style(SearchView view) {
        super.style(view);
        ComponentContainer rendition = getRendition(view);
        rendition.setSizeFull();

        rendition.setCaption(view.getName());
        for (View<?> child : view.getChildren()) {
            getRendition(child).setCaption(child.getName());
        }
    }

    void render(SimpleComposite view) {
        setRendition(view, new CssLayout());
    }

    void style(SimpleComposite view) {
        super.style(view);
        ComponentContainer rendition = getRendition(view);

        if (rendition instanceof AbstractOrderedLayout) {
            AbstractOrderedLayout layout = (AbstractOrderedLayout) rendition;
            layout.setMargin(true);
            layout.setSpacing(true);
        }

        rendition.setWidth("100%");
    }

    void style(TreeView view) {
        super.style(view);
        Tree rendition = getRendition(view);
        rendition.addStyleName(
                ChameleonTheme.COMPOUND_LAYOUT_SIDEBAR_MENU);
        rendition.setSizeFull();
    }
}
