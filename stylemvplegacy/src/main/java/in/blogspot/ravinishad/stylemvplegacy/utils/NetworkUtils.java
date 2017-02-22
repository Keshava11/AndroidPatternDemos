package in.blogspot.ravinishad.stylemvplegacy.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import in.blogspot.ravinishad.stylemvplegacy.StyleMVPApplication;

/**
 * Created by ravi on 18/5/16.
 */
public class NetworkUtils {

    /**
     * This method checks if network is available or not
     *
     * @return
     */
    public static boolean checkNetworkAvailability() {
        ConnectivityManager connectivityManager = (ConnectivityManager) StyleMVPApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }

    /**
     * This method needs
     *
     * @return
     */
    private static boolean checkInternetAccess() {
        try {
            Process p1 = Runtime.getRuntime().exec("ping -c 1 www.google.com");
            int returnVal = p1.waitFor();
            return (returnVal == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @return
     */
    public static boolean checkActiveInternet() {
        boolean activeInternet = false;
        if (checkNetworkAvailability()) {
            activeInternet = checkInternetAccess();
        }

        return activeInternet;
    }
}
