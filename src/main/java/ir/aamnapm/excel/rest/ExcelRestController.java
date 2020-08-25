package ir.aamnapm.excel.rest;

import ir.aamnapm.excel.service.IExcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ExcelRestController<T> {

    private final IExcelService iExcelService;

    @GetMapping(value = "/excel")
    public ResponseEntity<ByteArrayResource> excelGet() {
        Map generate = iExcelService.generate(null, null);

        String name = (String) generate.get("NAME");
        byte[] bytes = (byte[]) generate.get("CONTENT");

        return ResponseEntity
                .ok()
                .contentLength(bytes.length)
                .header("Content-type", "application/vnd.ms-excel")
                .header("Content-disposition", "attachment; filename=\"" + name + "." + "xlsx" + "\"")
                .body(new ByteArrayResource(bytes));
    }
}
