package io.moj.mobile.android.sdk.test;

import com.google.common.collect.ImmutableSet;

import org.powermock.core.spi.PowerMockPolicy;
import org.powermock.mockpolicies.MockPolicyClassLoadingSettings;
import org.powermock.mockpolicies.MockPolicyInterceptionSettings;

import java.lang.reflect.Method;
import java.util.Set;

/**
 * Created by skidson on 16-02-15.
 */
public class AndroidMockPolicy implements PowerMockPolicy {

    private static final Set<Class<? extends Mock>> MOCKS = ImmutableSet.of(
            MockBundle.class,
            MockTextUtils.class
    );

    @Override
    public void applyClassLoadingPolicy(MockPolicyClassLoadingSettings settings) {
        try {
            for (Class<? extends Mock> mockClass : MOCKS) {
                Mock mock = mockClass.newInstance();
                for (String mockedClass : mock.getMockedClassNames()) {
                    settings.addFullyQualifiedNamesOfClassesToLoadByMockClassloader(mockedClass);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void applyInterceptionPolicy(MockPolicyInterceptionSettings settings) {
        try {
            for (Class<? extends Mock> mockClass : MOCKS) {
                Mock mock = mockClass.newInstance();
                for (Method proxiedMethod : mock.getProxiedMethods()) {
                    settings.proxyMethod(proxiedMethod, mock);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
