package CodeforcesWrapper.Response;

import CodeforcesWrapper.models.Submission;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class SubmissionListSuccessfulResponse extends SuccessfulResponse {

    private ArrayList<Submission> submissions;

    public ArrayList<Submission> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(ArrayList<Submission> submissions) {
        this.submissions = submissions;
    }
}
