package jasmine.demo.example.application.web.controller;

import jasmine.demo.example.application.web.dto.WebExampleDTO;
import jasmine.demo.example.application.web.dto.WebExampleQO;
import jasmine.demo.example.constant.ExampleMessages;
import jasmine.framework.lock.DeclaredGlobalLock;
import jasmine.framework.lock.GlobalLock;
import jasmine.framework.remote.sender.MessageSendService;
import jasmine.core.util.QI18nUtil;
import jasmine.framework.web.entity.WebResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.demo.example.application.web.conversion.WebExampleConversion;
import jasmine.demo.example.business.dto.ExampleDTO;
import jasmine.demo.example.business.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author mh.z
 */
@Api(tags = "例子")
@RestController
public class ExampleController {
    @Autowired
    private ExampleService exampleService;

    @Autowired
    private MessageSendService publisherService;

    @ApiOperation(value = "向世界打招呼")
    @GetMapping("/example/hello/world")
    public ResponseEntity<WebResult> helloWorld() {
        WebResult result = WebResult.success(QI18nUtil.getMessage(ExampleMessages.HELLO_WORLD));

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "先加锁，后向世界打招呼")
    @GetMapping("/example/lockThenHelloWorld")
    public ResponseEntity<WebResult> lockThenHelloWorld() {
        DeclaredGlobalLock lock = GlobalLock.declareLock("example", "lockThenHelloWorld");

        WebResult result = lock.lock(() -> {
            Thread.sleep(5000L);
            return WebResult.success(QI18nUtil.getMessage(ExampleMessages.HELLO_WORLD));
        });

        return ResponseEntity.ok(result);
    }

    @ApiOperation(value = "发送消息")
    @GetMapping("/example/publish/message")
    public ResponseEntity<WebResult> publishMessage(@RequestParam String category,
                                                    @RequestParam String content) {
        Object data = Map.of("category", category, "content", content);
        publisherService.send("example", data);

        return ResponseEntity.ok(WebResult.success("publish success"));
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
