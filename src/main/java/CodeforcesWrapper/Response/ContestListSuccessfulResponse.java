package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.Contest;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 29/01/2016.
 */
public class ContestListSuccessfulResponse extends SuccessfulResponse{

    private ArrayList<Contest> contests;

    public ArrayList<Contest> getContests() {
        return contests;
    }

    public void setContests(ArrayList<Contest> contests) {
        this.contests = contests;
    }
}
