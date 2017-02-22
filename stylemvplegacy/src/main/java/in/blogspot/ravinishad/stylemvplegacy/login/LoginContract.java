package in.blogspot.ravinishad.stylemvplegacy.login;

import in.blogspot.ravinishad.stylemvplegacy.BasePresenter;
import in.blogspot.ravinishad.stylemvplegacy.BaseView;

/**
 * Created by ravi on 2/1/17.
 */
public class LoginContract {

    public interface Presenter extends BasePresenter {
        boolean validateForm(String iUserName, String iPassword);

        void submitLoginForm(String iEmailId, String iPassword);

    }

    public interface LoginView extends BaseView<Presenter> {
        void enableButton(boolean iShouldEnable);

        void updateViews(Object iResponseObject);

        void showToastMessage(String iMessage);

        void clearForm();

        /**
         * ProgressDialog specific method to be implemented by classes
         */
        void showLoadingBar(String iMessage);

        void cancelLoadingBar();
    }


}
