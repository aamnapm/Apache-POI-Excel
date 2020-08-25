package ir.aamnapm.excel.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Style {

    public static CellStyle styleHeader(Workbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setFont(fontHeader((XSSFWorkbook) workbook));
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        return headerStyle;
    }

    public static CellStyle styleValue(Workbook workbook) {
        CellStyle valueStyle = workbook.createCellStyle();
        valueStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        valueStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        valueStyle.setBorderBottom(BorderStyle.THIN);
        valueStyle.setBorderTop(BorderStyle.THIN);
        valueStyle.setBorderLeft(BorderStyle.THIN);
        valueStyle.setBorderRight(BorderStyle.THIN);
        valueStyle.setFont(fontValue((XSSFWorkbook) workbook));
        valueStyle.setAlignment(HorizontalAlignment.CENTER);
        return valueStyle;
    }

    private static XSSFFont fontHeader(XSSFWorkbook workbook) {
        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 12);
        font.setBold(true);
        return font;
    }

    private static XSSFFont fontValue(XSSFWorkbook workbook) {
        XSSFFont font = (workbook).createFont();
        font.setFontName("Arial");
        font.setFontHeightInPoints((short) 10);
        font.setBold(false);
        return font;
    }
}
