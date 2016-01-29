package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.FailedResponseException;
import CodeforcesWrapper.Response.ProblemSetProblemsSuccessfulResponse;
import CodeforcesWrapper.Response.SubmissionListSuccessfulResponse;
import CodeforcesWrapper.models.Submission;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class ProblemSetRequest {

    private static String baseUrl = "http://www.codeforces.com/api/problemset.";

    public static ArrayList<Submission> getProblemSetSubmmissions(int count) throws IOException, FailedResponseException {
        String url = baseUrl;
        url =url.concat("recentStatus?count=");
        url =url.concat(String.valueOf(count));
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            SubmissionListSuccessfulResponse success = gson.fromJson(reader,SubmissionListSuccessfulResponse.class);
            return success.getSubmissions();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }
    //http://www.codeforces.com/api/problemset.implementation
    public static ProblemSetProblemsSuccessfulResponse getProblemSetProblems(ArrayList<String> tags) throws IOException, FailedResponseException {
        String url = baseUrl;
        url =url.concat("problems?tags=");
        for(int i=0;i<tags.size();i++){
            url = url.concat(tags.get(i));
            if(i!=tags.size()-1)
                url = url.concat(";");
        }
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            ProblemSetProblemsSuccessfulResponse success = gson.fromJson(reader,ProblemSetProblemsSuccessfulResponse.class);
            return success;
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }
}
