package jasmine.demo.example.business.adapter;

import jasmine.demo.example.business.dto.ExampleDTO;
import jasmine.demo.example.persistence.entity.ExampleDO;

/**
 * @author mh.z
 */
public class ExampleAdapter {

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
