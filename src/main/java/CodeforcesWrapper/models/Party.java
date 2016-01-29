package CodeforcesWrapper.models;

import java.util.ArrayList;

/**
 * Created by Mahmoud on 26/01/2016.
 */
public class Party {

    private int contestId;
    private ArrayList<Member> members;
    public enum ParticipantType {
        CONTESTANT, PRACTICE, VIRTUAL, MANAGER, OUT_OF_COMPETITION
    }
    private ParticipantType type;
    private int teamId;
    private String teamName;
    private Boolean ghost;
    private int room;
    private int startTimeSeconds;

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }

    public ArrayList<Member> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }

    public ParticipantType getType() {
        return type;
    }

    public void setType(ParticipantType type) {
        this.type = type;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Boolean getGhost() {
        return ghost;
    }

    public void setGhost(Boolean ghost) {
        this.ghost = ghost;
    }

    public int getRoom() {
        return room;
    }

    public void setRoom(int room) {
        this.room = room;
    }

    public int getStartTimeSeconds() {
        return startTimeSeconds;
    }

    public void setStartTimeSeconds(int startTimeSeconds) {
        this.startTimeSeconds = startTimeSeconds;
    }
}
