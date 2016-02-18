package io.moj.mobile.android.sdk.push;

import com.google.gson.Gson;

import org.junit.Test;

import io.moj.mobile.android.sdk.TestJson;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
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

    @Test
    public void testSerialization() {
        Transport t = new Transport();
        t.setCallback("callback");
        t.setHubName("hubName");
        t.setClientId("clientId");
        t.setDeviceRegistrationId("deviceRegistrationId");
        t.setAddress("address");
        t.setAlertBody("alertBody");
        t.setAlertCategory("alertCategory");
        t.setAlertSound("alertSound");
        t.setAppId("appId");
        t.setBadge(3);
        t.setCollectionName("collectionName");
        t.setConnectionString("connectionString");
        t.setDeviceToken("deviceToken");
        t.setHostName("hostName");
        t.setIdentifier(Transport.MongoIdentifierType.DEFAULT);
        t.setUserName("userName");
        t.setPassword("password");
        t.setPort(1337);
        t.setTopic("topic");
        t.setType(Transport.Type.HTTP_POST);

        String json = new Gson().toJson(t);
        assertEquals(TestJson.TRANSPORT, json);
    }

    @Test
    public void testDeserialization() {
        Transport t = new Gson().fromJson(TestJson.TRANSPORT, Transport.class);

        assertNotNull(t);
        assertEquals("callback", t.getCallback());
        assertEquals("hubName", t.getHubName());
        assertEquals("clientId", t.getClientId());
        assertEquals("deviceRegistrationId", t.getDeviceRegistrationId());
        assertEquals("address", t.getAddress());
        assertEquals("alertBody", t.getAlertBody());
        assertEquals("alertCategory", t.getAlertCategory());
        assertEquals("alertSound", t.getAlertSound());
        assertEquals("appId", t.getAppId());
        assertEquals(3, t.getBadge());
        assertEquals("collectionName", t.getCollectionName());
        assertEquals("connectionString", t.getConnectionString());
        assertEquals("deviceToken", t.getDeviceToken());
        assertEquals("hostName", t.getHostName());
        assertEquals(Transport.MongoIdentifierType.DEFAULT, t.getIdentifier());
        assertEquals("userName", t.getUserName());
        assertEquals("password", t.getPassword());
        assertEquals(1337, t.getPort());
        assertEquals("topic", t.getTopic());
        assertEquals(Transport.Type.HTTP_POST, t.getType());
    }

}