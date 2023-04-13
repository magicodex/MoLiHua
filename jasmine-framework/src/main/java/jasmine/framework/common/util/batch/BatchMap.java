package jasmine.framework.common.util.batch;

import jasmine.framework.common.util.CheckUtil;
import jasmine.framework.common.util.CollectionUtil;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

/**
 * @author mh.z
 */
public class BatchMap<K, V> extends AbstractBatchMap<K, V> {
    private Function<V, K> keyFunction;
    private Function<List<K>, List<V>> loadFunction;
    private Set<K> lazyKeys;

    public BatchMap(Function<V, K> keyFunction, Function<List<K>, List<V>> loadFunction) {
        this.keyFunction = keyFunction;
        this.loadFunction = loadFunction;
        this.lazyKeys = new HashSet<>();
    }

    @Override
    public <T> void lazyLoad(Collection<T> collection, Function<T, K> keyMapper) {
        CheckUtil.notNull(collection, "collection null");
        CheckUtil.notNull(keyMapper, "keyMapper null");

        if (collection.isEmpty()) {
            return;
        }

        List<K> keyList = CollectionUtil.mapToList(collection, keyMapper);
        Set<K> keySet = new HashSet<>(keyList);
        keySet.remove(null);

        if (CollectionUtil.isNotEmpty(keySet)) {
            lazyKeys.addAll(keySet);
        }
    }

    @Override
    protected void forceLoad() {
        if (CollectionUtil.isNotEmpty(lazyKeys)) {
            List<K> keyList = CollectionUtil.toList(lazyKeys);
            List<V> valueList = loadFunction.apply(keyList);

            for (V value : valueList) {
                loadedMap.put(keyFunction.apply(value), value);
            }

            lazyKeys.clear();
        }
    }

}
