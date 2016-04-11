package io.moj.mobile.android.sdk.test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by skidson on 16-02-15.
 */
public abstract class Mock implements InvocationHandler {

    public Mock() {
        // force a no-args constructor
    }

    abstract Set<String> getMockedClassNames() throws Exception;
    abstract Set<Method> getProxiedMethods() throws Exception;

}
