package in.blogspot.ravinishad.stylemvplegacy.server;

import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Random;

import in.blogspot.ravinishad.stylemvplegacy.modals.LoginResponse;
import in.blogspot.ravinishad.stylemvplegacy.server.constants.ServerCallPurpose;
import in.blogspot.ravinishad.stylemvplegacy.server.constants.ServerConstants;
import in.blogspot.ravinishad.stylemvplegacy.server.modals.HttpError;
import in.blogspot.ravinishad.stylemvplegacy.server.modals.ServerException;

/**
 * This will hold all the business logic here when all need to done from server side
 * Created by ravi on 2/1/17.
 */
public class StyleMVPOnlineImplementation implements RemoteTaskResponseListener {


    public StyleMVPOnlineImplementation setApiResponseListener(ApiResponseListener iApiResponseListener) {
        this.mApiResponseListener = iApiResponseListener;
        return this;
    }

    public StyleMVPOnlineImplementation setUIEventContract(UIEventContract iUIEventContract) {
        this.mUIEventContract = iUIEventContract;
        return this;
    }

    private ApiResponseListener mApiResponseListener;
    private UIEventContract mUIEventContract;

    // FIXME Remove Random
    private static Random sRandom = new Random();
    private static final String TAG = StyleMVPOnlineImplementation.class.getSimpleName();
    private int mCallPurpose;
    private String mExceptionMessage;

    private static StyleMVPOnlineImplementation sInstance;

    public static StyleMVPOnlineImplementation getInstance() {
        if(sInstance==null)
            sInstance = new StyleMVPOnlineImplementation();

        return sInstance;
    }

    /**
     * This launches the api request in background
     * @param iRequestBundle
     */
    public void launchBackgroundTask(Bundle iRequestBundle){

        new AsyncTask<Bundle, Object, Object>(){

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                mUIEventContract.startLoading();
            }

            @Override
            protected Object doInBackground(Bundle... iRequestBundle) {
                // Check for any parameter validity
                Bundle requestData = iRequestBundle[0];

                if (allParamsPresent(requestData)) {
                    mCallPurpose = requestData.getInt(ServerConstants.REQUEST_CALL_PURPOSE);

                    // TODO Add a required type of HttpClient
                    try {
                        // Lets sleep for 1.5 sec
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    Object responseObject =  createRestClientAndCallApi(requestData);
                    return responseObject;
                } else {
                    //
                    mExceptionMessage = "Illegal Bundle Parameters";
                    return null;
                }
            }

            @Override
            protected void onPostExecute(Object iResult) {
                super.onPostExecute(iResult);
                // This has to be called always
                mUIEventContract.stopLoading();

                if (iResult != null) {
                    if (!(iResult instanceof ServerException || iResult instanceof HttpError)) {
                        mApiResponseListener.apiSucceeded(iResult, mCallPurpose);
                    } else if (iResult instanceof ServerException) {
                        mApiResponseListener.apiFailed((ServerException) iResult, mCallPurpose);
                    } else {
                        mApiResponseListener.httpError((HttpError) iResult, mCallPurpose);
                    }
                } else {
                    // Cannot connect to host error. Better to show Dialog or Toast here but lets stick with MVP internals
                    mApiResponseListener.exceptionCaught(mCallPurpose, mExceptionMessage);
                }
            }
        }.execute(iRequestBundle);
    }

    /**
     * check if all params are valid and present
     *
     * @param iApiRequestData complete api request data
     * @return boolean based upon parameters
     */
    private static boolean allParamsPresent(Bundle iApiRequestData) {
        // FIXME remove this random part.
        // TODO Add validation implementation
        return true;//sRandom.nextBoolean();
    }

