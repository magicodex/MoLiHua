package jasmine.demo.example.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.demo.example.persistence.entity.ExampleEntity;

import java.util.List;

/**
 * @author mh.z
 */
public interface ExampleMapper extends BaseMapper<ExampleEntity> {

    /**
     * 查找所有记录
     *
     * @return
     */
    List<ExampleEntity> listAllExamples();
}
