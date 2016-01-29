package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.Hack;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 29/01/2016.
 */
public class ContestHacksSuccessfulResponse extends SuccessfulResponse {

    private ArrayList<Hack> hacks;

    public ArrayList<Hack> getHacks() {
        return hacks;
    }

    public void setHacks(ArrayList<Hack> hacks) {
        this.hacks = hacks;
    }
}
