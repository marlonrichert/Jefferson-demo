package org.vaadin.jefferson.demo.addressbook;

import java.util.HashMap;
import java.util.Map;

import org.vaadin.jefferson.Presentation;
import org.vaadin.jefferson.View;
import org.vaadin.jefferson.content.ButtonView;
import org.vaadin.jefferson.content.SimpleComposite;
import org.vaadin.jefferson.demo.addressbook.ui.AddressBookContent;
import org.vaadin.jefferson.demo.addressbook.ui.AddressBookContent.MainView;
import org.vaadin.jefferson.demo.addressbook.ui.ListView;
import org.vaadin.jefferson.demo.addressbook.ui.PersonForm;
import org.vaadin.jefferson.demo.addressbook.ui.PersonList;
import org.vaadin.jefferson.demo.addressbook.ui.SearchView;
import org.vaadin.jefferson.demo.addressbook.ui.TreeView;

import com.vaadin.terminal.Sizeable;
import com.vaadin.terminal.ThemeResource;
import com.vaadin.ui.AbstractOrderedLayout;
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
import com.vaadin.ui.Panel;
import com.vaadin.ui.Table;
import com.vaadin.ui.Tree;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.VerticalSplitPanel;
import com.vaadin.ui.themes.ChameleonTheme;

public class AddressBookPresentation extends Presentation {
    private static final Map<String, String> icons = new HashMap<String, String>() {
        {
            put(AddressBookContent.LOGO, "images/logo.png");
            put(AddressBookContent.SEARCH, "icons/32/folder-add.png");
            put(AddressBookContent.SHARE, "icons/32/users.png");
            put(AddressBookContent.HELP, "icons/32/help.png");
            put("Add contact", "icons/32/document-add.png");
        }
    };

    @Override
    protected void style(View<?> view) {
        super.style(view);
        Component rendition = view.getRendition();

        if (rendition instanceof Embedded) {
            ((Embedded) rendition).setSource(new ThemeResource(icons.get(view
                    .getName())));
        }

        rendition.setCaption(null);

        Component parentRendition = view.getParent().getRendition();
        if (parentRendition instanceof AbstractOrderedLayout) {
            AbstractOrderedLayout layout = (AbstractOrderedLayout) parentRendition;
            layout.setComponentAlignment(rendition, Alignment.MIDDLE_RIGHT);
            layout.setExpandRatio(rendition, 1);
        }
    }

    Component create(AddressBookContent view) {
        return new VerticalLayout();
    }

    void style(AddressBookContent view) {
        super.style(view);
        view.getRendition().setSizeFull();
    }

    Component create(ButtonView view) {
        return new Button();
    }

    void style(ButtonView view) {
        super.style(view);
        Button rendition = view.getRendition();
        rendition.setCaption(view.getName());
        rendition.setIcon(
                new ThemeResource(icons.get(view.getName())));
        rendition.addStyleName(ChameleonTheme.BUTTON_BORDERLESS);
        // rendition.addStyleName(ChameleonTheme.BUTTON_ICON_ON_TOP);

        Component parentRendition = view.getParent().getRendition();
        if (parentRendition instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) parentRendition).setExpandRatio(
                    rendition, 0);
        }
    }

    Component create(ListView view) {
        return new VerticalSplitPanel();
    }

    void style(ListView view) {
        super.style(view);
        ComponentContainer rendition = view.getRendition();
        rendition.addStyleName("view");

        if (rendition instanceof AbstractSplitPanel) {
            ((AbstractSplitPanel) rendition).setSplitPosition(40);
        }

        rendition.setSizeFull();
    }

    Component create(MainView view) {
        return new HorizontalSplitPanel();
    }

    void style(MainView view) {
        super.style(view);
        Component rendition = view.getRendition();

        if (rendition instanceof AbstractSplitPanel) {
            ((AbstractSplitPanel) rendition).setSplitPosition(
                    200, Sizeable.UNITS_PIXELS);
        }

        rendition.setSizeFull();

        Component parentRendition = view.getParent().getRendition();
        if (parentRendition instanceof AbstractOrderedLayout) {
            ((AbstractOrderedLayout) parentRendition).setExpandRatio(
                    rendition, 1);
        }
    }

    Component create(PersonForm view) {
        Form rendition = new Form();
        rendition.setFooter(new HorizontalLayout());
        return rendition;
    }

    void style(PersonForm view) {
        Form rendition = view.getRendition();
        Layout footer = rendition.getFooter();
        if (footer instanceof AbstractOrderedLayout) {
            ((HorizontalLayout) footer).setSpacing(true);
        }
    }

    void style(PersonList view) {
        super.style(view);
        Table rendition = view.getRendition();

        rendition.addStyleName(ChameleonTheme.TABLE_BORDERLESS);
        rendition.addStyleName(ChameleonTheme.TABLE_SMALL);
        rendition.addStyleName(ChameleonTheme.TABLE_STRIPED);
        rendition.addStyleName("strong");

        rendition.setSizeFull();
        rendition.setColumnCollapsingAllowed(true);
        rendition.setColumnReorderingAllowed(true);
    }

    void style(SearchView view) {
        super.style(view);
        ComponentContainer rendition = view.getRendition();
        rendition.addStyleName("view");
        rendition.setSizeFull();

        if (rendition instanceof Panel) {
            ((Panel) rendition).setContent(new FormLayout());
        }
    }

    Component create(SimpleComposite view) {
        return new CssLayout();
    }

    void style(SimpleComposite view) {
        super.style(view);
        ComponentContainer rendition = view.getRendition();

        if (rendition instanceof AbstractOrderedLayout) {
            AbstractOrderedLayout layout = (AbstractOrderedLayout) rendition;
            layout.setMargin(true);
            layout.setSpacing(true);
        }

        rendition.setWidth("100%");
    }

    void style(TreeView view) {
        super.style(view);
        Tree rendition = view.getRendition();
        rendition.addStyleName(
                ChameleonTheme.COMPOUND_LAYOUT_SIDEBAR_MENU);
        rendition.setSizeFull();
    }
}
