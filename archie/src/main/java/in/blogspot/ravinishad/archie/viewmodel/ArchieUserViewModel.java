package in.blogspot.ravinishad.archie.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import in.blogspot.ravinishad.archie.model.User;
import in.blogspot.ravinishad.archie.utils.ArchieConstants;

/**
 * Created by ravi on 9/13/17.
 */

public class ArchieUserViewModel extends BaseArchieViewModel<User[]> implements ArchieConstants {

    private static final String TAG = ArchieUserViewModel.class.getSimpleName();

    private MutableLiveData<User[]> mArchieUsers;

    public LiveData<User[]> getArchie() {

        if (mArchieUsers == null) {
            mArchieUsers = new MutableLiveData<>();
            loadArchiesFromServer(mArchieUsers, User[].class);
        }

        Log.e(TAG, "Archie User was created : " + mArchieUsers);

        return mArchieUsers;
    }

    @Override
    String getApiEP() {
        return USERS_EP;
    }

}
