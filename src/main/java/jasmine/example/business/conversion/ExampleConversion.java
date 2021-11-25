package jasmine.example.business.conversion;

import jasmine.example.business.dto.ExampleDTO;
import jasmine.example.persistence.model.ExampleDO;

/**
 * @author mh.z
 */
public class ExampleConversion {

    /**
     *
     * @param exampleDO
     * @return
     */
    public static ExampleDTO toExampleDTO(ExampleDO exampleDO) {
        ExampleDTO exampleDTO = new ExampleDTO();
        exampleDTO.setId(exampleDO.getId());
        exampleDTO.setExampleCode(exampleDO.getExampleCode());
        exampleDTO.setExampleName(exampleDO.getExampleName());

        return exampleDTO;
    }

}
