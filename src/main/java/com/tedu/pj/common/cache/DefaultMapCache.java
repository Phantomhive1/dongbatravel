package com.tedu.pj.common.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DefaultMapCache {
    private Map<Object, Object> cache = new ConcurrentHashMap<>();
    public void putObject(Object key, Object value) {
        cache.put(key, value);
    }

    public Object getObject(Object key) {
        return cache.get(key);
    }

    public void clear() {
        cache.clear();
    }
}
