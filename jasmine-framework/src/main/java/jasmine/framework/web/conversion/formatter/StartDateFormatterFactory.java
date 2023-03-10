package jasmine.framework.web.conversion.formatter;

import jasmine.framework.web.annotation.StartDate;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author mh.z
 */
public class StartDateFormatterFactory implements AnnotationFormatterFactory<StartDate> {
    private static final Set<Class<?>> FIELD_TYPES;

    static {
        FIELD_TYPES = Set.of(ZonedDateTime.class);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    public Printer<?> getPrinter(StartDate annotation, Class<?> fieldType) {
        return new StartDateFormatter(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(StartDate annotation, Class<?> fieldType) {
        return new StartDateFormatter(annotation, fieldType);
    }

}
