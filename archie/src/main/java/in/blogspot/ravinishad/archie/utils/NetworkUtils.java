package in.blogspot.ravinishad.archie.utils;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by ravi on 9/13/17.
 */

public class NetworkUtils {

    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private static final Request.Builder BUILDER = new Request.Builder();

    public static void getData(String iUrl, Callback iCallback) throws IOException {
        OK_HTTP_CLIENT.newCall(BUILDER.url(iUrl).build()).enqueue(iCallback);
    }
}
