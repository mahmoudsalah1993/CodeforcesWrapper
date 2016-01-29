package CodeforcesWrapper.models;

/**
 * Created by Mahmoud on 26/01/2016.
 */
public class ProblemResult {

    private double points;
    private int penalty;
    private int rejectedAttemptCount;
    public enum ProblemResultType{
        PRELIMINARY, FINAL
    }
    private ProblemResultType type;
    private int bestSubmissionTimeSeconds;

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

    public int getRejectedAttemptCount() {
        return rejectedAttemptCount;
    }

    public void setRejectedAttemptCount(int rejectedAttemptCount) {
        this.rejectedAttemptCount = rejectedAttemptCount;
    }

    public ProblemResultType getType() {
        return type;
    }

    public void setType(ProblemResultType type) {
        this.type = type;
    }

    public int getBestSubmissionTimeSeconds() {
        return bestSubmissionTimeSeconds;
    }

    public void setBestSubmissionTimeSeconds(int bestSubmissionTimeSeconds) {
        this.bestSubmissionTimeSeconds = bestSubmissionTimeSeconds;
    }
}
