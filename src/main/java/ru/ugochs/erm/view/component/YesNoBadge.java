package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.icon.VaadinIcon;
import org.vaadin.addons.badge.Badge;

public class YesNoBadge extends Badge {
    public YesNoBadge(boolean flag) {
        if (flag) {
            this.setVariant(Badge.BadgeVariant.SUCCESS);
            this.setIcon(VaadinIcon.CHECK.create());
        } else {
            this.setVariant(Badge.BadgeVariant.ERROR);
            this.setIcon(VaadinIcon.CLOSE_SMALL.create());
        }
    }
}
