package footballcupmanager;

public class Team {
    private int teamId;
    private String teamName;
    private String teamCountry;

    public Team(int teamId, String teamName, String teamCountry) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.teamCountry = teamCountry;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getTeamCountry() {
        return teamCountry;
    }
}
