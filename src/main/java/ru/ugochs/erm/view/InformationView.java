package ru.ugochs.erm.view;

import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.vaadin.reports.PrintPreviewReport;
import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAllEmergenciesByPeriod;
import ru.ugochs.erm.view.component.NavigationMenu;
import ru.ugochs.erm.view.util.*;
import javax.annotation.security.RolesAllowed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@RolesAllowed({"ADMIN", "USER"})
@Route(value = "information", layout = NavigationMenu.class)
public class InformationView extends VerticalLayout implements BeforeEnterObserver {
    private final Db db;

    public InformationView(Db db) {
        this.db = db;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, String> parameters = new QueryParametersAsSimpleMap(event.getLocation().getQueryParameters());
        Map<District, List<Emergency>> emergenciesByDistricts = new GetAllEmergenciesByPeriod(
            LocalDate.parse(parameters.get("from")),
            LocalDate.parse(parameters.get("to")),
            this.db
        ).perform().stream()
            .collect(
                Collectors.groupingBy(
                    emergency -> emergency.getStreet().getDistrict(),
                    Collectors.mapping(
                        Function.identity(),
                        Collectors.toList()
                    )
                )
            );
        List<InformationData> data = new ArrayList<>();
        int e = 1;
        for (Map.Entry<District, List<Emergency>> entry : emergenciesByDistricts.entrySet()) {
            int d = 1;
            for (Emergency emergency : entry.getValue()) {
                data.add(new InformationData(emergency, e++, d++));
            }
        }
        PrintPreviewReport<InformationData> report = new TemplatePrintPreviewReport<>(
            this.getClass()
                .getClassLoader()
                .getResource("templates/information.jrxml")
                .getPath(),
            new HashMap<>(
                Map.of(
                    "period", new Period(
                        LocalDate.parse(parameters.get("from")),
                        LocalDate.parse(parameters.get("to"))
                    ).text(),
                    "asOf", String.format(
                        "по состоянию на %s",
                        new LocalDateTimeAsRussianString(
                            LocalDateTime.now()
                        )
                    )
                )
            ),
            (dat, rep, params) -> {
                JRDataSource dataSource = new JRBeanCollectionDataSource(dat);
                params.put("REPORT_DATA_SOURCE", dataSource);
                try {
                    return JasperFillManager.fillReport(
                        JasperCompileManager.compileReport(this.getClass()
                            .getClassLoader()
                            .getResource("templates/information.jrxml")
                            .getPath()),
                        params,
                        dataSource
                    );
                } catch (JRException ex) {
                    throw new RuntimeException(ex);
                }
            }
        );
        report.setItems(data);
        add(
            new HorizontalLayout(
                new Anchor(
                    report.getStreamResource(
                        "Информация.docx",
                        () -> data,
                        TemplatePrintPreviewReport.Format.DOCX
                    ),
                    "Загрузить"
                )
            ),
            report
        );
    }
}
