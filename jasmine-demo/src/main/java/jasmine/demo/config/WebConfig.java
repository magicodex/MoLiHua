package jasmine.demo.config;

import jasmine.framework.web.impl.conversion.EndDateFormatterFactory;
import jasmine.framework.web.impl.conversion.StartDateFormatterFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.support.FormattingConversionService;
import org.springframework.web.bind.support.ConfigurableWebBindingInitializer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;

/**
 * @author mh.z
 */
@Configuration
public class WebConfig {
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public WebConfig(RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @PostConstruct
    public void addFormatterForFieldAnnotations() {
        ConfigurableWebBindingInitializer webBindingInitializer = (ConfigurableWebBindingInitializer)
                requestMappingHandlerAdapter.getWebBindingInitializer();
        FormattingConversionService conversionService = (FormattingConversionService)
                webBindingInitializer.getConversionService();

        if (conversionService != null) {
            conversionService.addFormatterForFieldAnnotation(new StartDateFormatterFactory());
            conversionService.addFormatterForFieldAnnotation(new EndDateFormatterFactory());
        }
    }

}
