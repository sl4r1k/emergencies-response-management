package ru.ugochs.erm.view;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteParameters;
import ru.ugochs.erm.entity.AbstractEntity;
import java.util.Map;

public class NavigationByEntity extends Navigation {
    private final AbstractEntity entity;

    public NavigationByEntity(AbstractEntity entity, Class<? extends Component> componentType) {
        super(componentType);
        this.entity = entity;
    }

    @Override
    public void perform() {
        UI.getCurrent().navigate(
                this.componentType,
                new RouteParameters(Map.of("id", this.entity.getId().toString()))
        );
    }
}
