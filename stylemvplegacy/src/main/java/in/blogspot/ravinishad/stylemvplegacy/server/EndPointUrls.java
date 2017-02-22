package in.blogspot.ravinishad.stylemvplegacy.server;

import android.text.TextUtils;
import android.util.SparseArray;

import in.blogspot.ravinishad.stylemvplegacy.server.constants.ServerCallPurpose;


/**
 * Class holding all the details about all the api urls
 */
public class EndPointUrls {
    // Updates its size with increase in number of api's
    private static final SparseArray<String> ENDPOINT_URLS = new SparseArray<>(2);
    private static final String LINE_SEPARATOR = "/";

    static {
        formUrls(ServerCallPurpose.LOGIN_POST, "https://reqres.in/api/");
        formUrls(ServerCallPurpose.PROFILE_GET, "https://reqres.in/api/");
    }


    /**
     * This forms an api endpoint url
     *
     * @param iKeyCallPurpose current key call purpose
     * @param iBaseUrl        base url
     */
    private static void formUrls(int iKeyCallPurpose, String iBaseUrl) {

        String apiUrl = null;

        switch (iKeyCallPurpose) {
            case ServerCallPurpose.LOGIN_POST:
                apiUrl = "login";
                break;
            case ServerCallPurpose.PROFILE_GET:
                apiUrl = "users/2";
                break;
        }

        if (apiUrl != null) {
            ENDPOINT_URLS.put(iKeyCallPurpose, iBaseUrl + apiUrl);
        }
    }

    /**
     * This forms an api endpoint url
     *
     * @param iKeyCallPurpose current key call purpose
     * @param iBaseUrl        base url
     * @param iBaseVersion    version of
     */
    private static void formUrls(int iKeyCallPurpose, String iBaseUrl, String iBaseVersion) {

        String apiUrl = null;

        switch (iKeyCallPurpose) {
            case ServerCallPurpose.LOGIN_POST:
                apiUrl = "login";
                break;
            case ServerCallPurpose.PROFILE_GET:
                apiUrl = "users/2";
                break;
        }

        if (apiUrl != null) {
            ENDPOINT_URLS.put(iKeyCallPurpose, iBaseUrl + LINE_SEPARATOR + iBaseVersion + LINE_SEPARATOR + apiUrl);
        }
    }

    /**
     * Get the api endpoint url
     *
     * @param iKeyCallPurpose call purpose for the api
     * @return api endpoint url
     */
    public static String getEndpointUrl(int iKeyCallPurpose) {
        String resolvedApiUrl = ENDPOINT_URLS.get(iKeyCallPurpose);

        if (TextUtils.isEmpty(resolvedApiUrl))
            throw new IllegalArgumentException("No endpoint url exists for call purpose : " + iKeyCallPurpose);

        return resolvedApiUrl;
    }

}
