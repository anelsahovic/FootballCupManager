package footballcupmanager;

public class Tournament {
    private int tournamentId;
    private String name;
    private int numOfTeams;


    public Tournament(int tournamentId, String name, int numOfTeams) {
        this.tournamentId = tournamentId;
        this.name = name;
        this.numOfTeams = numOfTeams;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public String getName() {
        return name;
    }

    public int getNumOfTeams() {
        return numOfTeams;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "tournamentId=" + tournamentId +
                ", name='" + name + '\'' +
                ", numOfTeams=" + numOfTeams +
                '}';
    }
}
