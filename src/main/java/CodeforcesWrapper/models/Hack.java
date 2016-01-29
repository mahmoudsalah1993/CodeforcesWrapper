package CodeforcesWrapper.models;


/**
 * Created by Mahmoud on 26/01/2016.
 */
public class Hack {

    private int id;
    private int creationTimeSeconds;
    private Party hacker;
    private Party defender;
    public enum HackVerdict{
        HACK_SUCCESSFUL, HACK_UNSUCCESSFUL, INVALID_INPUT, GENERATOR_INCOMPILABLE, GENERATOR_CRASHED, IGNORED, TESTING,
        OTHER
    }
    private HackVerdict verdict;
    private Problem problem;
    private String test;
    private JudgeProtocol judgeProtocol;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreationTimeSeconds() {
        return creationTimeSeconds;
    }

    public void setCreationTimeSeconds(int creationTimeSeconds) {
        this.creationTimeSeconds = creationTimeSeconds;
    }

    public Party getHacker() {
        return hacker;
    }

    public void setHacker(Party hacker) {
        this.hacker = hacker;
    }

    public HackVerdict getVerdict() {
        return verdict;
    }

    public void setVerdict(HackVerdict verdict) {
        this.verdict = verdict;
    }

    public Party getDefender() {
        return defender;
    }

    public void setDefender(Party defender) {
        this.defender = defender;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public JudgeProtocol getJudgeProtocol() {
        return judgeProtocol;
    }

    public void setJudgeProtocol(JudgeProtocol judgeProtocol) {
        this.judgeProtocol = judgeProtocol;
    }
}
