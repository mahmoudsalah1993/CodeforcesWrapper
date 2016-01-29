package CodeforcesWrapper;

import CodeforcesWrapper.models.RatingChange;

import java.util.ArrayList;


/**
 * Created by Mahmoud on 27/01/2016.
 */
public class Wrapper {

    private String key;
    private String secret;

    public Wrapper(String key , String secret){
        this.key = key;
        this.secret = secret;
    }

    public ArrayList<RatingChange> getUserRating(){
        return null;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
