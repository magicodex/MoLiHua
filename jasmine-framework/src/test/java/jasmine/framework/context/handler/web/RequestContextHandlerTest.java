package jasmine.framework.context.handler.web;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.web.context.request.RequestAttributes;

import java.util.Collections;
import java.util.Map;

/**
 * @author mh.z
 */
public class RequestContextHandlerTest {

    @Test
    public void testCopyAttributes() {
        RequestContextHandler handler = new RequestContextHandler();

        Map<String, Object> attributeMap = Map.of("key1", "value1", "key2", "value2");
        RequestAttributes requestAttributes = new CopiedRequestAttributes("session1",
                Collections.emptyMap(), attributeMap);

        // 请求作用域
        {
            Map<String, Object> actualMap = handler.copyAttributes(requestAttributes,
                    RequestAttributes.SCOPE_REQUEST);

            Assert.assertEquals(2, actualMap.size());
            Assert.assertEquals("value1", actualMap.get("key1"));
            Assert.assertEquals("value2", actualMap.get("key2"));
        }

        // 会话作用域
        {
            Map<String, Object> actualMap = handler.copyAttributes(requestAttributes,
                    RequestAttributes.SCOPE_SESSION);

            Assert.assertEquals(0, actualMap.size());
        }
    }

}
