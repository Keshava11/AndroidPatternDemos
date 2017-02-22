package in.blogspot.ravinishad.stylemvplegacy.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ravi.stylemvplegacy.R;
import in.blogspot.ravinishad.stylemvplegacy.modals.LoginResponse;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ravi on 2/3/17.
 */
public class ProfileActivity extends AppCompatActivity {

    @BindView(R.id.profile_imv)
    ImageView mProfileImv;
    @BindView(R.id.profile_name_txv)
    TextView mProfileTxv;
    @BindView(R.id.profile_email_txv)
    TextView mProfileEmailTxv;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mUnbinder = ButterKnife.bind(this);

        Intent incomingIntent = getIntent();
        LoginResponse loginResponse = incomingIntent.getParcelableExtra("user_info");

        String imageUrl = loginResponse.getImageUrl();
        String name = loginResponse.getUserName();
        String emaild = loginResponse.getEmailId();

        Picasso.with(this).load(imageUrl).resize(100, 100).into(mProfileImv);

        mProfileTxv.setText(name);
        mProfileEmailTxv.setText(emaild);
    }


    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }
}
