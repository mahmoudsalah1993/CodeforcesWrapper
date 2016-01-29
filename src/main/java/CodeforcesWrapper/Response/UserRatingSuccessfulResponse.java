package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.RatingChange;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class UserRatingSuccessfulResponse extends SuccessfulResponse{

    private ArrayList<RatingChange> ratingChanges;

    public ArrayList<RatingChange> getRatingChanges() {
        return ratingChanges;
    }

    public void setRatingChanges(ArrayList<RatingChange> ratingChanges) {
        this.ratingChanges = ratingChanges;
    }
}
