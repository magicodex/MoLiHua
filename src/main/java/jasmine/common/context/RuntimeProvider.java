package jasmine.common.context;

public interface RuntimeProvider {

    <T> T getByType(Class<T> type);
}
