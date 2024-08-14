package footballcupmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class TournamentController implements Initializable {
    // FXML declarations
    @FXML
    private ChoiceBox<Integer> noOfTeams;

    @FXML
    private Label winnerLabel1, winnerLabel2, winnerLabel3, winnerLabel4, winnerLabel5, winnerLabel6,
            winnerLabel7, winnerLabel8, winnerLabel9, winnerLabel10, winnerLabel11, winnerLabel12,
            winnerLabel13, winnerLabel14, winnerLabel15, winnerLabel16;

    @FXML
    private Label winnerMessage1, winnerMessage2, winnerMessage3, winnerMessage4, winnerMessage5, winnerMessage6,
            winnerMessage7, winnerMessage8, winnerMessage9, winnerMessage10, winnerMessage11, winnerMessage12,
            winnerMessage13, winnerMessage14, winnerMessage15, winnerMessage16;

    @FXML
    private Label teamLabel1, teamLabel2, teamLabel3, teamLabel4, teamLabel5, teamLabel6, teamLabel7, teamLabel8,
            teamLabel9, teamLabel10, teamLabel11, teamLabel12, teamLabel13, teamLabel14, teamLabel15, teamLabel16,
            teamLabel17, teamLabel18, teamLabel19, teamLabel20, teamLabel21, teamLabel22, teamLabel23, teamLabel24,
            teamLabel25, teamLabel26, teamLabel27, teamLabel28, teamLabel29, teamLabel30, teamLabel31, teamLabel32;

    @FXML
    private Label numberOfTeams;
    @FXML
    private TextField nameOfTournamentTxtField;

    @FXML
    private Button goTo16Btn, goToQuarterBtn, goToSemiFinalsBtn, goToFinalBtn, playMatchBtn;

    // Variables
    private List<Label> winnerMessage;
    private List<Label> winnerLabel;
    private List<Label> teamLabels;
    private List<Team> winners = new ArrayList<>();

    // Main controller reference
    private final MainController mainController = new MainController();

    // Initialize ChoiceBox with number of teams options
    public void populateNoTeamsChoicebox() {
        noOfTeams.getItems().addAll(32, 16, 8, 4, 2);
        System.out.println("ChoiceBox populated: " + noOfTeams.getItems());
    }

    // Handle Back button click to return to the Home screen
    public void TournamentBackBtnClick(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/HomeScreen.fxml");
    }

    // Handle Start Tournament button click
    public void StartTheTournament(ActionEvent event) throws IOException {
        if (noOfTeams.getValue() == null) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields!");
            alert.setContentText("Please choose number of teams for the tournament!");
            alert.showAndWait();
        }

        if (!nameOfTournamentTxtField.getText().isEmpty()) {
            saveNewTournament();
            switch (noOfTeams.getValue()) {
                case 32 -> mainController.switchToScene(event, "/FxmlFiles/RoundOf32.fxml");
                case 16 -> mainController.switchToScene(event, "/FxmlFiles/RoundOf16.fxml");
                case 8 -> mainController.switchToScene(event, "/FxmlFiles/QuarterFinals.fxml");
                case 4 -> mainController.switchToScene(event, "/FxmlFiles/SemiFinals.fxml");
                case 2 -> mainController.switchToScene(event, "/FxmlFiles/Final.fxml");
                default -> System.out.println("Choose the right type of tournament!");
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields!");
            alert.setContentText("Please enter name of the tournament!");
            alert.showAndWait();
        }


    }

    // Show winner teams and messages
    public void showWinnerTeams() {
        for (int i = 0; i < winners.size(); i++) {
            if (i < winnerLabel.size()) {
                winnerLabel.get(i).setVisible(true);
                winnerLabel.get(i).setText(winners.get(i).getTeamName());
            }
        }
    }

    public void showWinnerMessages() {
        for (Label label : winnerMessage) {
            label.setVisible(true);
        }
    }

    // Play matches based on the round
    public void play32Matches() {
        playMatches();
        goTo16Btn.setDisable(false);
    }

    public void play16Matches() {
        playMatches();
        goToQuarterBtn.setDisable(false);
    }

    public void playQuaterFinals() {
        playMatches();
        goToSemiFinalsBtn.setDisable(false);
    }

    public void playSemiFinals() {
        playMatches();
        goToFinalBtn.setDisable(false);
    }

    public void playFinal() {
     //   playMatches();
        showFinalWinner();
    }

    // Play and simulate matches, show winners and messages
    private void playMatches() {
        showWinnerMessages();
        showWinnerTeams();
        playMatchBtn.setDisable(true);
    }


    private Stage stage;
    private Scene scene;
    private Parent root;
    // Display final winner alert
    private void showFinalWinner() {
        winners = loadWinners(Integer.parseInt(numberOfTeams.getText()));
        Team winnerTeam = simulateMatch(winners.get(0), winners.get(1));
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setHeaderText("WINNER : " + winnerTeam.getTeamName());
        alert.setContentText(winnerTeam.getTeamName() + " won the tournament");

        // Set action to be performed when the alert is closed
        alert.setOnHidden(e -> {
            winners = null;

            try {
                root = FXMLLoader.load(getClass().getResource("/FxmlFiles/HomeScreen.fxml"));
                // Use alert's current window (stage) to navigate
                stage = (Stage) playMatchBtn.getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        alert.showAndWait();
    }


    // Navigation methods to different rounds
    public void goTo16Round(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/RoundOf16.fxml");
    }

    public void goToQuarterFinals(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/QuarterFinals.fxml");
    }

    public void goToSemiFinals(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/SemiFinals.fxml");
    }

    public void goToFinal(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/Final.fxml");
    }

    // Simulate a match between two teams
    private Team simulateMatch(Team team1, Team team2) {
        return Math.random() > 0.5 ? team1 : team2;
    }

    // Load teams from the database
    public List<Team> loadTeamsFromDatabase() {
        List<Team> teams = new ArrayList<>();
        List<Team> randomTeams = new ArrayList<>();
        Random random = new Random();

        String sqlQuery = "SELECT * FROM team;";  // Select all teams initially

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            // Load all teams into the list
            while (result.next()) {
                Team team = new Team(result.getInt("team_id"), result.getString("team_name"), result.getString("team_country"));
                teams.add(team);
            }

            // Ensure there are enough teams to select from
            int totalTeams = Integer.parseInt(numberOfTeams.getText());
            if (teams.size() < totalTeams) {
                System.out.println("Error: Not enough teams in the database.");
                return randomTeams;
            }

            // Randomly select teams
            while (randomTeams.size() < totalTeams) {
                int randomIndex = random.nextInt(teams.size());
                Team selectedTeam = teams.get(randomIndex);
                if (!randomTeams.contains(selectedTeam)) {
                    randomTeams.add(selectedTeam);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return randomTeams;
    }

    public List<Team> loadWinners(int numOfTeams) {
        List<Team> teams = new ArrayList<>();
        String sqlQuery = "SELECT t.team_id, t.team_name, t.team_country " +
                "FROM played_match pm " +
                "JOIN team t ON pm.winner_id = t.team_id " +
                "WHERE pm.tournament_id = ? " +
                "ORDER BY pm.match_id DESC LIMIT ?;";
        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {
            prepare.setInt(1, getTournamentID());
            prepare.setInt(2, numOfTeams);
            ResultSet result = prepare.executeQuery();
            while (result.next()) {
                Team team = new Team(result.getInt("team_id"), result.getString("team_name"), result.getString("team_country"));
                teams.add(team);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return teams;
    }

    // Set the team labels with team names
    public void setTeams(List<Team> teams) {
        if (teams.size() != Integer.parseInt(numberOfTeams.getText())) {
            System.out.println("Error: The number of teams provided does not match the number of teams playing.");
            return;
        }
        for (int i = 0; i < teams.size(); i++) {
            Label label = teamLabels.get(i);
            if (label != null) {
                label.setText(teams.get(i).getTeamName());
                label.setVisible(true); // Show the label
            } else {
                System.out.println();
            }
        }
    }

    // Show the teams loaded from the database
    public void showTeams() {
        List<Team> teams;
        winners.clear();
        winners = loadWinners(Integer.parseInt(numberOfTeams.getText()));

        if (winners.isEmpty()) {
            teams = loadTeamsFromDatabase(); // Load the 32 teams from the database
            System.out.println("new teams are loaded from database(not passed winners)");
        } else {
            teams = new ArrayList<>(winners); // Use the winners from the previous round
            winners.clear();  // Clear winners list to prepare for the current round
            System.out.println("teams are passed as winners from prev round");
        }

        System.out.println("Number of teams: " + teams.size());
        System.out.println("Number of labels: " + numberOfTeams.getText());

        if (teams.size() % 2 != 0) {
            throw new IllegalStateException("The number of teams must be even.");
        }

        setTeams(teams);

        for (Team team : teams) {
            saveTeamsInTournament(getTournamentID(), team.getTeamId());
            System.out.print(team.getTeamName() + " | ");
        }
        System.out.println("\n");
        System.out.println("--------------------------------------------");
        System.out.println("Tournament ID : " + getTournamentID());

        for (int i = 0; i < teams.size(); i += 2) {
            Team team1 = teams.get(i);
            Team team2 = teams.get(i + 1);

            Team winner = simulateMatch(team1, team2); // Simulate the match and get the winner
            System.out.println(i + ". Match between: " + team1.getTeamName() + " - and - " + team2.getTeamName() + " == " + winner.getTeamName());
            saveMatch(getTournamentID(), team1.getTeamId(), team2.getTeamId(), winner.getTeamId());
            System.out.println(winner.toString());
            winners.add(winner);
        }
        System.out.println("---------------------------");
    }

    //save the tournament matches and winners to database
    public void saveNewTournament() {
        String sqlQuery = "INSERT INTO tournament(name, type) VALUES(?, ?)";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {
            if (!nameOfTournamentTxtField.getText().isEmpty()) {
                prepare.setString(1, nameOfTournamentTxtField.getText());
                prepare.setString(2, String.valueOf(noOfTeams.getValue()));
                prepare.executeUpdate();
            } else {

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getTournamentID() {
        String sqlQuery = "SELECT * FROM tournament ORDER BY tournament_id DESC LIMIT 1;";
        int id = 0;
        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            if (result.next()) {
                return result.getInt("tournament_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void saveMatch(int tournament_id, int team1Id, int team2Id, int winnerId) {
        String sqlQuery = "INSERT INTO played_match(tournament_id, team1_id, team2_id, winner_id) VALUES(?, ?, ?, ?);";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {
            prepare.setInt(1, tournament_id);
            prepare.setInt(2, team1Id);
            prepare.setInt(3, team2Id);
            prepare.setInt(4, winnerId);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveTeamsInTournament(int tournament_id, int team_Id) {
        String sqlQuery = "INSERT INTO tournament_team(tournament_id, team_id) VALUES(?, ?);";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {
            prepare.setInt(1, tournament_id);
            prepare.setInt(2, team_Id);
            prepare.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Initialize labels for winner messages and winner teams
    private void initializeLabels(int numTeams) {
        winnerMessage = new ArrayList<>();
        winnerLabel = new ArrayList<>();

        for (int i = 1; i <= numTeams / 2; i++) {
            try {
                Label messageLabel = (Label) this.getClass().getDeclaredField("winnerMessage" + i).get(this);
                winnerMessage.add(messageLabel);

                Label label = (Label) this.getClass().getDeclaredField("winnerLabel" + i).get(this);
                winnerLabel.add(label);

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }


    // Initialize team labels
    private void initializeTeamLabels(int numTeams) {
        teamLabels = new ArrayList<>();
        for (int i = 1; i <= numTeams; i++) {
            try {
                Label teamLabel = (Label) this.getClass().getDeclaredField("teamLabel" + i).get(this);
                teamLabels.add(teamLabel);
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
    }

    public void loadAll() {
        initializeLabels(Integer.parseInt(numberOfTeams.getText())); // Initialize labels for up to 32 teams
        initializeTeamLabels(Integer.parseInt(numberOfTeams.getText())); // Ensure teamLabels are initialized
        showTeams(); // Load and display the teams
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (noOfTeams == null) {
            System.out.println("\n");
        } else {
            System.out.println("noOfTeams ChoiceBox is initialized");
            populateNoTeamsChoicebox();
        }

        if (Integer.parseInt(numberOfTeams.getText()) == 32 || Integer.parseInt(numberOfTeams.getText()) == 16 || Integer.parseInt(numberOfTeams.getText()) == 8 || Integer.parseInt(numberOfTeams.getText()) == 4 || Integer.parseInt(numberOfTeams.getText()) == 2) {
            loadAll();
        }
        //  initializeLabels(Integer.parseInt(numberOfTeams.getText())); // Initialize labels for up to 32 teams
        //  initializeTeamLabels(Integer.parseInt(numberOfTeams.getText())); // Ensure teamLabels are initialized
        // showTeams(); // Load and display the teams
    }

}
