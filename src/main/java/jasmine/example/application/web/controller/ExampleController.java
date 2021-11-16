package jasmine.example.application.web.controller;

import jasmine.common.web.WebResult;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @GetMapping("/example/hello/world")
    public ResponseEntity<WebResult> helloWorld() {
        WebResult result = new WebResult();
        result.setData("Hello, world!");

        return ResponseEntity.ok(result);
    }

}
