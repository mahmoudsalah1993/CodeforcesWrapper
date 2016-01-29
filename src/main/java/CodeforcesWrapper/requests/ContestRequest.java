package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.*;
import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.models.Contest;
import CodeforcesWrapper.models.Hack;
import CodeforcesWrapper.models.RatingChange;
import CodeforcesWrapper.models.Submission;
import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by Mahmoud on 29/01/2016.
 */
public class ContestRequest {

    private static String baseUrl = "http://www.codeforces.com/api/contest.";

    public static ArrayList<RatingChange> getContestRatingChanges(int id) throws IOException, FailedResponseException {
        String url = baseUrl;
        url =url.concat("ratingChanges?contestId=");
        url =url.concat(String.valueOf(id));
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

    public static ContestStandingsSuccessfulResponse getContestStandings
            (int id,int from, int count, ArrayList<String> handles,int room,Boolean showUnofficial) throws IOException, FailedResponseException {
        String url = baseUrl;
        url = url.concat("standings?contestId=");
        url = url.concat(String.valueOf(id));
        if(from!=-1){
            url = url.concat("&from=");
            url = url.concat(String.valueOf(from));
        }
        if(count!=-1){
            url = url.concat("&count=");
            url = url.concat(String.valueOf(count));
        }
        if(handles!=null){
            url = url.concat("&handles=");
            for(int i=0;i<handles.size();i++){
                url = url.concat(handles.get(i));
                if(i!=handles.size()-1)
                    url = url.concat(";");
            }
        }
        if(room!=-1){
            url = url.concat("&room=");
            url = url.concat(String.valueOf(room));
        }
        url = url.concat("&showUnofficial=");
        url = url.concat(String.valueOf(showUnofficial));
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            ContestStandingsSuccessfulResponse success = gson.fromJson(reader,ContestStandingsSuccessfulResponse.class);
            return success;
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

    public static ArrayList<Submission> getContestSubmissions(int id,String handle,int from,int count) throws IOException, FailedResponseException {
        String url = baseUrl;
        url = url.concat("status?contestId=");
        url = url.concat(String.valueOf(id));
        if(handle!=null){
            url = url.concat("&handle=");
            url.concat(handle);
        }
        if(from!=-1){
            url = url.concat("&from=");
            url.concat(String.valueOf(from));
        }
        if(count!=-1){
            url = url.concat("&count=");
            url.concat(String.valueOf(count));
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

    //Anonymous call for Hacks
    public static ArrayList<Hack> getContestHacks(int id)
            throws FailedResponseException, IOException {
        String url = baseUrl;
        url = url.concat("hacks?contestId=");
        url = url.concat(String.valueOf(id));
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            ContestHacksSuccessfulResponse success = gson.fromJson(reader,ContestHacksSuccessfulResponse.class);
            return success.getHacks();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

    //Non anonymous call for hacks
    public static ArrayList<Hack> getContestHacks(int id,String key,String secret)
            throws FailedResponseException, IOException {
        String url = baseUrl;
        url = url.concat("hacks?contestId=");
        url = url.concat(String.valueOf(id));
        url = url.concat("&apiKey=");
        url = url.concat(key);
        long time = System.currentTimeMillis()/1000;
        url = url.concat("&time=");
        url = url.concat(String.valueOf(time));
        url = url.concat("&apiSig=");
        String hashString = "/contest.hacks?apiKey=";
        hashString = hashString.concat(String.valueOf(key));
        hashString = hashString.concat("&contestId=");
        hashString = hashString.concat(String.valueOf(id));
        hashString = hashString.concat("&time=");
        hashString = hashString.concat(String.valueOf(time));
        hashString = hashString.concat("#");
        hashString = hashString.concat(secret);
        String apiSig = Request.getSha512(hashString);
        url = url.concat(apiSig);
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            ContestHacksSuccessfulResponse success = gson.fromJson(reader,ContestHacksSuccessfulResponse.class);
            return success.getHacks();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

    //Anonymous call for contest list
    public static ArrayList<Contest> getContestList(Boolean gym)
            throws IOException, FailedResponseException {
        String url = baseUrl;
        url = url.concat("list?gym=");
        url = url.concat(String.valueOf(gym));
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            ContestListSuccessfulResponse success = gson.fromJson(reader,ContestListSuccessfulResponse.class);
            return success.getContests();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }

    //Defined user call for contest list
    public static ArrayList<Contest> getContestList(Boolean gym, String key , String secret)
            throws IOException, FailedResponseException {
        String url = baseUrl;
        url = url.concat("list?gym=");
        url = url.concat(String.valueOf(gym));
        url = url.concat("&apiKey=");
        url = url.concat(key);
        long time = System.currentTimeMillis()/1000;
        url = url.concat("&time=");
        url = url.concat(String.valueOf(time));
        url = url.concat("&apiSig=");
        String hashString = "/contest.list?apiKey=";
        hashString = hashString.concat(String.valueOf(key));
        hashString = hashString.concat("&gym=");
        hashString = hashString.concat(String.valueOf(gym));
        hashString = hashString.concat("&time=");
        hashString = hashString.concat(String.valueOf(time));
        hashString = hashString.concat("#");
        hashString = hashString.concat(secret);
        String apiSig = Request.getSha512(hashString);
        url = url.concat(apiSig);
        byte[] response = Request.makeRequest(url);
        InputStream stream = new ByteArrayInputStream(response);
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        Gson gson = new Gson();
        try{
            ContestListSuccessfulResponse success = gson.fromJson(reader,ContestListSuccessfulResponse.class);
            return success.getContests();
        }
        catch(java.lang.IllegalStateException e){
            throw new FailedResponseException();
        }
    }
}
