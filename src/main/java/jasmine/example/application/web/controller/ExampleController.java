package jasmine.example.application.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.common.web.WebResult;
import jasmine.example.application.web.conversion.WebExampleConversion;
import jasmine.example.application.web.dto.WebExampleDTO;
import jasmine.example.application.web.dto.WebExampleQO;
import jasmine.example.business.dto.ExampleDTO;
import jasmine.example.business.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mh.z
 */
@Api(tags = "例子")
@RestController
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @ApiOperation(value = "向世界打招呼")
    @GetMapping("/example/hello/world")
    public ResponseEntity<WebResult> helloWorld() {
        WebResult result = WebResult.success("Hello, world!");

        return ResponseEntity.ok(result);
    }

    @GetMapping("/example/all")
    public ResponseEntity<WebResult<List<WebExampleDTO>>> listAllExamples() {
        List<ExampleDTO> exampleDTOList = exampleService.listAllExamples();
        List<WebExampleDTO> webExampleDTOList = exampleDTOList.stream()
                .map(WebExampleConversion::toWebExampleDTO).collect(Collectors.toList());

        WebResult result = WebResult.success(webExampleDTOList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/example/by/cond")
    public ResponseEntity<WebResult<List<WebExampleDTO>>> listExamplesByCond(@Valid WebExampleQO query) {
        WebResult result = WebResult.success(Collections.emptyList());
        // TODO

        return ResponseEntity.ok(result);
    }

}
