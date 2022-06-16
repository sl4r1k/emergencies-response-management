package ru.ugochs.erm.view;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.*;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.StyleBuilder;
import ar.com.fdvs.dj.domain.constants.*;
import ar.com.fdvs.dj.domain.constants.Font;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.vaadin.reports.PrintPreviewReport;
import ru.ugochs.erm.entity.Emergency;
import ru.ugochs.erm.service.crud.Db;
import ru.ugochs.erm.service.crud.GetAllEmergenciesByPeriod;
import ru.ugochs.erm.view.util.*;
import javax.annotation.security.RolesAllowed;
import java.awt.*;
import java.time.LocalDate;
import java.util.*;
import java.util.List;

@RolesAllowed({"ADMIN", "USER"})
@Route("report")
public class ReportView extends VerticalLayout implements BeforeEnterObserver {
    private final Db db;

    public ReportView(Db db) {
        this.db = db;
    }

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        Map<String, String> parameters = new QueryParametersAsSimpleMap(event.getLocation().getQueryParameters());
        List<Emergency> emergencies = new GetAllEmergenciesByPeriod(
            LocalDate.parse(parameters.get("from")),
            LocalDate.parse(parameters.get("to")),
            this.db
        ).perform();
        List<ReportData> data = new ArrayList<>();
        emergencies.forEach(emergency -> {
            data.add(new ReportData(emergency, ReportDataIdentifier.EMERGENCIES));
            data.add(new ReportData(emergency, ReportDataIdentifier.SERVICES));
        });
        Style totalHeaderStyle = new StyleBuilder(false)
            .setHorizontalAlign(HorizontalAlign.CENTER)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setFont(Font.ARIAL_MEDIUM_BOLD)
            .setTextColor(Color.BLUE)
            .build();
        Style colAndRowHeaderStyle = new StyleBuilder(false)
            .setHorizontalAlign(HorizontalAlign.CENTER)
            .setVerticalAlign(VerticalAlign.TOP)
            .setFont(Font.ARIAL_MEDIUM_BOLD)
            .build();
        Style mainHeaderStyle = new StyleBuilder(false)
            .setHorizontalAlign(HorizontalAlign.CENTER)
            .setVerticalAlign(VerticalAlign.MIDDLE)
            .setFont(Font.ARIAL_BIG_BOLD)
            .setTextColor(Color.BLACK)
            .build();
        Style totalStyle = new StyleBuilder(false).setPattern("#,###.##")
            .setHorizontalAlign(HorizontalAlign.CENTER)
            .setFont(Font.ARIAL_MEDIUM_BOLD)
            .build();
        DJCrosstab djcross = new CrosstabBuilder()
            .setHeaderStyle(mainHeaderStyle)
            .setDatasource("sr", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION)
            .setUseFullWidth(true)
            .setColorScheme(DJConstants.COLOR_SCHEMA_WHITE)
            .setCellBorder(Border.PEN_1_POINT())
            .addRow("Индекс", "index", String.class.getName(), false)
            .addColumn("Район","district", String.class.getName(), false)
            .addColumn("Идентификатор", "identifier", String.class.getName(), false)
            .addMeasure("quantity", Long.class.getName(), DJCalculation.SUM, "Количество", null)
            .setCellDimension(34, 40)
            .setColumnHeaderHeight(30)
            .setRowStyles(colAndRowHeaderStyle, totalStyle, totalHeaderStyle)
            .setColumnStyles(colAndRowHeaderStyle, totalStyle, totalHeaderStyle)
            .setRowHeaderWidth(80)
            .build();
        PrintPreviewReport<ReportData> report = new TemplatePrintPreviewReport<>(
            this.getClass()
                .getClassLoader()
                .getResource("templates/report.jrxml")
                .getPath(),
            new HashMap<>(
                Map.of(
                    "date", new Period(
                        LocalDate.parse(parameters.get("from")),
                        LocalDate.parse(parameters.get("to"))
                    ).text()
                )
            ),
            (dat, rep, params) -> {
                params.put("sr", dat);
                JRDataSource ds = new JRBeanCollectionDataSource(List.of(new Object()));
                JasperReport jr;
                try {
                    jr = DynamicJasperHelper.generateJasperReport(rep, new ClassicLayoutManager(), params);
                } catch (JRException e) {
                    throw new RuntimeException(e);
                }
                try {
                    return JasperFillManager.fillReport(jr, params, ds);
                } catch (JRException e) {
                    throw new RuntimeException(e);
                }
            }
        );
        report.getReportBuilder().setUseFullPageWidth(true)
            .setPageSizeAndOrientation(Page.Page_A4_Landscape())
            .setWhenNoData("(no data)", new Style(), true, true)
            .setTemplateFile("templates/report.jrxml")
            .setPrintColumnNames(true)
            .setIgnorePagination(true)
            .addFooterCrosstab(djcross);
        report.setItems(data);
        add(
            new HorizontalLayout(
                new Anchor(
                    report.getStreamResource(
                        "Донесение.docx",
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
