package jasmine.core.util.batch;

import jasmine.core.util.QCheckUtil;
import jasmine.core.util.QCollUtil;

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
        QCheckUtil.notNull(collection, "collection null");
        QCheckUtil.notNull(keyMapper, "keyMapper null");

        if (collection.isEmpty()) {
            return;
        }

        List<K> keyList = QCollUtil.mapToList(collection, keyMapper);
        Set<K> keySet = new HashSet<>(keyList);
        keySet.remove(null);

        if (QCollUtil.isNotEmpty(keySet)) {
            lazyKeys.addAll(keySet);
        }
    }

    @Override
    protected void forceLoad() {
        if (QCollUtil.isNotEmpty(lazyKeys)) {
            List<K> keyList = QCollUtil.castToList(lazyKeys);
            List<V> valueList = loadFunction.apply(keyList);

            for (V value : valueList) {
                loadedMap.put(keyFunction.apply(value), value);
            }

            lazyKeys.clear();
        }
    }

}
