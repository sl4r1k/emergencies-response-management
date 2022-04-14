package ru.ugochs.erm.service;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ugochs.erm.exception.ApplicationIllegalArgumentException;

public class WorkbookWithSingleSheet {
    private final Workbook workbook;

    public WorkbookWithSingleSheet(XSSFWorkbook workbook) {
        this.workbook = workbook;
    }

    public Workbook validated() {
        if (this.workbook.getNumberOfSheets() > 1) {
            throw new ApplicationIllegalArgumentException(
                "В рабочей книге не должно быть больше одного листа"
            );
        }
        return this.workbook;
    }
}
