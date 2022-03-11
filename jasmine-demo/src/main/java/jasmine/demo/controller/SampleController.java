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
    public ResponseEntity<WebResult<String>> lock(@PathVariable("lockName") String lockName,
                                                  @PathVariable("lockTime") Long lockTime) {
        logger.info("lock(" + lockName + ") locking...");
        // 加锁
        sampleService.lock(lockName, lockTime);
        logger.info("lock(" + lockName + ") unlock.");

        return ResponseEntity.ok(WebResult.success("lock ok."));
    }

}
