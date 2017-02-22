package in.blogspot.ravinishad.stylemvplegacy.utils;

import com.google.gson.Gson;
import com.google.gson.annotations.Expose;

import java.util.HashMap;

/**
 * Created by ravi on 9/2/16.
 */
public class DummyContentUtils {

//    /**
//     * @return
//     */
//    public static List<NameValuePair> getRequestDetailsAsNameValuePair() {
//        final List<NameValuePair> nameValuePairs = new ArrayList<>();
//        nameValuePairs.add(new BasicNameValuePair("email", "ravi@gmail.com"));
//        nameValuePairs.add(new BasicNameValuePair("password", "ravi"));
//        return nameValuePairs;
//    }


    /**
     * @return
     */
    public static HashMap<String, String> getRequestDetailsAsMap() {
        final HashMap<String, String> reqParams = new HashMap<>();
        reqParams.put("email", "asgdashd36363@gmail.com");
        reqParams.put("password", "ravi");
        return reqParams;
    }


    /**
     * @return
     */
    public static String getRequestContentAsJson() {
        LoginParams pars = new LoginParams();
        pars.setEmail("asgdashd36363@gmail.com");
        pars.setPassword("ravi");

        return new Gson().toJson(pars);
    }


    static class LoginParams {
        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Expose
        private String email;
        @Expose
        private String password;
    }




}
