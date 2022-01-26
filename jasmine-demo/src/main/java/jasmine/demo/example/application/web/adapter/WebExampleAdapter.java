package jasmine.demo.example.application.web.adapter;

import jasmine.demo.example.application.web.dto.WebExampleDTO;
import jasmine.demo.example.business.dto.ExampleDTO;

/**
 * @author mh.z
 */
public class WebExampleAdapter {

    /**
     *
     * @param exampleDTO
     * @return
     */
    public static WebExampleDTO toWebExampleDTO(ExampleDTO exampleDTO) {
        WebExampleDTO webExampleDTO = new WebExampleDTO();
        webExampleDTO.setId(exampleDTO.getId());
        webExampleDTO.setExampleCode(exampleDTO.getExampleCode());
        webExampleDTO.setExampleName(exampleDTO.getExampleName());

        return webExampleDTO;
    }


}
