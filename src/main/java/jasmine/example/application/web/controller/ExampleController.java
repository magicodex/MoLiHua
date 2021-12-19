package jasmine.example.application.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.common.global.lock.DeclaredGlobalLock;
import jasmine.common.global.lock.GlobalLockHelper;
import jasmine.common.util.Q;
import jasmine.common.web.WebResult;
import jasmine.example.application.web.conversion.WebExampleConversion;
import jasmine.example.application.web.dto.WebExampleDTO;
import jasmine.example.application.web.dto.WebExampleQO;
import jasmine.example.business.dto.ExampleDTO;
import jasmine.example.business.service.ExampleService;
import jasmine.example.constant.ExampleMessages;
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
        WebResult result = WebResult.success(Q.tr(ExampleMessages.HELLO_WORLD));

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "先加锁，后向世界打招呼")
    @GetMapping("/example/lockThenHelloWorld")
    public ResponseEntity<WebResult> lockThenHelloWorld() {
        DeclaredGlobalLock lock = GlobalLockHelper.declareLock("example", "lockThenHelloWorld");

        WebResult result = lock.lock(() -> {
            Thread.sleep(5000L);
            return WebResult.success(Q.tr(ExampleMessages.HELLO_WORLD));
        });

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "查找所有记录")
    @GetMapping("/example/all")
    public ResponseEntity<WebResult<List<WebExampleDTO>>> listAllExamples() {
        List<ExampleDTO> exampleDTOList = exampleService.listAllExamples();
        List<WebExampleDTO> webExampleDTOList = exampleDTOList.stream()
                .map(WebExampleConversion::toWebExampleDTO).collect(Collectors.toList());

        WebResult result = WebResult.success(webExampleDTOList);
        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "查找指定记录")
    @GetMapping("/example/by/cond")
    public ResponseEntity<WebResult<List<WebExampleDTO>>> listExamplesByCond(@Valid WebExampleQO query) {
        WebResult result = WebResult.success(Collections.emptyList());
        // TODO

        return ResponseEntity.ok(result);
    }

}
