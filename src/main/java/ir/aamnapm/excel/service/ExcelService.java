package ir.aamnapm.excel.service;

import ir.aamnapm.excel.utils.ExcelGenerator;
import ir.aamnapm.excel.dto.PersonDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService<T> implements IExcelService<T> {

    @Override
    public Map generate(List<T> list, Class<T> zClass) {
        PersonDTO personDTO = new PersonDTO();
        personDTO.setName("ali");
        personDTO.setFamily("akbari");

        PersonDTO personDTO2 = new PersonDTO();
        personDTO2.setName("ali2");
        personDTO2.setFamily("akbari2");

        PersonDTO personDTO3 = new PersonDTO();
        personDTO3.setName("reza");
        personDTO3.setFamily("mohamadi");
        personDTO3.setAge(25);

        List<PersonDTO> personDTOS = new ArrayList<>();
        personDTOS.add(personDTO);
        personDTOS.add(personDTO2);
        personDTOS.add(personDTO3);

        return new ExcelGenerator(personDTOS, PersonDTO.class).generate();
    }
}
