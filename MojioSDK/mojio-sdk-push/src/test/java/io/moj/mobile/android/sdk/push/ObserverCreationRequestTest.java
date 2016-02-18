package io.moj.mobile.android.sdk.push;

import com.google.gson.Gson;

import org.junit.Test;

import io.moj.mobile.android.sdk.TestJson;

import static junit.framework.Assert.assertEquals;

/**
 * Created by skidson on 16-02-18.
 */
public class ObserverCreationRequestTest {

    @Test
    public void testSerialization() {
        String key = "key";
        String subject = "subject";
        Observer.Type type = Observer.Type.MOJIO;
        Transport transport = Transport.forAndroid("gcmRegId");

        Condition[] conditions = new Condition[] {
                Condition.delay(2, 0, 0, 0),
                Condition.throttle("Speed.Value", "0.00:01:00.0000")
        };
        String[] fields = new String[] { "LastContactTime", "LastContactTime" };

        ObserverCreationRequest o = new ObserverCreationRequest.Builder(key)
                .subject(subject)
                .type(type)
                .transport(transport)
                .condition(conditions[0])
                .condition(conditions[1])
                .field(fields[0])
                .field(fields[1])
                .build();

        String json = new Gson().toJson(o, ObserverCreationRequest.class);
        assertEquals(TestJson.OBSERVER_REQUEST, json);
    }

}
