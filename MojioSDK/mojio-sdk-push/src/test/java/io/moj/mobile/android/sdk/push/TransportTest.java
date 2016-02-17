package io.moj.mobile.android.sdk.push;

import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;

public class TransportTest {

    @Test
    public void testForAndroid() {
        String gcmRegId = "asdf1234qwerty";

        Transport t = Transport.forAndroid(gcmRegId);
        assertEquals(Transport.Type.ANDROID, t.getType());
        assertEquals(gcmRegId, t.getDeviceRegistrationId());
    }

    @Test
    public void testForSignalR() {
        String clientId = "clientId";
        String hubName = "hubName";

        Transport t = Transport.forSignalR(clientId, hubName);
        assertEquals(Transport.Type.SIGNAL_R, t.getType());
        assertEquals(clientId, t.getClientId());
        assertEquals(hubName, t.getHubName());
        assertNull(t.getCallback());
    }

    @Test
    public void testForSignalR_withCallback() {
        String clientId = "clientId";
        String hubName = "hubName";
        String callback = "callback";

        Transport t = Transport.forSignalR(clientId, hubName, callback);
        assertEquals(Transport.Type.SIGNAL_R, t.getType());
        assertEquals(clientId, t.getClientId());
        assertEquals(hubName, t.getHubName());
        assertEquals(callback, t.getCallback());
    }

}