package ru.ugochs.erm.view.util;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import com.vaadin.flow.server.VaadinSession;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.j2ee.servlets.ImageServlet;
import org.apache.commons.lang3.function.TriFunction;
import org.vaadin.reports.PrintPreviewReport;
import java.util.HashMap;
import java.util.List;

public class TemplatePrintPreviewReport<T> extends PrintPreviewReport<T> {
    private final String path;
    private final HashMap<String, Object> parameters;
    private final TriFunction<? super List<? extends T>, DynamicReport, HashMap<String, Object>, JasperPrint> print;

    public TemplatePrintPreviewReport(
        String path,
        HashMap<String, Object> parameters,
        TriFunction<? super List<? extends T>, DynamicReport, HashMap<String, Object>, JasperPrint> print
    ) {
        this.path = path;
        this.parameters = parameters;
        this.print = print;
    }

    @Override
    protected DynamicReportBuilder buildReportBuilder() {
        return super.buildReportBuilder()
            .setTemplateFile(this.path, true, true, true, true);
    }

    @Override
    protected JasperPrint buildJasperPrint(List<? extends T> items, DynamicReport report) throws JRException {
        JasperPrint jp = this.print.apply(items, report, this.parameters);
        VaadinSession.getCurrent()
            .getSession()
            .setAttribute(
                ImageServlet.DEFAULT_JASPER_PRINT_SESSION_ATTRIBUTE,
                jp
            );
        return jp;
    }
}
