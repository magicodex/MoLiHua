package jasmine.example.business.service;

import jasmine.example.business.dto.ExampleDTO;

import java.util.List;

/**
 * @author mh.z
 */
public interface ExampleService {

    /**
     * 查找所有的记录
     *
     * @return
     */
    List<ExampleDTO> listAllExamples();
}
