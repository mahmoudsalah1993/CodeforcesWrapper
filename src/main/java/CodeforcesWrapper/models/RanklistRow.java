package CodeforcesWrapper.models;

/**
 * Created by Mahmoud on 26/01/2016.
 */
public class RanklistRow {

    private Party party;
    private int rank;
    private double points;
    private int penalty;
    private int successfulHackCount;
    private int unsuccessfulHackCount;
    private ProblemResult problemResult;
    private int lastSubmissionTimeSeconds;

    public Party getParty() {
        return party;
    }

    public void setParty(Party party) {
        this.party = party;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public double getPoints() {
        return points;
    }

    public void setPoints(double points) {
        this.points = points;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getSuccessfulHackCount() {
        return successfulHackCount;
    }

    public void setSuccessfulHackCount(int successfulHackCount) {
        this.successfulHackCount = successfulHackCount;
    }

    public int getUnsuccessfulHackCount() {
        return unsuccessfulHackCount;
    }

    public void setUnsuccessfulHackCount(int unsuccessfulHackCount) {
        this.unsuccessfulHackCount = unsuccessfulHackCount;
    }

    public ProblemResult getProblemResult() {
        return problemResult;
    }

    public void setProblemResult(ProblemResult problemResult) {
        this.problemResult = problemResult;
    }

    public int getLastSubmissionTimeSeconds() {
        return lastSubmissionTimeSeconds;
    }

    public void setLastSubmissionTimeSeconds(int lastSubmissionTimeSeconds) {
        this.lastSubmissionTimeSeconds = lastSubmissionTimeSeconds;
    }
}
