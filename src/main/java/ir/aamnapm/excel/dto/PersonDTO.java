package ir.aamnapm.excel.dto;

import ir.aamnapm.excel.utils.Excel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {

    @Excel(header = "نام")
    private String name;

    @Excel(header = "نام خانوادگی")
    private String family;

    @Excel(header = "سن")
    private int age;
}
