package ru.ugochs.erm.view.component;

import com.vaadin.flow.component.applayout.AppLayout;
import ru.ugochs.erm.configuration.security.AuthenticatedEmployee;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.view.*;
import ru.ugochs.erm.view.util.AccessibleNavigationMenuItems;

public class NavigationMenu extends AppLayout {
    public NavigationMenu(Db db) {
        this.addToNavbar(
            new NavigationMenuHeader(
                new NavigationMenuLayoutDiv(
                    new NavigationMenuAppNameH1(
                        "Управление по делам ГО и ЧС"
                    ),
                    new NavigationMenuEmployeeInitialsSpan(
                        new AuthenticatedEmployee(db)
                    )
                ),
                new NavigationMenuNav(
                    new AccessibleNavigationMenuItems(
                        new NavigationMenuItem(EmergencyView.class, "lab la-hotjar"),
                        new NavigationMenuItem(IndexView.class, "la la-tags"),
                        new NavigationMenuItem(ReporterView.class, "la la-user-secret"),
                        new NavigationMenuItem(ServiceView.class, "la la-ambulance"),
                        new NavigationMenuItem(DistrictView.class, "la la-map-marked"),
                        new NavigationMenuItem(StreetView.class, "la la-map-marker"),
                        new NavigationMenuItem(EmployeeView.class, "la la-user")
                    )
                )
            )
        );
    }
}
