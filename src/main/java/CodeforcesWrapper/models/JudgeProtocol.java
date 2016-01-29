package CodeforcesWrapper.models;

/**
 * Created by Mahmoud on 26/01/2016.
 */
public class JudgeProtocol {

    private Boolean manual;
    private String protocol;
    private String verdict;

    public Boolean getManual() {
        return manual;
    }

    public void setManual(Boolean manual) {
        this.manual = manual;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}
