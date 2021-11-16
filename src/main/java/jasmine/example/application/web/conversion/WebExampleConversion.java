package jasmine.example.application.web.conversion;

import jasmine.example.application.web.dto.WebExampleDTO;
import jasmine.example.business.dto.ExampleDTO;

public class WebExampleConversion {

    public static WebExampleDTO toWebExampleDTO(ExampleDTO exampleDTO) {
        WebExampleDTO webExampleDTO = new WebExampleDTO();
        webExampleDTO.setId(exampleDTO.getId());
        webExampleDTO.setExampleCode(exampleDTO.getExampleCode());
        webExampleDTO.setExampleName(exampleDTO.getExampleName());

        return webExampleDTO;
    }


}
