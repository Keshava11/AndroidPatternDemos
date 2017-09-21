package in.blogspot.ravinishad.archie.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ravi on 9/13/17.
 */

public class ArchiePost {

    public List<Post> getUserList() {
        return mUserList;
    }

    public ArchiePost setUserList(List<Post> mUserList) {
        this.mUserList = mUserList;
        return this;
    }

    private List<Post> mUserList = new ArrayList<>();

}
