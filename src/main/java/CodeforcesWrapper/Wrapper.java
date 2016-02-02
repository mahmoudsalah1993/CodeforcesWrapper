package CodeforcesWrapper;

import CodeforcesWrapper.Response.ContestStandingsSuccessfulResponse;
import CodeforcesWrapper.Response.Exceptions.ApiLimitException;
import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.Response.Exceptions.InvalidArgumentException;
import CodeforcesWrapper.Response.Exceptions.RequiredArgumentNullException;
import CodeforcesWrapper.Response.ProblemSetProblemsSuccessfulResponse;
import CodeforcesWrapper.models.*;
import CodeforcesWrapper.requests.ContestRequest;
import CodeforcesWrapper.requests.ProblemSetRequest;
import CodeforcesWrapper.requests.UserRequest;
import org.codehaus.jettison.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


/**
 * Created by Mahmoud on 27/01/2016.
 */
public class Wrapper {

    //All methods return JSONException in case the json response failed to parse

    //the strings forming each user's codeforces apiKey
    private String key;
    private String secret;

    //Api limits for handles requested and submissions count returned.
    private static final int handlesCountLimit = 10000;
    private static final int submissionsCountLimit = 1000;

    //wrapper is initialized with key and secret used to access private data if needed.
    public Wrapper(String key , String secret){
        this.key = key;
        this.secret = secret;
    }

    /*get rating history of a user , throws IOException in case the request failed to send
    , returns FailedResponseException if response from api had status FAIL,throws RequiredArgumentNullException if argument is null*/
    public ArrayList<RatingChange> getUserRating(String handle) throws RequiredArgumentNullException,
            IOException, FailedResponseException, JSONException {
        if(handle==null)throw new RequiredArgumentNullException();
        return UserRequest.getUserRating(handle);
    }

    /*get user's history of submissions, the user handle is required and if handle is null throws a RequiredArgumentNullException
    you can specify a 1-based index called from to start submissions with, and specify number of returned submissions in
    count. If from and count are not required they should be passed as negative values.
    Throws IOException in case the request failed to send, returns FailedResponseException if response from api had status FAIL
    */
    public ArrayList<Submission> getUserSubmissions(String handle, int from , int count) throws RequiredArgumentNullException,
            IOException, FailedResponseException, JSONException {
        if(handle==null)throw new RequiredArgumentNullException();
        if(from<1)from=-1;
        if(count<1)count=-1;
        return UserRequest.getUserStatus(handle, from, count);
    }

    /*get a list of users who participated in at least one rated contest, Boolean argument if true returns only users
    who participated in rated contest during the last month. otherwise all are returned. Throws IOException in case
     the request failed to send, returns FailedResponseException if response from api had status FAIL
     */
    public ArrayList<User> getRatedUsers(Boolean activeOnly) throws IOException, FailedResponseException, JSONException {
        return UserRequest.getUserRatedList(activeOnly);
    }

    /*get a list of one or more users, argument passed is an arraylist<String> of handles, the handles count should not
    exceed the api limit set in handlesCountLimit otherwise throws an ApiLimitException.Throws IOException in case
     the request failed to send, returns FailedResponseException if response from api had status FAIL.
    */
    public ArrayList<User> getUserInfo(ArrayList<String> handles) throws RequiredArgumentNullException,
            IOException, FailedResponseException, ApiLimitException, JSONException {
        if(handles==null)throw new RequiredArgumentNullException();
        if(handles.size()>handlesCountLimit)throw new ApiLimitException();
        return UserRequest.getUserInfo(handles);
    }

    /*get the list of most recent submissions , count argument is the number of submissions to return and
     is required. count can't exceed the value set in submissionsCountLimit otherwise throws ApiLimitException.
     if count is less than 1 throws InvalidArgumentException, throws IOException in case
     the request failed to send, returns FailedResponseException if response from api had status FAIL.
    */
    public ArrayList<Submission> getRecentSubmissions(int count) throws InvalidArgumentException,
            IOException, FailedResponseException, ApiLimitException, JSONException {
        if(count<=0) throw new InvalidArgumentException();
        if(count> submissionsCountLimit) throw new ApiLimitException();
        return ProblemSetRequest.getProblemSetSubmmissions(count);
    }

