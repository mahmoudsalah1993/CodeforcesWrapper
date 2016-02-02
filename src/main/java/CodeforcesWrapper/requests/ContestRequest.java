package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.*;
import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.models.Contest;
import CodeforcesWrapper.models.Hack;
import CodeforcesWrapper.models.RatingChange;
import CodeforcesWrapper.models.Submission;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

/**
 * Created by Mahmoud on 29/01/2016.
 */
public class ContestRequest {

    private static String baseUrl = "http://www.codeforces.com/api/contest.";

    public static ArrayList<RatingChange> getContestRatingChanges(int id) throws IOException, FailedResponseException, JSONException {
        ArrayList<RatingChange> ratingChanges = new ArrayList<RatingChange>();
        String url = baseUrl;
        url =url.concat("ratingChanges?contestId=");
        url =url.concat(String.valueOf(id));
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

    public static ContestStandingsSuccessfulResponse getContestStandings
            (int id,int from, int count, ArrayList<String> handles,int room,Boolean showUnofficial) throws IOException, FailedResponseException, JSONException {
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
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONObject standings = jsonObject.getJSONObject("result");
            return Request.getStandings(standings);
        }
    }

    public static ArrayList<Submission> getContestSubmissions(int id,String handle,int from,int count) throws IOException, FailedResponseException, JSONException {
        ArrayList<Submission> submissions = new ArrayList<Submission>();
        String url = baseUrl;
        url = url.concat("status?contestId=");
        url = url.concat(String.valueOf(id));
        if(handle!=null){
            url = url.concat("&handle=");
            url =url.concat(handle);
        }
        if(from!=-1){
            url = url.concat("&from=");
            url =url.concat(String.valueOf(from));
        }
        if(count!=-1){
            url = url.concat("&count=");
            url =url.concat(String.valueOf(count));
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

    //Anonymous call for Hacks
    public static ArrayList<Hack> getContestHacks(int id)
            throws FailedResponseException, IOException, InterruptedException, ExecutionException, JSONException {
        ArrayList<Hack> hacks = new ArrayList<Hack>();
        String url = baseUrl;
        url = url.concat("hacks?contestId=");
        url = url.concat(String.valueOf(id));
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jhacks = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jhacks.length() ; i++){
                JSONObject jobj =jhacks.getJSONObject(i);
                hacks.add(Request.getHack(jobj));
            }
        }
        return hacks;
    }

    //Non anonymous call for hacks
    public static ArrayList<Hack> getContestHacks(int id,String key,String secret)
            throws FailedResponseException, IOException, JSONException {
        if(key==null || secret == null)throw new FailedResponseException();
        ArrayList<Hack> hacks = new ArrayList<Hack>();
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
        String apiSig = Request.getApiSig(hashString);
        url = url.concat(apiSig);
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jhacks = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jhacks.length() ; i++){
                JSONObject jobj =jhacks.getJSONObject(i);
                hacks.add(Request.getHack(jobj));
            }
        }
        return hacks;
    }

    //Anonymous call for contest list
    public static ArrayList<Contest> getContestList(Boolean gym)
            throws IOException, FailedResponseException, JSONException {
        ArrayList<Contest> contests = new ArrayList<Contest>();
        String url = baseUrl;
        url = url.concat("list?gym=");
        url = url.concat(String.valueOf(gym));
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jcontests = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jcontests.length() ; i++){
                JSONObject jobj =jcontests.getJSONObject(i);
                contests.add(Request.getContest(jobj));
            }
        }
        return contests;
    }

    //Defined user call for contest list
    public static ArrayList<Contest> getContestList(Boolean gym, String key , String secret)
            throws IOException, FailedResponseException, JSONException {
        if(key==null || secret == null)throw new FailedResponseException();
        ArrayList<Contest> contests = new ArrayList<Contest>();
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
        String apiSig = Request.getApiSig(hashString);
        url = url.concat(apiSig);
        byte[] response = Request.makeRequest(url);
        String stringResponse = new String(response);
        JSONObject jsonObject = new JSONObject(stringResponse);
        if(!(jsonObject.get("status").toString().equals("OK"))){
            throw new FailedResponseException();
        }
        else{
            JSONArray jcontests = jsonObject.getJSONArray("result");
            for(int i=0 ; i < jcontests.length() ; i++){
                JSONObject jobj =jcontests.getJSONObject(i);
                contests.add(Request.getContest(jobj));
            }
        }
        return contests;
    }

}
