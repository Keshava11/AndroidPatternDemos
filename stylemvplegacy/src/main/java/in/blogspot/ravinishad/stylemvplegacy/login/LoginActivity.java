package in.blogspot.ravinishad.stylemvplegacy.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ravi.stylemvplegacy.R;
import in.blogspot.ravinishad.stylemvplegacy.modals.LoginResponse;
import in.blogspot.ravinishad.stylemvplegacy.profile.ProfileActivity;
import in.blogspot.ravinishad.stylemvplegacy.server.StyleMVPOnlineImplementation;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ravi on 2/1/17.
 */
public class LoginActivity extends AppCompatActivity implements LoginContract.LoginView, View.OnClickListener {

    private static final String EMPTY = "";
    private LoginPresenter mLoginPresenter;
    private Unbinder mUnbinder;

    @BindView(R.id.email_id_edt)
    EditText mEmailEdt;
    @BindView(R.id.password_edt)
    EditText mPasswordEdt;
    @BindView(R.id.submit_btn)
    Button mLoginBtn;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUnbinder = ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(StyleMVPOnlineImplementation.getInstance(), this);

        initializeViews();
    }

    /**
     * Initializes all the views
     */
    private void initializeViews() {
        mLoginBtn.setOnClickListener(this);
    }

    @Override
    public void enableButton(boolean iShouldEnable) {
        mLoginBtn.setEnabled(iShouldEnable);
    }

    @Override
    public void updateViews(Object iResponseObject) {

        // Clear Fields and Launch Views
        LoginResponse loginResponse = (LoginResponse) iResponseObject;

        if (!TextUtils.isEmpty(loginResponse.getEmailId()) && !TextUtils.isEmpty(loginResponse.getPassword())) {
            showToastMessage("Login is successful");
            // TODO Save them in preferences

        }

        // Launch new Activity
        Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
        profileActivityIntent.putExtra("user_info", loginResponse);
        startActivity(profileActivityIntent);
        finish();
    }

    @Override
    public void clearForm() {
        mEmailEdt.setText(EMPTY);
        mPasswordEdt.setText(EMPTY);
    }

    @Override
    public void showToastMessage(String iMessage) {
        Toast.makeText(this, iMessage, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingBar(String iMessage) {
        // Cancel an existing progress dialog
        if (mProgressDialog != null) {
            if (mProgressDialog.isShowing()) mProgressDialog.dismiss();
            mProgressDialog = null;
        }

        // Create a new progress dialog
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(iMessage);
        mProgressDialog.show();
    }

    @Override
    public void cancelLoadingBar() {
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        mUnbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submit_btn:
                String emailId = mEmailEdt.getText().toString().trim();
                String password = mPasswordEdt.getText().toString().trim();

                //Check if email or password is correct
                if (mLoginPresenter.validateForm(emailId, password)) {
                    mLoginPresenter.submitLoginForm(emailId, password);
                } else {
                    showToastMessage("Invalid emailId or Password.");
                }
                break;
        }

    }

    @Override
    public void setPresenter(LoginContract.Presenter iPresenter) {
        // can't say is there is to be done anything
        // TODO Add respective implementation

    }
}

