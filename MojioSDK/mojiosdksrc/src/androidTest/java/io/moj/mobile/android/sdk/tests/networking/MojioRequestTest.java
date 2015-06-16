package io.moj.mobile.android.sdk.tests.networking;

import android.content.Context;
import android.test.AndroidTestCase;

import io.moj.mobile.android.sdk.MojioClient;
import io.moj.mobile.android.sdk.tests.TestHelper;

/**
 * Created by ssawchenko on 15-05-15.
 */
public class MojioRequestTest extends AndroidTestCase {

    Context mCtx;
    MojioClient mMojioClient;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        mCtx = getContext();

        mMojioClient = new MojioClient(mCtx, TestHelper.MOJIO_APP_ID, TestHelper.MOJIO_SECRET_KEY, TestHelper.REDIRECT_URL);
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();

        mMojioClient.logout();
        mMojioClient = null;
    }




}
