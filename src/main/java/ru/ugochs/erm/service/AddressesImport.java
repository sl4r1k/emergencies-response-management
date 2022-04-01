package ru.ugochs.erm.service;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ru.ugochs.erm.entity.District;
import ru.ugochs.erm.entity.Street;
import ru.ugochs.erm.service.crud.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressesImport extends XlsImport {
    private final Sheet sheet;

    @SneakyThrows(IOException.class)
    public AddressesImport(InputStream stream, Db db) {
        super(db);
        try (
            Workbook workbook = new WorkbookWithSingleSheet(
                new XSSFWorkbook(stream)
            ).validated()
        ) {
            this.sheet = workbook.getSheetAt(0);
        }
    }

    @Override
    public void act() {
        List<District> districts = new AddAllDistricts(
            Stream.iterate(0, i -> i < this.sheet.getLastRowNum(), i -> ++i)
                .map(this.sheet::getRow)
                .map(row -> row.getCell(1))
                .map(Cell::getStringCellValue)
                .distinct()
                .map(District::new)
                .collect(Collectors.toList()),
            this.db
        ).perform();
        for (int i = 0; i <= this.sheet.getLastRowNum(); i++) {
            Row row = this.sheet.getRow(i);
            new AddStreet(
                new Street(
                    new GetDistrictByNameInMemory(
                        districts,
                        row.getCell(1).getStringCellValue()
                    ).perform().get(),
                    row.getCell(0).getStringCellValue()
                ),
                this.db
            ).perform();
        }
    }
}
