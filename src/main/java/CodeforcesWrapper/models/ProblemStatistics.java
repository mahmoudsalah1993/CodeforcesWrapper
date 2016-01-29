package CodeforcesWrapper.models;

/**
 * Created by Mahmoud on 26/01/2016.
 */
public class ProblemStatistics {

    private int contestId;
    private String index;
    private int solvedCount;

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

    public int getSolvedCount() {
        return solvedCount;
    }

    public void setSolvedCount(int solvedCount) {
        this.solvedCount = solvedCount;
    }
}
