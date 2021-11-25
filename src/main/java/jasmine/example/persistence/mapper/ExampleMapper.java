package jasmine.example.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.example.persistence.model.ExampleDO;

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
