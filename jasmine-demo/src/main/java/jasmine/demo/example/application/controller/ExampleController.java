package jasmine.demo.example.application.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

public class ExampleController {

    @GetMapping("/example/{id}")
    public void getExampleById() {

    }

    @PostMapping("/example")
    public void saveExample() {

    }

    @PutMapping("/example")
    public void updateExample() {

    }

    @DeleteMapping("/example/{id}")
    public void deleteExampleById() {

    }

    @GetMapping("/example/pageExamplesByCond")
    public void pageExamplesByCond() {

    }

    @GetMapping("/example/pageSearchExamplesByCond")
    public void pageSearchExamplesByCond() {

    }

    @GetMapping("/example/listExamplesByCode")
    public void listExamplesByCode() {

    }

    @GetMapping("/example/listExamplesByBaseInfo")
    public void listExamplesByBaseInfo() {

    }

    @GetMapping("/example/listExamplesByCodeAndName")
    public void listExamplesByCodeAndName() {

    }

    @GetMapping("/example/listExamplesByCodeAndCond")
    public void listExamplesByCodeAndCode() {

    }

    @GetMapping("/example/listExampleMoreInfoByCodeAndName")
    public void listExampleMoreInfoByCodeAndName() {

    }

}
