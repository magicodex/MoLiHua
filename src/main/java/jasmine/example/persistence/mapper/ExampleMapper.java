package jasmine.example.persistence.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import jasmine.example.persistence.model.ExampleDO;

import java.util.List;

public interface ExampleMapper extends BaseMapper<ExampleDO> {

    List<ExampleDO> listAllExamples();
}
