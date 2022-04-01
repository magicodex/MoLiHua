package jasmine.core.util.batch;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

/**
 * @author mh.z
 */
public abstract class AbstractBatchMap<K, V> implements Map<K, V> {
    protected Map<K, V> loadedMap;

    public AbstractBatchMap() {
        this.loadedMap = new LinkedHashMap<>();
    }

    /**
     * 加载数据
     *
     * @param keys
     * @param <T>
     */
    public <T> void load(Collection<K> keys) {
        lazyLoad(keys, Function.identity());
    }

    /**
     * 加载数据
     *
     * @param collection
     * @param keyMapper
     * @param <T>
     */
    public <T> void load(Collection<T> collection, Function<T, K> keyMapper) {
        lazyLoad(collection, keyMapper);
    }

    /**
     * 延迟加载
     *
     * @param collection
     * @param keyMapper
     */
    protected abstract <T> void lazyLoad(Collection<T> collection, Function<T, K> keyMapper);

    /**
     * 立即加载
     */
    protected abstract void forceLoad();

    @Override
    public V get(Object key) {
        forceLoad();
        return loadedMap.get(key);
    }

    @Override
    public V put(K key, V value) {
        forceLoad();
        return loadedMap.put(key, value);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        forceLoad();
        loadedMap.putAll(map);
    }

    @Override
    public V remove(Object key) {
        forceLoad();
        return loadedMap.remove(key);
    }

    @Override
    public void clear() {
        forceLoad();
        loadedMap.clear();
    }

    @Override
    public boolean containsKey(Object key) {
        forceLoad();
        return loadedMap.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        forceLoad();
        return loadedMap.containsValue(value);
    }

    @Override
    public Set<K> keySet() {
        forceLoad();
        return loadedMap.keySet();
    }

    @Override
    public Collection<V> values() {
        forceLoad();
        return loadedMap.values();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        forceLoad();
        return loadedMap.entrySet();
    }

    @Override
    public boolean isEmpty() {
        forceLoad();
        return loadedMap.isEmpty();
    }

    @Override
    public int size() {
        forceLoad();
        return loadedMap.size();
    }

}
