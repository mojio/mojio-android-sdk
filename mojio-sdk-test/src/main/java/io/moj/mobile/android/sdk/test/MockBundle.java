package io.moj.mobile.android.sdk.test;

import android.os.BaseBundle;
import android.os.Bundle;

import com.google.common.collect.ImmutableSet;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by skidson on 16-02-15.
 */
public class MockBundle extends Mock {

    private static final Set<String> MOCKED_CLASSNAMES = ImmutableSet.of(
            Bundle.class.getName(),
            BaseBundle.class.getName()
    );

    private Map<Object, Map<String, Object>> proxyBundleMap = new HashMap<>();

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Map<String, Object> bundleMap = proxyBundleMap.get(proxy);
        if (bundleMap == null) {
            bundleMap = new HashMap<>();
            proxyBundleMap.put(proxy, bundleMap);
        }

        String key = (String) args[0];
        if (method.getName().startsWith("put")) {
            bundleMap.put(key, args[1]);
        } else if (method.getName().startsWith("get")) {
            Object value = bundleMap.get(key);
            if (value == null && method.getReturnType().isPrimitive())
                value = 0;
            return value;
        }
        return null;
    }

    @Override
    public Set<String> getMockedClassNames() {
        return MOCKED_CLASSNAMES;
    }

    @Override
    public Set<Method> getProxiedMethods() {
        Set<Method> proxiedMethods = new HashSet<>();
        for (Method method : Bundle.class.getMethods()) {
            if (method.getName().startsWith("get") || method.getName().startsWith("put"))
                proxiedMethods.add(method);
        }
        return ImmutableSet.copyOf(proxiedMethods);
    }
}
