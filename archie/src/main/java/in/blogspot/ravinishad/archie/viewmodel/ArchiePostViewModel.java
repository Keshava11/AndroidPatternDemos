package in.blogspot.ravinishad.archie.viewmodel;

import android.app.ProgressDialog;
import android.arch.lifecycle.MutableLiveData;

import in.blogspot.ravinishad.archie.model.Post;
import in.blogspot.ravinishad.archie.utils.ArchieConstants;

/**
 * Created by ravi on 9/14/17.
 */

public class ArchiePostViewModel extends BaseArchieViewModel<Post[]> {

    private MutableLiveData<Post[]> mArchiePost = null;
    private int mUserId;
    private ProgressDialog mProgressDialog;

    public MutableLiveData<Post[]> getArchiePost(int iUserId) {
        mUserId = iUserId;

        if (mArchiePost == null) {
            mArchiePost = new MutableLiveData<>();
            loadArchiesFromServer(mArchiePost, Post[].class);
        }

        return mArchiePost;
    }


    @Override
    String getApiEP() {
        return ArchieConstants.POST_BY_USER_EP + "?userId=" + mUserId;
    }

}
