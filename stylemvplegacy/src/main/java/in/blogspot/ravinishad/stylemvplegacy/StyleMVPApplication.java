package in.blogspot.ravinishad.stylemvplegacy;

import android.app.Application;
import android.content.Context;

/**
 * Created by ravi on 2/1/17.
 */

public class StyleMVPApplication extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}
