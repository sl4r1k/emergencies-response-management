package ru.ugochs.erm.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class WorkbookWithSingleSheet {
    private final Workbook workbook;

    public WorkbookWithSingleSheet(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public Workbook validated() {
        if (this.workbook.getNumberOfSheets() > 1) {
            throw new IllegalArgumentException(
                "В рабочей книге не должно быть больше одного листа"
            );
        }
        return this.workbook;
    }
}
