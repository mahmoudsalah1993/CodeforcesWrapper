package CodeforcesWrapper.requests;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

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

    public static String getSha512(String hashString) {
        Random ran = new Random();
        String hash = "";
        for(int i = 0 ; i < 6 ; i++) {
            hash += (char) (ran.nextInt(25) + 97);
        }
        hash = hash.concat(hashString);
        return DigestUtils.sha512Hex(hash);
    }
}
