package org.vaadin.jefferson.demo;

import com.vaadin.Application;
import com.vaadin.ui.Window;

public class CustomersDemo extends Application {

    @Override
    public void init() {
        setTheme("jefferson-demo");
        Window mainWindow = new Window("Customers Demo");
        setMainWindow(mainWindow);

        CustomersContent content = new CustomersContent();
        CustomersPresentation presentation = new CustomersPresentation();

        try {
            mainWindow.setContent(presentation.visit(content));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }
    }
}
