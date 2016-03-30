package io.moj.mobile.android.sdk.model.requests;

import org.junit.Test;

import static com.google.common.truth.Truth.assertThat;
import static io.moj.mobile.android.sdk.test.TestUtils.assertAccess;
import static io.moj.mobile.android.sdk.test.TestUtils.assertToStringContainsAllFields;

/**
 * Created by skidson on 16-03-23.
 */
public class RequestTests {

    @Test
    public void testMojioClaimRequest() throws IllegalAccessException {
        String imei = "imei";
        MojioClaimRequest request = new MojioClaimRequest(imei);
        assertThat(request.getImei()).isEqualTo(imei);

        assertToStringContainsAllFields(request);
        assertAccess(request);
    }

}
