package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.models.RatingChange;
import CodeforcesWrapper.models.Submission;
import CodeforcesWrapper.models.User;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class UserRequest {

    private static String baseUrl = "http://www.codeforces.com/api/user.";

    public static ArrayList<RatingChange> getUserRating(String handle) throws IOException, FailedResponseException,
            JSONException {
        ArrayList<RatingChange> ratingChanges = new ArrayList<RatingChange>();
        String url = baseUrl;
        url =url.concat("rating?handle=");
        url =url.concat(handle);
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jRatingChanges = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jRatingChanges.length() ; i++){
                JSONObject jobj =jRatingChanges.getJSONObject(i);
                ratingChanges.add(Request.getRatingChange(jobj));
            }
        }
        return ratingChanges;
    }

    public static ArrayList<Submission> getUserStatus(String handle,int from,int count) throws IOException,
            FailedResponseException, JSONException {
        ArrayList<Submission> submissions = new ArrayList<Submission>();
        String url = baseUrl;
        url =url.concat("status?handle=");
        url = url.concat(handle);
        if(from!=-1){
            url = url.concat("&from=");
            url = url.concat(String.valueOf(from));
        }
        if(count!=-1){
            url = url.concat("&count=");
            url = url.concat(String.valueOf(count));
        }
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

    public static ArrayList<User> getUserRatedList(Boolean activeOnly) throws IOException, FailedResponseException, JSONException {
        ArrayList<User> users = new ArrayList<User>();
        String url = baseUrl;
        url =url.concat("ratedList?activeOnly=");
        url =url.concat(String.valueOf(activeOnly));
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jUsers = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jUsers.length() ; i++){
                JSONObject jobj =jUsers.getJSONObject(i);
                users.add(Request.getUser(jobj));
            }
        }
        return users;
    }

    public static ArrayList<User> getUserInfo(ArrayList<String> handles) throws IOException, FailedResponseException,
            JSONException {
        ArrayList<User> users = new ArrayList<User>();
        String url = baseUrl;
        if(handles==null){
            throw new FailedResponseException();
        }
        url =url.concat("info?handles=");
        for(int i=0;i<handles.size();i++){
            url = url.concat(handles.get(i));
            if(i!=handles.size()-1)
                url = url.concat(";");
        }
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jUsers = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jUsers.length() ; i++){
                JSONObject jobj =jUsers.getJSONObject(i);
                users.add(Request.getUser(jobj));
            }
        }
        return users;
    }

}
