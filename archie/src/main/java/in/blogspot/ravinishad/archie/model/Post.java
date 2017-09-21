package in.blogspot.ravinishad.archie.model;

import com.google.gson.annotations.Expose;

/**
 * Created by ravi on 9/14/17.
 */

public class Post {

    @Expose
    private int userId;
    @Expose
    private int id;
    @Expose
    private String title;
    @Expose
    private String body;

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
