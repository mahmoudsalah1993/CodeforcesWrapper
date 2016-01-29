package CodeforcesWrapper.models;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 26/01/2016.
 */
public class Problem {

    private int contestId;
    private String index;
    private String name;
    public enum ContestType {
        PROGRAMMING, QUESTION
    }
    private ContestType type;
    private double points;
    private ArrayList<String> tags;

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ContestType getType() {
        return type;
    }

    public void setType(ContestType type) {
        this.type = type;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public ArrayList<String> getTags() {
        return tags;
    }

    public void setTags(ArrayList<String> tags) {
        this.tags = tags;
    }
}
