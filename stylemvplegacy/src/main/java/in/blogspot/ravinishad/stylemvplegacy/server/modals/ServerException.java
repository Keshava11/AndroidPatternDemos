package in.blogspot.ravinishad.stylemvplegacy.server.modals;

import com.google.gson.annotations.Expose;

/**
 * Created by ravi on 2/2/17.
 */
public class ServerException {

    @Expose
    private int statusCode;
    @Expose
    private String statusText;

    public int getStatusCode() {
        return statusCode;
    }

    public ServerException setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusText() {
        return statusText;
    }

    public ServerException setStatusText(String statusText) {
        this.statusText = statusText;
        return this;
    }

}
