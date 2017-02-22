package in.blogspot.ravinishad.stylemvplegacy.modals;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

/**
 * Created by ravi on 2/3/17.
 */

public class LoginResponse implements Parcelable{

    protected LoginResponse(Parcel in) {
        statusCode = in.readInt();
        statusText = in.readString();
        emailId = in.readString();
        password = in.readString();
        imageUrl = in.readString();
        userId = in.readString();
        userName = in.readString();
        token = in.readString();
    }

    public static final Creator<LoginResponse> CREATOR = new Creator<LoginResponse>() {
        @Override
        public LoginResponse createFromParcel(Parcel in) {
            return new LoginResponse(in);
        }

        @Override
        public LoginResponse[] newArray(int size) {
            return new LoginResponse[size];
        }
    };

    public int getStatusCode() {
        return statusCode;
    }

    public LoginResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusText() {
        return statusText;
    }

    public LoginResponse setStatusText(String statusText) {
        this.statusText = statusText;
        return this;
    }

    public String getEmailId() {
        return emailId;
    }

    public LoginResponse setEmailId(String emailId) {
        this.emailId = emailId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginResponse setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public LoginResponse setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    @Expose
    private int statusCode;
    @Expose
    private String statusText;
    @Expose
    private String emailId;
    @Expose
    private String password;
    @Expose
    private String imageUrl;
    @Expose
    private String userId;
    @Expose
    private String token;

    public String getUserId() {
        return userId;
    }

    public LoginResponse setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public LoginResponse setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getToken() {
        return token;
    }

    public LoginResponse setToken(String token) {
        this.token = token;
        return this;
    }

    @Expose
    private String userName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(statusCode);
        parcel.writeString(statusText);
        parcel.writeString(emailId);
        parcel.writeString(password);
        parcel.writeString(imageUrl);
        parcel.writeString(userId);
        parcel.writeString(userName);
        parcel.writeString(token);
    }


}
