package ir.aamnapm.excel.utils;

import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Convert {

    public static <T> Map<String, Object> objectListToExcelMap(List<T> list, Class<T> clazz) {
        Map<String, Object> excelMap = new HashMap<>();
        Map<Integer, Map<String, String>> excelValues = new HashMap<>();
        List<String> excelHeaders = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Map<String, String> jsonElementsMap = new HashMap<>();
            for (Field field : clazz.getDeclaredFields()) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Excel.class)) {
                    try {
                        jsonElementsMap.put(getKey(field), "0".equals(String.valueOf(field.get(list.get(i)))) ? "" : String.valueOf(field.get(list.get(i))));
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
//                        throw new ;
                        return null;
                    }
                    if (i == 0)
                        excelHeaders.add(getKey(field));
                }
            }
            excelValues.put(i, jsonElementsMap);
        }
        excelMap.put("VALUE", excelValues);
        excelMap.put("HEADER", excelHeaders);

        return excelMap;
    }

    private static String getKey(Field field) {
        String value = field.getAnnotation(Excel.class).header();
        return value.isEmpty() ? field.getName() : value;
    }


    public static Map<String, Object> excelToByteArray(Workbook workbook, String excelName) {
        try {
            Map<String, Object> map = new HashMap<>();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            workbook.write(out);
            workbook.close();

            map.put("DATA", out.toByteArray());
            map.put("NAME", excelName);
            return map;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
//            throw new Exception(Exception.ErrorType.InternalServerError, e.getCause() + "", "گزارش با مشکل مواجه شده است.");
        }
    }
}
