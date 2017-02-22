package in.blogspot.ravinishad.stylemvplegacy.server.constants;

/**
 * Created by ravi on 2/2/17.
 */
public class ServerConstants {
    public static final String REQUEST_BODY_CONTENT = "request_body_content";
    public static final String REQUEST_CALL_PURPOSE = "request_call_purpose";
    public static final String REQUEST_HTTP_METHOD = "request_api_method";

    public static class RequestMethod {
        public static final String METHOD_GET = "GET";
        public static final String METHOD_POST = "POST";
        public static final String METHOD_PUT = "PUT";
        public static final String METHOD_DELETE = "DELETE";
    }

    public static class ServerHeaderKeys {
        public static final String AUTHORIZATION = "header_authorization";
        public static final String CONTENT_TYPE = "header_content_type";
        public static final String CONTENT_LENGTH = "header_content_length";
    }
}

