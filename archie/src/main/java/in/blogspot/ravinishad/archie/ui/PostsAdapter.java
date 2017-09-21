package in.blogspot.ravinishad.archie.ui;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import in.blogspot.ravinishad.archie.R;
import in.blogspot.ravinishad.archie.databinding.ViewItemPostsBinding;
import in.blogspot.ravinishad.archie.model.Post;

/**
 * Created by ravi on 9/14/17.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.PostViewHolder> {

    public PostsAdapter setPosts(Post[] iPosts) {
        this.mPosts = iPosts;
        return this;
    }

    private Post[] mPosts;
    private PostsClickCallback mPostsClickCallback;

    public PostsAdapter(PostsClickCallback iPostsClickCallback) {
        mPostsClickCallback = iPostsClickCallback;
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewItemPostsBinding viewItemPostsBinding = DataBindingUtil.
                inflate(LayoutInflater.from(parent.getContext()), R.layout.view_item_posts, parent, false);
        // Set click listener
        viewItemPostsBinding.setPostCallback(mPostsClickCallback);
        return new PostViewHolder(viewItemPostsBinding);
    }

    @Override
    public void onBindViewHolder(PostViewHolder holder, int position) {
        // Binding data
        holder.mViewItemPostsBinding.setUserPost(mPosts[position]);
        holder.mViewItemPostsBinding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mPosts != null ? mPosts.length : 0;
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {

        ViewItemPostsBinding mViewItemPostsBinding;

        PostViewHolder(ViewItemPostsBinding iViewItemPostsBinding) {
            super(iViewItemPostsBinding.getRoot());
            mViewItemPostsBinding = iViewItemPostsBinding;
        }
    }

}
