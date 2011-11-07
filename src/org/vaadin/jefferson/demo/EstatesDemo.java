/*
 * Copyright 2011 Vaadin Ltd.
 * 
 * Licensed under the GNU Affero General Public License, Version 2 (the 
 * "License"); you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at
 * 
 * http://www.gnu.org/licenses/agpl.html
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.vaadin.jefferson.demo;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.vaadin.jefferson.content.UIElement;

import com.vaadin.Application;
import com.vaadin.data.Property;
import com.vaadin.data.util.IndexedContainer;
import com.vaadin.ui.AbstractSelect;
import com.vaadin.ui.Component;
import com.vaadin.ui.Window;

public class EstatesDemo extends Application {
    private final Map<String, String> propertyCaptions = new HashMap<String, String>();
    private final Map<String, String> propertyValues = new HashMap<String, String>();

    public EstatesDemo() {
        propertyCaptions.put("max", "Greater value of assets and debit");
        propertyCaptions.put("date", "Filed for bankruptcy");
        propertyCaptions.put("state", "State");

        propertyValues.put("max", "241 959,01 €");
        propertyValues.put("date", "1.1.2010");
        propertyValues.put("state", "Canceled");
    }

    @Override
    public void init() {
        setTheme("jefferson-demo");
        Window mainWindow = new Window("Estates Demo");
        setMainWindow(mainWindow);

        EstatesContent content = new EstatesContent();
        EstatesPresentation presentation = new EstatesPresentation();

        try {
            mainWindow.setContent(presentation.render(content));
        } catch (Exception e) {
            throw new ExceptionInInitializerError(e);
        }

        for (UIElement<?> property : content.getPropertiesView().getChildren()) {
            Component component = property.getRendition();
            String name = property.getName();
            component.setCaption(propertyCaptions.get(name));
            ((Property) component).setValue(propertyValues.get(name));
        }

        AbstractSelect estates = content.getEstatesControl().getRendition();
        estates.setContainerDataSource(
                new IndexedContainer(Arrays.asList("A", "B", "C")));
    }
}
