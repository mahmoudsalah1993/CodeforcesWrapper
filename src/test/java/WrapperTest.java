import CodeforcesWrapper.Response.Exceptions.ApiLimitException;
import CodeforcesWrapper.Response.Exceptions.FailedResponseException;
import CodeforcesWrapper.Response.Exceptions.InvalidArgumentException;
import CodeforcesWrapper.Response.Exceptions.RequiredArgumentNullException;
import CodeforcesWrapper.Wrapper;
import CodeforcesWrapper.models.Hack;
import junit.framework.*;
//import org.junit.*;
import org.codehaus.jettison.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Mahmoud on 29/01/2016.
 */
public class WrapperTest {

   Wrapper wrapper;

    @Before
    public void initialize(){
        wrapper = new Wrapper(null,null);
    }

    @Test
    public void testGetContestHacks() throws IOException, FailedResponseException, ExecutionException, InterruptedException, JSONException {
        assertEquals(Hack.HackVerdict.HACK_SUCCESSFUL, wrapper.getContestHacks(374, true).get(0).getVerdict());
    }

    @Test
    public void testGetContestList() throws IOException, FailedResponseException, JSONException {
        assertEquals(100001, wrapper.getContestList(true, true).get(0).getId());
    }

    @Test
    public void testGetContestRatingChanges() throws IOException, FailedResponseException, JSONException {
        assertEquals(1749, wrapper.getContestRatingChanges(375).get(0).getOldRating());
    }

    @Test
    public void testGetContestStandings() throws IOException, FailedResponseException, JSONException, ApiLimitException {
        assertEquals("A", wrapper.getContestStandings(374, 1, 5, null, -1, true).getProblems().get(0).getIndex());
    }

    @Test
    public void testGetContestSubmissions() throws IOException, FailedResponseException, JSONException {
        assertEquals(15739572, wrapper.getContestSubmissions(374, null, 1, 10).get(0).getId());
    }

    @Test
    public void testGetPRoblemSetProblems() throws IOException, FailedResponseException, JSONException {
        ArrayList<String> tags = new ArrayList<String>();
        tags.add("implementation");
        assertEquals(621, wrapper.getProblems(tags).getProblems().get(0).getContestId());
    }

    @Test
    public void testGetProblemSetSubmissions() throws FailedResponseException, InvalidArgumentException,
            ApiLimitException, IOException, JSONException {
        Exception ex = null;
        try{
            wrapper.getRecentSubmissions(10).get(0).getId();
        }catch (Exception e){
            ex=e;
        }
        assertNull(ex);
    }

    @Test
    public void testGetUserInfo() throws IOException, FailedResponseException, JSONException,
            ApiLimitException, RequiredArgumentNullException {
        ArrayList<String> users = new ArrayList<String>();
        users.add("DmitriyH");
        users.add("Fefer_Ivan");
        assertEquals(104, wrapper.getUserInfo(users).get(1).getContribution());
    }

    @Test
    public void testGetUserRatedList() throws IOException, FailedResponseException, JSONException {
        assertEquals(160, wrapper.getRatedUsers(true).get(0).getContribution());
    }

    @Test
    public void testGetUserRatings() throws IOException, FailedResponseException, JSONException,
            RequiredArgumentNullException {
        assertEquals(30, wrapper.getUserRating("Fefer_Ivan").get(0).getRank());
    }

    @Test
    public void testGetUserStatus() throws IOException, FailedResponseException, JSONException,
            RequiredArgumentNullException {
        assertEquals(13004518, wrapper.getUserSubmissions("Fefer_Ivan",1,10).get(0).getId());
    }

}
