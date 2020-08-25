package ir.aamnapm.excel.dto;

import ir.aamnapm.excel.utils.Excel;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PersonDTO {

    @Excel(nameFa = "نام")
    private String name;

    @Excel(nameFa = "نام خانوادگی")
    private String family;

    @Excel(nameFa = "سن")
    private int age;
}
