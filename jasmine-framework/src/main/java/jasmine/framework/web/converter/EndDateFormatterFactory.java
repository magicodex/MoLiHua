package jasmine.framework.web.converter;

import jasmine.framework.web.annotation.EndDate;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.ZonedDateTime;
import java.util.Set;

/**
 * @author mh.z
 */
public class EndDateFormatterFactory implements AnnotationFormatterFactory<EndDate> {
    private static final Set<Class<?>> FIELD_TYPES;

    static {
        FIELD_TYPES = Set.of(ZonedDateTime.class);
    }

    @Override
    public Set<Class<?>> getFieldTypes() {
        return FIELD_TYPES;
    }

    @Override
    public Printer<?> getPrinter(EndDate annotation, Class<?> fieldType) {
        return new EndDateFormatter(annotation, fieldType);
    }

    @Override
    public Parser<?> getParser(EndDate annotation, Class<?> fieldType) {
        return new EndDateFormatter(annotation, fieldType);
    }

}
