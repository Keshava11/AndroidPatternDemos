package in.blogspot.ravinishad.stylemvplegacy.server.modals;

/**
 * Created by ravi on 2/2/17.
 */
public class HttpError {

    private int mErrorCode;
    private String mErrorMessage;

    public int getErrorCode() {
        return mErrorCode;
    }

    public HttpError setErrorCode(int mErrorCode) {
        this.mErrorCode = mErrorCode;
        return this;
    }

    public String getErrorMessage() {
        return mErrorMessage;
    }

    public HttpError setErrorMessage(String mErrorMessage) {
        this.mErrorMessage = mErrorMessage;
        return this;
    }

}
