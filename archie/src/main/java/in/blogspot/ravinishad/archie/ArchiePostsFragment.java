package in.blogspot.ravinishad.archie;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.blogspot.ravinishad.archie.model.Post;
import in.blogspot.ravinishad.archie.ui.PostsAdapter;
import in.blogspot.ravinishad.archie.ui.PostsClickCallback;
import in.blogspot.ravinishad.archie.utils.ArchieConstants;
import in.blogspot.ravinishad.archie.viewmodel.ArchiePostViewModel;

/**
 * Created by ravi on 9/13/17.
 */

public class ArchiePostsFragment extends LifecycleFragment implements PostsClickCallback {

    protected static final String TAG = ArchiePostsFragment.class.getSimpleName();

    @BindView(R.id.posts_screen_title_txv)
    protected TextView mPostsTitleTxv;

    @BindView(R.id.posts_recycler_view)
    protected RecyclerView mPostsRecyclerView;

    private PostsAdapter mAdapter;

    public static ArchiePostsFragment getInstance(int iUserID, String iUserName) {
        Bundle args = new Bundle();
        args.putInt(ArchieConstants.USER_ID, iUserID);
        args.putString(ArchieConstants.USER_NAME, iUserName);

        ArchiePostsFragment archiePostsFragment = new ArchiePostsFragment();
        archiePostsFragment.setArguments(args);

        return archiePostsFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archie_posts, container, false);
        ButterKnife.bind(this, view);
        initializeViews();
        return view;
    }

    private void initializeViews() {

        mAdapter = new PostsAdapter(this);
        mPostsRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mPostsRecyclerView.setAdapter(mAdapter);

        Bundle args = getArguments();
        int userId = args.getInt(ArchieConstants.USER_ID);
        String userName = args.getString(ArchieConstants.USER_NAME);

        mPostsTitleTxv.setText(getString(R.string.post_by_user, userName));

        ViewModelProviders.of(this).get(ArchiePostViewModel.class).getArchiePost(userId)
                .observe(this, new Observer<Post[]>() {
                    @Override
                    public void onChanged(@Nullable Post[] posts) {
                        mAdapter.setPosts(posts);
                        mAdapter.notifyDataSetChanged();
                    }
                });
    }

    @Override
    public void onPostClick(Post iPost) {
        Toast.makeText(getActivity(), "Id of post selected :" + iPost.getId(), Toast.LENGTH_SHORT).show();
    }
}
