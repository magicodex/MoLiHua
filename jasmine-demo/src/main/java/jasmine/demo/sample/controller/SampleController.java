package jasmine.demo.sample.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jasmine.core.util.QDateUtil;
import jasmine.core.util.QI18nUtil;
import jasmine.demo.sample.dto.Params1DTO;
import jasmine.demo.sample.dto.SampleCreateDTO;
import jasmine.demo.sample.dto.SampleDTO;
import jasmine.demo.sample.dto.SampleUpdateDTO;
import jasmine.demo.sample.service.SampleService;
import jasmine.framework.cache.CacheUtil;
import jasmine.framework.remote.mq.SendMessageService;
import jasmine.framework.web.validation.helper.ValidationHelper;
import jasmine.framework.web.annotation.EndDate;
import jasmine.framework.web.annotation.StartDate;
import jasmine.framework.web.entity.WebResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.time.ZonedDateTime;

/**
 * @author mh.z
 */
@Tag(name = "示例")
@RestController
public class SampleController {
    private static Logger logger = LoggerFactory.getLogger(SampleController.class);
    private SampleService sampleService;
    private SendMessageService sendMessageService;

    public SampleController(SampleService sampleService, SendMessageService sendMessageService) {
        this.sampleService = sampleService;
        this.sendMessageService = sendMessageService;
    }

    //
    // 锁
    //

    @Operation(summary = "锁定指定时间")
    @RequestMapping(value = "/api/sample/lock/{lockName}/{lockTime}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> lock1(@Parameter(name = "锁名称") @PathVariable("lockName") String lockName,
                                                   @Parameter(name = "加锁时间") @PathVariable("lockTime") Long lockTime) {
        logger.info("lock(" + lockName + ") locking...");
        // 加锁
        sampleService.lockThenSleep(lockName, lockTime);
        logger.info("lock(" + lockName + ") unlock.");

        return ResponseEntity.ok(WebResult.success());
    }

    //
    // 缓存
    //

    @Operation(summary = "读取缓存")
    @RequestMapping(value = "/api/sample/cache/get/{name}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> cache2(@Parameter(name = "缓存key") @PathVariable("name") String name) {
        // 获取缓存
        String value = CacheUtil.get("sample", name, String.class);

        return ResponseEntity.ok(WebResult.success(value));
    }

    @Operation(summary = "添加缓存")
    @RequestMapping(value = "/api/sample/cache/set/{name}/{value}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> cache1(@Parameter(name = "缓存key") @PathVariable("name") String name,
                                                    @Parameter(name = "缓存的值") @PathVariable("value") String value) {
        // 设置缓存
        CacheUtil.set("sample", name, value);

        return ResponseEntity.ok(WebResult.success());
    }

    //
    // 消息队列
    //

    @Operation(summary = "发送消息")
    @RequestMapping(value = "/api/sample/mq/send/{message}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> mq1(@Parameter(name = "消息内容") @PathVariable("message") String message) {
        // 发送消息
        sendMessageService.send("sample", null, message);

        return ResponseEntity.ok(WebResult.success());
    }

    //
    // 多语言
    //

    @Operation(summary = "获取多语言")
    @RequestMapping(value = "/api/sample/i18n/get/{messageKey}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> i18n1(@Parameter(name = "多语言key") @PathVariable("messageKey") String messageKey) {
        String message = QI18nUtil.getMessage(messageKey);

        return WebResult.success(message).toEntity();
    }

    @Operation(summary = "设置语言环境")
    @RequestMapping(value = "/api/sample/i18n/set/{langCode}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> i18n2(HttpServletRequest request, HttpServletResponse response,
                                                   @Parameter(name = "语言代码") @PathVariable("langCode") String langCode) {
        Cookie cookie = new Cookie("LANG", langCode);
        cookie.setMaxAge(3600 * 24 * 365);
        cookie.setPath("/");
        response.addCookie(cookie);

        return ResponseEntity.ok(WebResult.success());
    }

    //
    // 校验
    //

    @Operation(summary = "校验参数")
    @RequestMapping(value = "/api/sample/validation/{param1}/{param2}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> validation1(@ModelAttribute Params1DTO param, @Parameter(hidden = true) Errors errors) {
        ValidationHelper validationHelper = ValidationHelper.create(errors);
        validationHelper.field("param1").rejectIfBlank();
        validationHelper.field("param2").rejectIfBlank();

        if (validationHelper.hasErrors()) {
            return validationHelper.toEntity();
        }

        return ResponseEntity.ok(WebResult.success());
    }

    //
    // 转换类型
    //

    @Operation(summary = "转换类型")
    @RequestMapping(value = "/api/sample/conversion/{startDate}/{endDate}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<String>> conversion1(@Parameter(name = "开始日期") @PathVariable("startDate") @StartDate ZonedDateTime startDate,
                                                         @Parameter(name = "结束日期") @PathVariable("endDate") @EndDate ZonedDateTime endDate) {
        String startDateStr = QDateUtil.formatYearSecond(startDate);
        String endDateStr = QDateUtil.formatYearSecond(endDate);

        WebResult<String> result = WebResult.success(startDateStr + "~" + endDateStr);
        return result.toEntity();
    }

    //
    // 数据库
    //

    @Operation(summary = "查询数据")
    @RequestMapping(value = "/api/sample/data/query/{sampleId}",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<SampleDTO>> data1(@Parameter(name = "记录ID") @PathVariable("sampleId") Long sampleId) {
        SampleDTO sample = sampleService.getSampleById(sampleId);

        return WebResult.success(sample).toEntity();
    }

    @Operation(summary = "保存数据")
    @RequestMapping(value = "/api/sample/data/create",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<SampleDTO>> data2(@Valid @ModelAttribute SampleCreateDTO sample) {
        SampleDTO sampleDTO = sampleService.saveSample(sample);

        return WebResult.success(sampleDTO).toEntity();
    }

    @Operation(summary = "修改数据")
    @RequestMapping(value = "/api/sample/data/update",
            method = {RequestMethod.GET})
    public ResponseEntity<WebResult<SampleDTO>> data3(@Valid @ModelAttribute SampleUpdateDTO sample) {
        SampleDTO sampleDTO = sampleService.updateSample(sample);

        return WebResult.success(sampleDTO).toEntity();
    }

}
