package io.moj.mobile.android.sdk.push;

import org.junit.Test;

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

        assertNotNull(o.getConditions());
        assertEquals(conditions.length, o.getConditions().size());
        for (Condition condition : conditions)
            assertEquals("Wrong condition for '" + condition.getType() + "'", condition, o.getConditions().get(condition.getType()));

        assertNotNull(o.getFields());
        assertEquals(fields.length, o.getFields().size());
        for (String field : fields)
            assertTrue("Missing field '" + field + "'", o.getFields().contains(field));
    }

}