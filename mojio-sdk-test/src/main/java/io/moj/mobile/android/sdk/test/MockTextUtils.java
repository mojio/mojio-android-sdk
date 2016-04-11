package io.moj.mobile.android.sdk.test;


import android.text.TextUtils;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableSet;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by skidson on 16-02-15.
 */
public class MockTextUtils extends Mock {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        String text = (String) args[0];
        switch (method.getName()) {
            case "isEmpty":
                return isEmpty(text);
            case "isDigitsOnly":
                return isDigitsOnly(text);
        }
        throw new UnsupportedOperationException("Method '" + method.getName() + "' not mocked");
    }

    @Override
    public Set<String> getMockedClassNames() {
        return ImmutableSet.of(TextUtils.class.getName());
    }

    @Override
    public Set<Method> getProxiedMethods() throws NoSuchMethodException {
        return ImmutableSet.of(
                TextUtils.class.getMethod("isEmpty", CharSequence.class),
                TextUtils.class.getMethod("isDigitsOnly", CharSequence.class)
        );
    }

    private boolean isEmpty(String text) {
        return Strings.isNullOrEmpty(text);
    }

    private boolean isDigitsOnly(String text) {
        try {
            Long.parseLong(text);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
