package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.Response.UserListSuccessfulResponse;
import CodeforcesWrapper.Response.UserRatingSuccessfulResponse;
import CodeforcesWrapper.Response.SubmissionListSuccessfulResponse;
import CodeforcesWrapper.models.RatingChange;
import CodeforcesWrapper.models.Submission;
import CodeforcesWrapper.models.User;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class UserRequest {

    private static String baseUrl = "http://www.codeforces.com/api/user.";

    public static ArrayList<RatingChange> getUserRating(String handle) throws IOException, FailedResponseException {
        String url = baseUrl;
        url =url.concat("rating?handle=");
        url =url.concat(handle);
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            UserRatingSuccessfulResponse success = gson.fromJson(reader,UserRatingSuccessfulResponse.class);
            return success.getRatingChanges();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

    public static ArrayList<Submission> getUserStatus(String handle,int from,int count) throws IOException, FailedResponseException {
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

    public static ArrayList<User> getUserRatedList(Boolean activeOnly) throws IOException, FailedResponseException {
        String url = baseUrl;
        url =url.concat("ratedList?activeOnly=");
        url =url.concat(String.valueOf(activeOnly));
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            UserListSuccessfulResponse success = gson.fromJson(reader,UserListSuccessfulResponse.class);
            return success.getUsers();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

    public static ArrayList<User> getUserInfo(ArrayList<String> handles) throws IOException, FailedResponseException {
        String url = baseUrl;
        url =url.concat("info?handles=");
        for(int i=0;i<handles.size();i++){
            url = url.concat(handles.get(i));
            if(i!=handles.size()-1)
                url = url.concat(";");
        }
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            UserListSuccessfulResponse success = gson.fromJson(reader,UserListSuccessfulResponse.class);
            return success.getUsers();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

}
