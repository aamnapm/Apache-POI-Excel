package ir.aamnapm.excel.service;

import java.util.List;
import java.util.Map;

public interface IExcelService<T> {
    Map<String, Object> generate(List<T> list, Class<T> zClass);
}
