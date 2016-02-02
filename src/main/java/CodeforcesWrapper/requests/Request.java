package CodeforcesWrapper.requests;

import CodeforcesWrapper.Response.ContestStandingsSuccessfulResponse;
import CodeforcesWrapper.Response.ProblemSetProblemsSuccessfulResponse;
import CodeforcesWrapper.models.*;
import jodd.json.JsonParser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.codehaus.jettison.json.JSONObject;

import java.io.IOException;
import java.util.Random;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public class Request {

    public static byte[] makeRequest(String url) throws IOException {
        HttpClient client = new HttpClient();
        GetMethod method = new GetMethod(url);
        client.executeMethod(method);
        byte[] response = method.getResponseBody();
        method.releaseConnection();
        return response;
    }

    public static String getApiSig(String hashString) {
        Random ran = new Random();
        char c;
        String hash = "";
        String apiSig = "";
        for(int i = 0 ; i < 6 ; i++) {
            c = (char) (ran.nextInt(25) + 97);
            hash += c;
            apiSig += c;
        }
        hash = hash.concat(hashString);
        hash = DigestUtils.sha512Hex(hash);
        apiSig = apiSig.concat(hash);
        return apiSig;
    }

    public static Hack getHack(JSONObject obj) throws IOException {
        JsonParser parser = new JsonParser();
        Hack hack = parser.parse(obj.toString(),Hack.class);
        return hack;
    }

    public static Contest getContest(JSONObject obj) throws IOException {
        JsonParser parser = new JsonParser();
        Contest contest = parser.parse(obj.toString(),Contest.class);
        return contest;
    }

    public static RatingChange getRatingChange(JSONObject jobj) {
        JsonParser parser = new JsonParser();
        RatingChange ratingChange = parser.parse(jobj.toString(),RatingChange.class);
        return ratingChange;
    }

    public static ContestStandingsSuccessfulResponse getStandings(JSONObject jobj) {
        JsonParser parser = new JsonParser();
        ContestStandingsSuccessfulResponse standings = parser.parse(jobj.toString(),ContestStandingsSuccessfulResponse.class);
        return standings;
    }

    public static Submission getSubmission(JSONObject jobj) {
        JsonParser parser = new JsonParser();
        Submission submission = parser.parse(jobj.toString(),Submission.class);
        return submission;
    }

    public static ProblemSetProblemsSuccessfulResponse getProblemsResponse(JSONObject jobj) {
        JsonParser parser = new JsonParser();
        ProblemSetProblemsSuccessfulResponse response = parser.parse(jobj.toString(), ProblemSetProblemsSuccessfulResponse.class);
        return response;
    }

    public static User getUser(JSONObject jobj){
        JsonParser parser = new JsonParser();
        User user = parser.parse(jobj.toString(), User.class);
        return user;
    }
}
