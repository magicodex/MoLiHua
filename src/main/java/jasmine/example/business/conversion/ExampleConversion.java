package jasmine.example.business.conversion;

import jasmine.example.business.dto.ExampleDTO;
import jasmine.example.persistence.model.ExampleDO;

public class ExampleConversion {

    public static ExampleDTO toExampleDTO(ExampleDO exampleDO) {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setId(exampleDO.getId());
        exampleDTO.setExampleCode(exampleDO.getExampleCode());
        exampleDTO.setExampleName(exampleDO.getExampleName());

        return exampleDTO;
    }

}
