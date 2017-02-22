package in.blogspot.ravinishad.stylemvplegacy.server;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import in.blogspot.ravinishad.stylemvplegacy.StyleMVPApplication;

/**
 * This is for the all offline implmentation to be done using preferences, database and content provider
 * Created by ravi on 2/1/17.
 */
public class StyleMVPOfflineImplementation implements RemoteTaskResponseListener {

    private static StyleMVPOfflineImplementation sInstance;
    private static SharedPreferences sSharedPreferences;

    private StyleMVPOfflineImplementation() {
        sSharedPreferences = PreferenceManager.getDefaultSharedPreferences(StyleMVPApplication.getContext());
    }

    public static StyleMVPOfflineImplementation getInstance() {
        if (sInstance == null)
            sInstance = new StyleMVPOfflineImplementation();
        return sInstance;
    }

    /**
     * Check if login details saved in preferences
     *
     * @return boolean based upon presence of valid login
     */
    public boolean checkForValidLogin() {
        boolean emailExists = sSharedPreferences.contains("key_prefs_email");
        boolean passwordExists = sSharedPreferences.contains("key_prefs_password");

        if (!(emailExists && passwordExists)) {
            return false;
        } else {
            String email = sSharedPreferences.getString("key_prefs_email", null);
            String password = sSharedPreferences.getString("key_prefs_password", null);
            return !(TextUtils.isEmpty(email) && TextUtils.isEmpty(password));
        }
    }

    /**
     * Gets the profile data from local
     * @return modal class holding all the details of the loggedIn used saved after last api call
     */
    public Object getProfileData(){

        return null;
    }

}
