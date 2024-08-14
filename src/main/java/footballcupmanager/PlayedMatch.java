package footballcupmanager;

import java.util.Objects;

public class PlayedMatch {
    private int matchId;
    private int tournamentId;
    private String team1;
    private String team2;
    private String winner;

    public PlayedMatch(int matchId, int tournamentId, String team1, String team2, String winner) {
        this.matchId = matchId;
        this.tournamentId = tournamentId;
        this.team1 = team1;
        this.team2 = team2;
        this.winner = winner;
    }

    public int getMatchId() {
        return matchId;
    }

    public int getTournamentId() {
        return tournamentId;
    }

    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public String getWinner() {
        return winner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayedMatch that = (PlayedMatch) o;
        return matchId == that.matchId && tournamentId == that.tournamentId && Objects.equals(team1, that.team1) && Objects.equals(team2, that.team2) && Objects.equals(winner, that.winner);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
