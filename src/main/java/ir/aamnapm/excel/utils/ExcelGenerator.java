package ir.aamnapm.excel.utils;

import ir.aamnapm.excel.utils.Convert;
import ir.aamnapm.excel.utils.Style;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ExcelGenerator<T> {

    private List<T> list;
    private Class<T> zClass;
    private int rowIdx = 1;

    public ExcelGenerator(List<T> list, Class<T> zClass) {
        this.list = list;
        this.zClass = zClass;
    }

    public Map<String, Object> generate() {

        Map<String, Object> excelMap = Convert.objectListToExcelMap(list, zClass);

        List<String> excelHeaders = (List<String>) excelMap.get("HEADER");
        Map<Integer, Map<String, String>> excelValues = (Map<Integer, Map<String, String>>) excelMap.get("VALUE");

        if (excelMap == null || excelHeaders == null || excelValues == null) {
            return null;
//            throw new Exception(HRMException.ErrorType.InternalServerError, e.getCause() + "", "گزارش با مشکل مواجه شده است.");
        }

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = createSheet(workbook, excelHeaders);

        createHeaderCells(sheet, workbook, excelHeaders);

        createValueCells(sheet, workbook, excelValues, excelHeaders);

        return Convert.excelToByteArray(workbook, zClass.getSimpleName() + System.currentTimeMillis());
    }

    private Sheet createSheet(Workbook workbook, List<String> excelHeaders) {
        Sheet sheet = workbook.createSheet(zClass.getSimpleName());
        IntStream.range(0, excelHeaders.size()).forEach(value -> {
            sheet.setColumnWidth(value, 6000);
        });
        return sheet;
    }

    private void createValueCells(Sheet sheet, Workbook workbook, Map<Integer, Map<String, String>> excelValues, List<String> excelHeaders) {
        excelValues.forEach((integer, stringStringMap) -> {
            Row row = sheet.createRow(rowIdx++);
            IntStream.range(0, excelHeaders.size()).forEach(i -> {

                Cell cell = row.createCell(i);
                cell.setCellStyle(Style.styleValue(workbook));
                if (excelHeaders.get(i) != null) {
                    cell.setCellValue(stringStringMap.get(excelHeaders.get(i)));
                } else {
                    cell.setCellValue("");
                }
            });
        });
    }

    private void createHeaderCells(Sheet sheet, Workbook workbook, List<String> excelHeaders) {
        Row header = sheet.createRow(0);
        CellStyle headerStyle = Style.styleHeader(workbook);

        IntStream.range(0, excelHeaders.size()).forEach(i -> {
            Cell headerCell = header.createCell(i);
            headerCell.setCellValue(excelHeaders.get(i));
            headerCell.setCellStyle(headerStyle);
        });
    }
}
