package in.blogspot.ravinishad.archie.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import in.blogspot.ravinishad.archie.R;
import in.blogspot.ravinishad.archie.model.Address;
import in.blogspot.ravinishad.archie.model.User;

/**
 * Created by ravi on 9/14/17.
 */

public class UsersAdapter extends BaseAdapter {

    public UsersAdapter setArchie(User[] mArchie) {
        this.mArchie = mArchie;
        return this;
    }

    private User[] mArchie;
    private LayoutInflater mLayoutInflater;

    public UsersAdapter(Context iContext) {
        mLayoutInflater = LayoutInflater.from(iContext);
    }

    @Override
    public int getCount() {
        return mArchie != null ? mArchie.length : 0;
    }

    @Override
    public Object getItem(int i) {
        return mArchie != null ? mArchie[i] : null;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        UserViewHolder userViewHolder;

        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.view_item_archie_user, viewGroup, false);
            userViewHolder = new UserViewHolder();

            userViewHolder.mNameTxv = view.findViewById(R.id.user_name_txv);
            userViewHolder.mEmailTxv = view.findViewById(R.id.user_email_txv);
            userViewHolder.mAddressTxv = view.findViewById(R.id.user_address_txv);
            userViewHolder.mPhoneTxv = view.findViewById(R.id.user_phone_txv);

            view.setTag(userViewHolder);
        } else {
            userViewHolder = (UserViewHolder) view.getTag();
        }

        User user = mArchie[i];
        userViewHolder.mNameTxv.setText(user.getName());
        userViewHolder.mEmailTxv.setText(user.getEmail());

        Address address = user.getAddress();
        userViewHolder.mAddressTxv.setText(address != null ? address.toString() : "");
        userViewHolder.mPhoneTxv.setText(user.getPhone());

        return view;
    }

    private static class UserViewHolder {

        TextView mNameTxv;
        TextView mEmailTxv;
        TextView mAddressTxv;
        TextView mPhoneTxv;
    }

}
