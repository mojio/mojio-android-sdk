package io.moj.mobile.android.sdk.push;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import io.moj.mobile.android.sdk.TestJson;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;
import static junit.framework.Assert.assertTrue;

public class ObserverTest {

    @Test
    public void testBuilder() {
        String key = "key";
        String subject = "subject";
        Observer.Type type = Observer.Type.MOJIO;
        Transport transport = Transport.forAndroid("gcmRegId");

        Condition[] conditions = new Condition[] {
                Condition.delay(2, 0, 0, 0),
                Condition.throttle("Speed.Value", "0.00:01:00.0000")
        };
        String[] fields = new String[] { "LastContactTime", "LastContactTime" };

        Observer o = new Observer.Builder(key)
            .subject(subject)
            .type(type)
            .transport(transport)
            .condition(conditions[0])
            .condition(conditions[1])
            .field(fields[0])
            .field(fields[1])
            .build();

        assertEquals(key, o.getKey());
        assertEquals(subject, o.getSubject());
        assertEquals(type, o.getType());

        assertNotNull(o.getTransports());
        assertEquals(1, o.getTransports().size());
        assertEquals(transport, o.getTransports().get(0));
        assertEquals(transport, o.getTransport());

        assertNotNull(o.getConditions());
        assertEquals(conditions.length, o.getConditions().size());
        for (Condition condition : conditions)
            assertEquals("Wrong condition for '" + condition.getType() + "'",
                    condition, o.getConditions().get(condition.getType().getKey()));

        assertNotNull(o.getFields());
        assertEquals(fields.length, o.getFields().size());
        for (String field : fields)
            assertTrue("Missing field '" + field + "'", o.getFields().contains(field));
    }

    @Test
    public void testBuilder_multipleTransports() {
        Observer.Type type = Observer.Type.MOJIO;
        Transport transport1 = Transport.forAndroid("gcmRegId");
        Transport transport2 = Transport.forSignalR("clientId", "hubName");

        Observer o = new Observer.Builder("key")
                .transport(transport1)
                .transport(transport2)
                .build();

        // currently observer POST apis only support one transport
        assertNotNull(o.getTransports());
        assertEquals(1, o.getTransports().size());
        assertEquals(transport2, o.getTransports().get(0));
        assertEquals(transport2, o.getTransport());
    }

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
        Condition condition = gson.fromJson(TestJson.CONDITION, Condition.class);
        Map<String, Condition> conditions = ImmutableMap.of(
                Condition.Type.DEBOUNCE.getKey(), condition,
                Condition.Type.THRESHOLD.getKey(), condition,
                Condition.Type.PROPERTY_CHANGED.getKey(), condition,
                Condition.Type.THROTTLE.getKey(), condition
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
        Condition c = gson.fromJson(TestJson.CONDITION, Condition.class);
        Map<String, Condition> conditions = ImmutableMap.of(
                Condition.Type.DEBOUNCE.getKey(), c,
                Condition.Type.THRESHOLD.getKey(), c,
                Condition.Type.PROPERTY_CHANGED.getKey(), c,
                Condition.Type.THROTTLE.getKey(), c
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