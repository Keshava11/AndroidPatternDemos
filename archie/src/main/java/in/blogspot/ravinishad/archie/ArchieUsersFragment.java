package in.blogspot.ravinishad.archie;

import android.arch.lifecycle.LifecycleFragment;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import in.blogspot.ravinishad.archie.model.User;
import in.blogspot.ravinishad.archie.ui.UsersAdapter;
import in.blogspot.ravinishad.archie.viewmodel.ArchieUserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ravi on 9/13/17.
 */

public class ArchieUsersFragment extends LifecycleFragment {

    protected static final String TAG = ArchieUsersFragment.class.getSimpleName();

    @BindView(R.id.archie_title_txv)
    protected TextView mArchieTxv;
    @BindView(R.id.archie_lsv)
    protected ListView mArchieLsv;

    private UsersAdapter mUsersAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_archie_users, container, false);
        ButterKnife.bind(this, view);

        initializeViews();
        return view;
    }

    private void initializeViews() {
        // Init adapter for user list. Initially it is empty
        mUsersAdapter = new UsersAdapter(getActivity());
        mArchieLsv.setAdapter(mUsersAdapter);

        // Access the ViewModel that contains the LiveData which can withstand configuration changes
        ArchieUserViewModel model = ViewModelProviders.of(this).get(ArchieUserViewModel.class);
        model.getArchie().observe(this, new Observer<User[]>() {
            @Override
            public void onChanged(@Nullable User[] archie) {
                // Update ListView
                mUsersAdapter.setArchie(archie);
                mUsersAdapter.notifyDataSetChanged();
            }
        });

        mArchieLsv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                User user = (User) adapterView.getItemAtPosition(i);

                // Launch a posts list fragment
                ((ArchieActivity) getActivity()).showArchiePosts(user.getId(), user.getUsername());
            }
        });

    }

}
