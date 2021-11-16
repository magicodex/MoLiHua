package jasmine.example.application.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.common.web.WebResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "例子")
@RestController
public class ExampleController {

    @ApiOperation(value = "向世界打招呼")
    @GetMapping("/example/hello/world")
    public ResponseEntity<WebResult> helloWorld() {
        WebResult result = new WebResult();
        result.setData("Hello, world!");

        return ResponseEntity.ok(result);
    }

}
