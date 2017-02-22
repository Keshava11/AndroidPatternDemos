package in.blogspot.ravinishad.stylemvplegacy.server;

import android.os.Bundle;

import in.blogspot.ravinishad.stylemvplegacy.server.constants.ServerCallPurpose;

/**
 * FIXME Not used anymore
 * Wrapper for worker implementation. It delegates to Online and offline implementations accordingly
 * Created by ravi on 2/1/17.
 */
public class RemoteApi {

    private static RemoteApi sRemoteApi;
    private RemoteTaskResponseListener mRemoteTaskResponseListener;

    private RemoteApi(RemoteTaskResponseListener iRemoteTaskResponseListener) {
        mRemoteTaskResponseListener = iRemoteTaskResponseListener;
    }

    public static RemoteApi getRemoteApiInstance(RemoteTaskResponseListener iRemoteTaskResponseListener) {
        if (sRemoteApi == null)
            sRemoteApi = new RemoteApi(iRemoteTaskResponseListener);

        return sRemoteApi;
    }

    /**
     * Used to force {@link RemoteApi} to create a new instance next time it's called.
     */
    public static void destroyInstance() {
        sRemoteApi = null;
    }

    /**
     * Method to be called by presenters any background work
     * @param iApiRequestData
     * @param iApiCallPurpose
     */
    public void callApiOrDatabase(Bundle iApiRequestData, int iApiCallPurpose) {

        switch (iApiCallPurpose){

            case ServerCallPurpose.LOGIN_POST:

                ((StyleMVPOnlineImplementation)mRemoteTaskResponseListener).launchBackgroundTask(iApiRequestData);
                break;
            case ServerCallPurpose.PROFILE_GET:

                break;
        }
    }

}
