package in.blogspot.ravinishad.archie.utils;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by ravi on 9/15/17.
 */

public class ViewUtils {

    public static ProgressDialog getProgressDialog(Context iContext, String iMessage) {
        final ProgressDialog progressDialog = new ProgressDialog(iContext);
        progressDialog.setMessage(iMessage);
        progressDialog.show();

        return progressDialog;
    }

}
