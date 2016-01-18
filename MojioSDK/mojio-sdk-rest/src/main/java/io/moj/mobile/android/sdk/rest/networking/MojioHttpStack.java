package io.moj.mobile.android.sdk.rest.networking;

import android.support.annotation.NonNull;

import com.android.volley.toolbox.HurlStack;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.OkUrlFactory;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Extension of Volley's {@link HurlStack} that uses OkHttp.
 *
 * Created by skidson on 15-09-23.
 */
public class MojioHttpStack extends HurlStack {
    /*
     * Note: this currently uses HttpURLConnection that is known to have issues before Gingerbread
     * see http://android-developers.blogspot.ca/2011/09/androids-http-clients.html.
     * The Mojio SDK currently only supports API 15 and up so this isn't an issue but if we provide
     * older support in the future we should use the following:
     * https://gist.github.com/bryanstern/4e8f1cb5a8e14c202750
     * https://plus.google.com/+JakeWharton/posts/31jhDwaCvtg
     */

    private final OkUrlFactory okUrlFactory;

    public MojioHttpStack() {
        this(new OkUrlFactory(new OkHttpClient()));
    }

    public MojioHttpStack(@NonNull OkUrlFactory okUrlFactory) {
        this.okUrlFactory = okUrlFactory;
    }

    @Override protected HttpURLConnection createConnection(URL url) throws IOException {
        return okUrlFactory.open(url);
    }
}