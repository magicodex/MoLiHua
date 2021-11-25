package test.context;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@Transactional(rollbackFor = Exception.class)
@ContextConfiguration(locations = "classpath:/test/config/springContext.xml")
public class BeanTestContext {

}
