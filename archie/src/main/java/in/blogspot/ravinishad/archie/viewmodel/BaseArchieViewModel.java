package in.blogspot.ravinishad.archie.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import in.blogspot.ravinishad.archie.utils.NetworkUtils;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * Created by ravi on 9/14/17.
 */

abstract class BaseArchieViewModel<T> extends ViewModel{

    void loadArchiesFromServer(final MutableLiveData<T> iMutableData, final Class<T> iResponse) {

        try {
            NetworkUtils.getData(getApiEP(), new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    final String responseStr = response.body().string();

                    Log.e("TAG", "Response fetched for posts : " + responseStr);

                    if (responseStr != null && !responseStr.isEmpty()) {
                        // We parsed here
                        T userData = new Gson().fromJson(responseStr, iResponse);

                        // posting to get it ready for all the observers
                        iMutableData.postValue(userData);

                        Log.d(TAG, "Archie was updated with new data :" + userData);
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    abstract String getApiEP();

}