    /**
     * Method creates REST client to call api with provide request details
     *
     * @param iRequestData Bundle containing all the information in the request bundle
     * @return parsed object
     */
    private Object createRestClientAndCallApi(Bundle iRequestData) {

        // TODO Add a required type of HttpClient
        int callPurpose = iRequestData.getInt(ServerConstants.REQUEST_CALL_PURPOSE);
        String requestMethod = iRequestData.getString(ServerConstants.REQUEST_HTTP_METHOD);
        String requestBodyContent = iRequestData.getString(ServerConstants.REQUEST_BODY_CONTENT);

        String urlString = EndPointUrls.getEndpointUrl(callPurpose);
        // Check in case of GET request where some data need to be added

        HttpURLConnection httpURLConnection = null;

        try {
            Log.e(TAG, "Url string : "+urlString);

            URL url = new URL(urlString);
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod(requestMethod);
            httpURLConnection.setReadTimeout(60000);
            httpURLConnection.setConnectTimeout(60000);

            httpURLConnection.setDoInput(true);
            httpURLConnection.setUseCaches(true);

            // Check headers and add in the httpUrlConnection
            String[] headerKeys = {ServerConstants.ServerHeaderKeys.AUTHORIZATION,
                    ServerConstants.ServerHeaderKeys.CONTENT_LENGTH, ServerConstants.ServerHeaderKeys.CONTENT_TYPE};

            // Loop over headers to add in request
            for (String header : headerKeys) {
                String headerValue = iRequestData.getString(header);

                if (!TextUtils.isEmpty(headerValue))
                    httpURLConnection.setRequestProperty(header, headerValue);
            }

            // Few more properties when request method is POST
            if (ServerConstants.RequestMethod.METHOD_POST.equals(requestMethod)) {
                // It is required to be enabled for PUT and POST
                httpURLConnection.setDoOutput(true);

                if (!TextUtils.isEmpty(requestBodyContent)) {
                    OutputStream ous = httpURLConnection.getOutputStream();
                    ous.write(requestBodyContent.getBytes());
                    ous.close();
                }
            }

            //Check few properties of HttpURLConnection based upon the request method. Write request body
            int statusCode = httpURLConnection.getResponseCode();
            Gson gson = new Gson();

            if (statusCode == HttpURLConnection.HTTP_OK) {
                // Check and parse api response if valid
                InputStream is = httpURLConnection.getInputStream();
                // TODO Read input stream and parse
                String validResponse = streamToString(is);

                switch (mCallPurpose){
                    case ServerCallPurpose.LOGIN_POST:
                        return gson.fromJson(validResponse, LoginResponse.class);
                    case ServerCallPurpose.PROFILE_GET:
                        // TODO
                        break;
                    default:
                        mExceptionMessage = "Illegal ServerCallPurpose";
                        return null;
                }

                // 1. Valid Parsed Object. // 2. Valid Server Response Error

            } else {
                InputStream es = httpURLConnection.getErrorStream();
                // TODO Read error stream and parse
                // 3. Check for http Error
                String errorResponse = streamToString(es);
                Log.e(TAG, "Error response : "+errorResponse);

                String httpErrorMsg  = httpURLConnection.getResponseMessage()!=null?httpURLConnection.getResponseMessage():errorResponse;

                switch (statusCode) {
                    case HttpURLConnection.HTTP_BAD_REQUEST:
                        // Update message in this case if required
                        // httpErrorMsg = "<Message for bad reuqets>"
                        break;
                    case HttpURLConnection.HTTP_UNAUTHORIZED:
                        break;
                    case HttpURLConnection.HTTP_NOT_FOUND:
                        break;
                    case HttpURLConnection.HTTP_BAD_METHOD:
                        break;
                    case HttpURLConnection.HTTP_CLIENT_TIMEOUT:
                        break;
                    case HttpURLConnection.HTTP_CONFLICT:
                        break;
                    case HttpURLConnection.HTTP_INTERNAL_ERROR:
                        break;
                }

                // Create a simple Http Error message
                HttpError httpError = new HttpError();
                httpError.setErrorCode(statusCode);
                httpError.setErrorMessage(httpErrorMsg);

                return httpError;
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
            mExceptionMessage = "Url is malformed";
        } catch (NullPointerException e) {
            e.printStackTrace();
            mExceptionMessage = "Something wrong happen.";
        } catch (SocketException e) {
            e.printStackTrace();
            mExceptionMessage = "Some issue with socket connection";
        } catch (SocketTimeoutException e) {
            e.printStackTrace();
            mExceptionMessage = "There was a request timeout";
        } catch (IOException e) {
            e.printStackTrace();
            mExceptionMessage = "There was an issue with IO.";
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
        }

        // Possibilities:
        // 1. Parsed Valid Object - Also includes valid Server error responses
        // 2. Api Error Response
        // 3. null Cannot connect to host error

        return null;
    }


    /**
     * Reads from the inputstream into turns it to a string
     *
     * @param iInputStream inputstream
     */
    private String streamToString(InputStream iInputStream) {
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(iInputStream));
            StringBuilder responseBuffer = new StringBuilder();

            while ((line = bufferedReader.readLine()) != null) {
                responseBuffer.append(line);
            }

            // return the string back to caller
            line = responseBuffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return line;
    }

}
