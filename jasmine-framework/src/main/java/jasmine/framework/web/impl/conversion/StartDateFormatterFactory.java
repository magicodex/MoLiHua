package jasmine.framework.web.impl.conversion;

import jasmine.framework.web.annotation.conversion.StartDate;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 * @author mh.z
 */
public class StartDateFormatterFactory implements AnnotationFormatterFactory<StartDate> {
    private static final Set<Class<?>> FIELD_TYPES;

    static {
        FIELD_TYPES = new HashSet<>();
        FIELD_TYPES.add(ZonedDateTime.class);
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
