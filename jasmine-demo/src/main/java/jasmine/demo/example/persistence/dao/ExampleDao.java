package jasmine.demo.example.persistence.dao;

import jasmine.demo.example.persistence.mapper.ExampleMapper;
import jasmine.demo.example.persistence.entity.ExampleEO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author mh.z
 */
@Repository
public class ExampleDao {
    private ExampleMapper baseMapper;

    public ExampleDao(ExampleMapper baseMapper) {
        this.baseMapper = baseMapper;
    }

    public List<ExampleEO> listAllExamples() {
        /*LambdaQueryWrapper<ExampleDO> wrapper = Wrappers.lambdaQuery();

        return baseMapper.selectList(wrapper);*/

        return baseMapper.listAllExamples();
    }

}
