package io.moj.mobile.android.sdk.push;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.moj.mobile.android.sdk.TestJson;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

public class ObserverTest {

    @Test
    public void testSetTransport() {
        Observer o = new Observer("key");
        Transport transport1 = Transport.forAndroid("gcmRegId");
        Transport transport2 = Transport.forSignalR("clientId", "hubName");

        o.setTransport(transport1);
        assertNotNull(o.getTransports());
        assertEquals(1, o.getTransports().size());
        assertEquals(transport1, o.getTransports().get(0));
        assertEquals(transport1, o.getTransport());

        o.setTransport(transport2);
        assertNotNull(o.getTransports());
        assertEquals(1, o.getTransports().size());
        assertEquals(transport2, o.getTransports().get(0));
        assertEquals(transport2, o.getTransport());
    }

    @Test
    public void testSetTransports() {
        Observer o = new Observer("key");
        Transport transport1 = Transport.forAndroid("gcmRegId");
        Transport transport2 = Transport.forSignalR("clientId", "hubName");

        o.setTransports(Lists.newArrayList(transport1, transport2));
        assertNotNull(o.getTransports());
        assertEquals(2, o.getTransports().size());
        assertEquals(transport1, o.getTransports().get(0));
        assertEquals(transport2, o.getTransports().get(1));
        assertEquals(transport1, o.getTransport());
    }

    @Test
    public void testSerialization() {
        Gson gson = new Gson();
        Condition condition1 = Condition.debounce(1, 2, 3, 4, 5);
        Condition condition2 = Condition.delay(6, 7, 8, 9);
        Condition condition3 = Condition.onPropertyChanged("Property");
        Condition condition4 = Condition.throttle(0, 3, 6, 9);

        List<Condition> conditions = Lists.newArrayList(
                condition1,
                condition2,
                condition3,
                condition4
        );

        Observer o = new Observer();
        o.setTransport(gson.fromJson(TestJson.TRANSPORT, Transport.class));
        o.setType(Observer.Type.VEHICLE);
        o.setFields(Lists.newArrayList("field1", "field2"));
        o.setConditions(conditions);
        o.setCreatedOn("createdOn");
        o.setExpiryDate("expiryDate");
        o.setKey("key");
        o.setLastModified("lastModified");
        o.setSubject("subject");

        String json = gson.toJson(o);
        assertEquals(TestJson.OBSERVER, json);
    }

    @Test
    public void testDeserialization() {
        Gson gson = new Gson();
        Observer o = gson.fromJson(TestJson.OBSERVER, Observer.class);
        Transport t = gson.fromJson(TestJson.TRANSPORT, Transport.class);
        Condition condition1 = Condition.debounce(1, 2, 3, 4, 5);
        Condition condition2 = Condition.delay(6, 7, 8, 9);
        Condition condition3 = Condition.onPropertyChanged("Property");
        Condition condition4 = Condition.throttle(0, 3, 6, 9);

        List<Condition> conditions = Lists.newArrayList(
                condition1,
                condition2,
                condition3,
                condition4
        );

        assertNotNull(o);
        assertEquals(t, o.getTransport());
        assertNotNull(o.getTransports());
        assertEquals(1, o.getTransports().size());
        assertEquals(t, o.getTransports().get(0));
        assertEquals(Observer.Type.VEHICLE, o.getType());
        assertEquals(Lists.newArrayList("field1", "field2"), o.getFields());
        assertEquals(conditions, o.getConditions());
        assertEquals("createdOn", o.getCreatedOn());
        assertEquals("expiryDate", o.getExpiryDate());
        assertEquals("key", o.getKey());
        assertEquals("lastModified", o.getLastModified());
        assertEquals("subject", o.getSubject());
    }

}