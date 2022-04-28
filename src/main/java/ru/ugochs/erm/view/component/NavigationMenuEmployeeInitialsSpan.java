package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.html.Span;
import ru.ugochs.erm.entity.Employee;

public class NavigationMenuEmployeeInitialsSpan extends Span {
    public NavigationMenuEmployeeInitialsSpan(Employee employee) {
        super(employee.getInitials());
        this.addClassNames(
            "font-medium",
            "text-s",
            "text-secondary"
        );
        new NavigationMenuLogoutContextMenu(this);
    }
}
