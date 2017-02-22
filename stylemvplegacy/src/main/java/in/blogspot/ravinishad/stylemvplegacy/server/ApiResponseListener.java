package in.blogspot.ravinishad.stylemvplegacy.server;

import in.blogspot.ravinishad.stylemvplegacy.server.modals.HttpError;
import in.blogspot.ravinishad.stylemvplegacy.server.modals.ServerException;

/**
 * Created by ravi on 2/2/17.
 */
public interface ApiResponseListener {
    void apiSucceeded(Object iValidResponse, int iApiCallType);
    void apiFailed(ServerException iErrorResponse, int iApiCallType);
    void httpError(HttpError iHttpError, int iApiCallType);
    void exceptionCaught(int iApiCallType, String iMessage);

}
