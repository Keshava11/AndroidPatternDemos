package in.blogspot.ravinishad.stylemvplegacy.login;

import android.os.Bundle;
import android.text.TextUtils;

import com.google.gson.Gson;
import in.blogspot.ravinishad.stylemvplegacy.login.modals.LoginRequest;
import in.blogspot.ravinishad.stylemvplegacy.server.ApiResponseListener;
import in.blogspot.ravinishad.stylemvplegacy.server.StyleMVPOnlineImplementation;
import in.blogspot.ravinishad.stylemvplegacy.server.UIEventContract;
import in.blogspot.ravinishad.stylemvplegacy.server.constants.ServerCallPurpose;
import in.blogspot.ravinishad.stylemvplegacy.server.constants.ServerConstants;
import in.blogspot.ravinishad.stylemvplegacy.server.modals.HttpError;
import in.blogspot.ravinishad.stylemvplegacy.server.modals.ServerException;

/**
 * Created by ravi on 2/1/17.
 */
class LoginPresenter implements LoginContract.Presenter, UIEventContract, ApiResponseListener {

    private StyleMVPOnlineImplementation mOnlineApiImplementation;
    private LoginContract.LoginView mLoginView;

    LoginPresenter(StyleMVPOnlineImplementation iOnlineApiImplementation, LoginContract.LoginView iLoginView) {
        mOnlineApiImplementation = iOnlineApiImplementation;

        // Initialize the view
        mOnlineApiImplementation.setApiResponseListener(this);
        mOnlineApiImplementation.setUIEventContract(this);

        mLoginView = iLoginView;
        mLoginView.setPresenter(this);
    }

    /**
     * Validates form elements to be non empty
     * @param iUserName iUserName
     * @param iPassword iPassword
     * @return validation succeeded
     */
    @Override
    public boolean validateForm(String iUserName, String iPassword) {
        return !(TextUtils.isEmpty(iUserName) && TextUtils.isEmpty(iPassword));
    }

    /**
     * Submit api login form with the given emailid and password
     * @param iEmailId emailId
     * @param iPassword password
     */
    @Override
    public void submitLoginForm(String iEmailId, String iPassword) {
        // Disable button here
        mLoginView.enableButton(false);

        // Frame Api Request
        LoginRequest loginRequest = new LoginRequest().setEmailId(iEmailId).setPassword(iPassword);
        String loginRequestString = new Gson().toJson(loginRequest);

        // Request Bundle
        Bundle requestBundle = new Bundle();

        requestBundle.putString(ServerConstants.REQUEST_BODY_CONTENT, loginRequestString);
        requestBundle.putInt(ServerConstants.REQUEST_CALL_PURPOSE, ServerCallPurpose.LOGIN_POST);
        requestBundle.putString(ServerConstants.REQUEST_HTTP_METHOD, ServerConstants.RequestMethod.METHOD_POST);

        // Header Keys details
        requestBundle.putString(ServerConstants.ServerHeaderKeys.AUTHORIZATION, null);//"Some authorization");
        requestBundle.putString(ServerConstants.ServerHeaderKeys.CONTENT_LENGTH, null);//String.valueOf(loginRequestString.length()));
        requestBundle.putString(ServerConstants.ServerHeaderKeys.CONTENT_TYPE, "application/json");

        // Launch Api Implementor
        mOnlineApiImplementation.launchBackgroundTask(requestBundle);
    }

    @Override
    public void configurePresenter() {
        // TODO Don't know what need to be done
    }


    @Override
    public void startLoading() {
        mLoginView.showLoadingBar("Logging in...");
    }

    @Override
    public void stopLoading() {
        mLoginView.cancelLoadingBar();
        // Also enabled the button
        mLoginView.enableButton(true);
    }

    @Override
    public void apiSucceeded(Object iValidResponse, int iApiCallType) {
        // Update the Views with the latest response
        mLoginView.clearForm();
        mLoginView.updateViews(iValidResponse);
    }

    @Override
    public void apiFailed(ServerException iErrorResponse, int iApiCallType) {
        // Show either dialog or error messages
        mLoginView.clearForm();
        mLoginView.showToastMessage(iErrorResponse.getStatusText());
    }

    @Override
    public void httpError(HttpError iHttpError, int iApiCallType) {
        // Show either dialog or error messages
        mLoginView.clearForm();
        mLoginView.showToastMessage(iHttpError.getErrorMessage());
    }

    @Override
    public void exceptionCaught(int iApiCallType, String iMessage) {
        mLoginView.clearForm();
        // Show either dialog or error messages
        if (!TextUtils.isEmpty(iMessage)) {
            mLoginView.showToastMessage(iMessage);
        }
    }
}
