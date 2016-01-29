package CodeforcesWrapper.Response;

/**
 * Created by Mahmoud on 28/01/2016.
 */
public abstract class SuccessfulResponse {

    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
