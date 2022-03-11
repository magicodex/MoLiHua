package jasmine.demo.sample;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.core.util.QI18nUtil;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.web.entity.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author mh.z
 */
@Api(tags = "示例")
@RestController
public class SampleController {
    private static Logger logger = LoggerFactory.getLogger(SampleController.class);
    private SampleService sampleService;
    private SendMessageService sendMessageService;

    public SampleController(SampleService sampleService, SendMessageService sendMessageService) {
        this.sampleService = sampleService;
        this.sendMessageService = sendMessageService;
    }

    @ApiOperation("锁定指定时间")
    @RequestMapping(value = "/api/sample/lock/{lockName}/{lockTime}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> lock1(@PathVariable("lockName") String lockName,
                                                   @PathVariable("lockTime") Long lockTime) {
        logger.info("lock(" + lockName + ") locking...");
        // 加锁
        sampleService.lock(lockName, lockTime);
        logger.info("lock(" + lockName + ") unlock.");

        return ResponseEntity.ok(WebResult.success());
    }

    @ApiOperation("读取缓存")
    @RequestMapping(value = "/api/sample/cache/get/{name}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> cache2(@PathVariable("name") String name) {
        // 获取缓存
        String value = CacheUtil.get("sample", name, String.class);

        return ResponseEntity.ok(WebResult.success(value));
    }

    @ApiOperation("设置数据")
    @RequestMapping(value = "/api/sample/cache/set/{name}/{value}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> cache1(@PathVariable("name") String name,
                                                    @PathVariable("value") String value) {
        // 设置缓存
        CacheUtil.set("sample", name, value);

        return ResponseEntity.ok(WebResult.success());
    }

    @ApiOperation("发送消息")
    @RequestMapping(value = "/api/sample/mq/send/{message}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> mq1(@PathVariable("message") String message) {
        // 发送消息
        sendMessageService.send("sample", null, message);

        return ResponseEntity.ok(WebResult.success());
    }

    @ApiOperation("获取多语言")
    @RequestMapping(value = "/api/sample/i18n/get/{messageKey}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> i18n1(@PathVariable("messageKey") String messageKey) {
        String message = QI18nUtil.getMessage(messageKey);

        return ResponseEntity.ok(WebResult.success(message));
    }

    @ApiOperation("设置语言环境")
    @RequestMapping(value = "/api/sample/i18n/set/{langCode}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> i18n2(HttpServletRequest request, HttpServletResponse response,
                                                   @PathVariable("langCode") String langCode) {
        Cookie cookie = new Cookie("LANG", langCode);
        cookie.setMaxAge(3600 * 24 * 365);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(WebResult.success());
    }

}
