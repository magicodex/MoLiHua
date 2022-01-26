package jasmine.demo.example.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.demo.example.persistence.entity.ExampleDO;

import java.util.List;

/**
 * @author mh.z
 */
public interface ExampleMapper extends BaseMapper<ExampleDO> {

    /**
     * 查找所有记录
     *
     * @return
     */
    List<ExampleDO> listAllExamples();
}
