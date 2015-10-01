package com.canoo.cog.ui.city.util;

/*
 * #%L
 * code-of-gotham
 * %%
 * Copyright (C) 2015 Canoo Engineering AG
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import javafx.collections.ObservableList;
import javafx.scene.Node;

import com.canoo.cog.ui.city.model.AbstractElement;
import com.canoo.cog.ui.city.model.Building;
import com.canoo.cog.ui.city.model.City;
import com.canoo.cog.ui.city.model.Hood;

public class LayoutManager {

    public void moveCityToChildren(City city) {
        double xOffset = city.getShape().getWidth()/2;
        double zOffset = city.getShape().getWidth()/2;
        city.getShape().setTranslateX(-xOffset);
        city.getShape().setTranslateZ(-zOffset);
    }

    public void moveCityChildrenBackToCity(AbstractElement element) {
        ObservableList<Node> children = element.getChildren();
        for (Node child : children) {
            if (child instanceof AbstractElement) {
                AbstractElement item = (AbstractElement) child;
                double xOffset = item.getWidth()/2;
                double zOffset = item.getWidth()/2;
                item.getShape().setTranslateX(-xOffset);
                item.getShape().setTranslateZ(-zOffset);
                moveCityChildrenBackToCity(item);
            }
        }
    }

    public void correctYDirection(AbstractElement element) {
        ObservableList<Node> children = element.getChildren();
        for (Node child : children) {
            if (child instanceof AbstractElement) {
                AbstractElement abstractElement = (AbstractElement) child;
                double yOffset = 0;
                if(child instanceof Building) {
                    yOffset = abstractElement.getHeight() / 2;
                }
                if(child instanceof Hood) {
                    yOffset = abstractElement.getHeight();
                }
                abstractElement.setTranslateY(-yOffset);
                correctYDirection((AbstractElement) child);
            }
        }
    }

    public void setRelativeOffset(AbstractElement element) {
        ObservableList<Node> children = element.getChildren();
        for (Node child : children) {
            if (child instanceof AbstractElement) {
                AbstractElement item = (AbstractElement) child;
                double xOffset = item.getxOffset();
                double zOffset = item.getzOffset();
                item.setTranslateX(-zOffset);
                item.setTranslateZ(-xOffset);
                setRelativeOffset(item);
            }
        }
    }
}
