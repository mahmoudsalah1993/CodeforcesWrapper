package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.User;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class UserListSuccessfulResponse extends SuccessfulResponse {

    private ArrayList<User> users;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
