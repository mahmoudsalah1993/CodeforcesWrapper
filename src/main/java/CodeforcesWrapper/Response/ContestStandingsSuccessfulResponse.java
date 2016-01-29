package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.Contest;
import CodeforcesWrapper.models.Problem;
import CodeforcesWrapper.models.RanklistRow;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 29/01/2016.
 */
public class ContestStandingsSuccessfulResponse extends SuccessfulResponse{

    private Contest contest;
    private ArrayList<Problem> problems;
    private ArrayList<RanklistRow> rows;

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contest) {
        this.contest = contest;
    }

    public ArrayList<Problem> getProblems() {
        return problems;
    }

    public void setProblems(ArrayList<Problem> problems) {
        this.problems = problems;
    }

    public ArrayList<RanklistRow> getRows() {
        return rows;
    }

    public void setRows(ArrayList<RanklistRow> rows) {
        this.rows = rows;
    }
}
