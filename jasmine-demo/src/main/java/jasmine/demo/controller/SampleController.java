package jasmine.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jasmine.demo.service.SampleService;
import jasmine.framework.web.entity.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mh.z
 */
@Api(tags = "示例")
@RestController
public class SampleController {
    private static Logger logger = LoggerFactory.getLogger(SampleController.class);
    private SampleService sampleService;

    public SampleController(SampleService sampleService) {
        this.sampleService = sampleService;
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

    @ApiOperation("读取指定缓存")
    @RequestMapping(value = "/api/sample/cache/get/{name}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> cache2(@PathVariable("name") String name) {
        // 获取缓存
        String value = sampleService.getFromCache(name);

        return ResponseEntity.ok(WebResult.success(value));
    }

    @ApiOperation("缓存指定数据")
    @RequestMapping(value = "/api/sample/cache/set/{name}/{value}",
            method = {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<WebResult<String>> cache1(@PathVariable("name") String name,
                                                    @PathVariable("value") String value) {
        // 设置缓存
        sampleService.setToCache(name, value);

        return ResponseEntity.ok(WebResult.success());
    }

}
