package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.Problem;
import CodeforcesWrapper.models.ProblemStatistics;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class ProblemSetProblemsSuccessfulResponse extends SuccessfulResponse{

    private ArrayList<Problem> problems;
    private ArrayList<ProblemStatistics> problemStatistics;

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public void setProblems(ArrayList<Problem> problems) {
        this.problems = problems;
    }

    public ArrayList<ProblemStatistics> getProblemStatistics() {
        return problemStatistics;
    }

    public void setProblemStatistics(ArrayList<ProblemStatistics> problemStatistics) {
        this.problemStatistics = problemStatistics;
    }
}
