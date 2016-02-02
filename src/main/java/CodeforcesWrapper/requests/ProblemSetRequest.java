package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.Response.ProblemSetProblemsSuccessfulResponse;
import CodeforcesWrapper.models.Submission;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class ProblemSetRequest {

    private static String baseUrl = "http://www.codeforces.com/api/problemset.";

    public static ArrayList<Submission> getProblemSetSubmmissions(int count) throws IOException,
            FailedResponseException, JSONException {
        ArrayList<Submission> submissions = new ArrayList<Submission>();
        String url = baseUrl;
        url =url.concat("recentStatus?count=");
        url =url.concat(String.valueOf(count));
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jSubmissions = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jSubmissions.length() ; i++){
                JSONObject jobj =jSubmissions.getJSONObject(i);
                submissions.add(Request.getSubmission(jobj));
            }
        }
        return submissions;
    }


    public static ProblemSetProblemsSuccessfulResponse getProblemSetProblems(ArrayList<String> tags) throws IOException,
            FailedResponseException, JSONException {
        String url = baseUrl;
        url =url.concat("problems?tags=");
        for(int i=0;i<tags.size();i++){
            url = url.concat(tags.get(i));
            if(i!=tags.size()-1)
                url = url.concat(";");
        }
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONObject problems = jsonObject.getJSONObject("result");
            return Request.getProblemsResponse(problems);
        }
    }
}