    /*returns an object ProblemSetProblemsSuccessfulResponse containing a list of Problem objects and another list of
    ProblemStatistics objects. argument tags filters only problems with such tags, if not set returns all problems.
    Throws IOException in case the request failed to send, returns FailedResponseException if response from api had status FAIL.
     */
    public ProblemSetProblemsSuccessfulResponse getProblems(ArrayList<String> tags) throws IOException,
            FailedResponseException, JSONException {
        if(tags==null){
            tags = new ArrayList<String>();
        }
        return ProblemSetRequest.getProblemSetProblems(tags);
    }

    /*returns a list of Submission objects from the specified contest in required argument contestId,
     The rest of argument are optional and specify which user to get submissions , the 1-based starting address of
      submissions, and count of submissions returned respectively. if not desired to specify , handle should be null,
      from and count less than 1. Throws IOException in case the request failed to send, returns
      FailedResponseException if response from api had status FAIL.
     */
    public ArrayList<Submission> getContestSubmissions(int contestId , String handle,int from , int count) throws IOException,
            FailedResponseException, JSONException {
        if(from <=0)from = -1;
        if(count<=0)count = -1;
        return ContestRequest.getContestSubmissions(contestId, handle, from, count);
    }

    /*returns a ContestStandingsSuccessfulResponse object containing "contest, "problems" and "rows". Field "contest"
    contains a Contest object. Field "problems" contains a list of Problem objects. Field "rows" contains a list of
    RanklistRow objects. only contestId is required, from,count and room if not required should be set less than or
    equal 0 , while handles should be null if not desired. handles count shouldn't exceed handlesCountLimit otherwise
    an ApiLimitException is thrown Throws IOException in case the request failed to send, returns FailedResponseException
     if response from api had status FAIL.
     */
    public ContestStandingsSuccessfulResponse getContestStandings(int contestId, int from, int count, ArrayList<String>
            handles , int room , Boolean showUnofficial) throws IOException, FailedResponseException, ApiLimitException, JSONException {
        if(from <=0)from = -1;
        if(count<=0)count = -1;
        if(room<=0)room = -1;
        if(handles!=null && handles.size()> handlesCountLimit)throw new ApiLimitException();
        return ContestRequest.getContestStandings(contestId, from, count, handles, room, showUnofficial);
    }

    /*returns a list of RatingChange objects, which are the rating changes after contest specified in required argument
     contestId. Throws IOException in case the request failed to send, returns FailedResponseException if response from
      api had status FAIL.*/
    public ArrayList<RatingChange> getContestRatingChanges(int contestId) throws IOException, FailedResponseException, JSONException {
        return ContestRequest.getContestRatingChanges(contestId);
    }

    /*Returns a list of Contest objects. if gym is true, gym contest are returned otherwise regular contests are
    returned If this method is called not anonymously, then all available contests for a calling user will be returned
    too, including mashups and private gyms. Throws IOException in case the request failed to send, returns
    FailedResponseException if response from api had status FAIL.*/
    public ArrayList<Contest> getContestList(Boolean gym , Boolean anonymous) throws IOException, FailedResponseException, JSONException {
        if(anonymous)
            return ContestRequest.getContestList(gym);
        else return ContestRequest.getContestList(gym, key, secret);
    }

    /*Returns list of hacks in the specified contests. Full information about hacks is available only after some time
    after the contest end. During the contest user can see only own hacks if call is sent non-anonymously.Throws
    IOException in case the request failed to send, returns FailedResponseException if response from api had status FAIL.
    */
    public ArrayList<Hack> getContestHacks(int contestId, Boolean anonymous) throws IOException, FailedResponseException, InterruptedException, ExecutionException, JSONException {
        if(anonymous) {

            ArrayList<Hack> temp = new ArrayList<Hack>(10000);
            temp = ContestRequest.getContestHacks(contestId);
            return temp;
        }
            else return ContestRequest.getContestHacks(contestId , key , secret);
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }
}
