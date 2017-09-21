package in.blogspot.ravinishad.archie;

import android.arch.lifecycle.LifecycleActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;

public class ArchieActivity extends LifecycleActivity {

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_archie);

        mFragmentManager = getSupportFragmentManager();

        if (savedInstanceState == null) {
            mFragmentManager.beginTransaction().add(R.id.archie_container, new ArchieUsersFragment(),
                    ArchieUsersFragment.TAG).commit();
        }
    }

    /**
     * Launches a fragment containing Archie posts
     */
    public void showArchiePosts(int iUserId, String iUserName) {

        mFragmentManager.beginTransaction().replace(R.id.archie_container, ArchiePostsFragment.getInstance(iUserId, iUserName),
                ArchiePostsFragment.TAG).addToBackStack(ArchiePostsFragment.TAG).commit();

    }

}
