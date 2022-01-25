package jasmine.demo.example.business.service;

import jasmine.demo.example.business.dto.ExampleDTO;
import jasmine.demo.example.business.conversion.ExampleConversion;
import jasmine.demo.example.persistence.dao.ExampleDao;
import jasmine.demo.example.persistence.model.ExampleDO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mh.z
 */
@Service
public class ExampleServiceImpl implements ExampleService {
    private ExampleDao exampleDao;

    public ExampleServiceImpl(ExampleDao exampleDao) {
        this.exampleDao = exampleDao;
    }

    @Override
    public List<ExampleDTO> listAllExamples() {
        List<ExampleDO> exampleDOList = exampleDao.listAllExamples();

        List<ExampleDTO> exampleDTOList = exampleDOList.stream()
                .map(ExampleConversion::toExampleDTO).collect(Collectors.toList());

        return exampleDTOList;
    }

}
