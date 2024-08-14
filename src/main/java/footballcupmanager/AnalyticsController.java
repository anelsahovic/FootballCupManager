package footballcupmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class AnalyticsController implements Initializable {
    @FXML
    private TableColumn<PlayedMatch, String> pmAwayTeam;

    @FXML
    private TableColumn<PlayedMatch, String> pmHomeTeam;

    @FXML
    private TableColumn<PlayedMatch, Integer> pmId;

    @FXML
    private TableView<PlayedMatch> pmTable;

    @FXML
    private TableColumn<PlayedMatch, String> pmWinner;

    @FXML
    private TableColumn<Tournament, Integer> tourId;

    @FXML
    private TextField tourIdTxtField;

    @FXML
    private TableColumn<Tournament, String> tourName;

    @FXML
    private TableColumn<Tournament, Integer> tourNoTeams;

    @FXML
    private TableView<Tournament> tourTable;

    private ObservableList<Tournament> tournamentList;
    private ObservableList<PlayedMatch> matchesList;

    MainController mainController = new MainController();

    public void AnalyticsBackBtnClick(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/HomeScreen.fxml");
    }


    public ObservableList<Tournament> addTournamentListData() {
        ObservableList<Tournament> listData = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM tournament";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                Tournament tournament = new Tournament(result.getInt("tournament_id"), result.getString("name"), result.getInt("type"));
                listData.add(tournament);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void showTournaments() {
        tournamentList = addTournamentListData();

        tourId.setCellValueFactory(new PropertyValueFactory<>("tournamentId"));
        tourName.setCellValueFactory(new PropertyValueFactory<>("name"));
        tourNoTeams.setCellValueFactory(new PropertyValueFactory<>("numOfTeams"));

        tourTable.setItems(tournamentList);
    }

    public ObservableList<PlayedMatch> addPlayedMatchesListData() {
        ObservableList<PlayedMatch> listData = FXCollections.observableArrayList();
        String sqlQuery = "SELECT \n" +
                "    pm.match_id,\n" +
                "    pm.tournament_id ,\n" +
                "    t1.team_name AS team1name,\n" +
                "    t2.team_name AS team2name,\n" +
                "    tw.team_name AS winner_name\n" +
                "FROM \n" +
                "    played_match pm\n" +
                "JOIN \n" +
                "    team t1 ON pm.team1_id = t1.team_id\n" +
                "JOIN \n" +
                "    team t2 ON pm.team2_id = t2.team_id\n" +
                "JOIN \n" +
                "    team tw ON pm.winner_id = tw.team_id WHERE pm.tournament_id="+Integer.parseInt(tourIdTxtField.getText())+";\n";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                PlayedMatch match = new PlayedMatch(result.getInt("match_id"), result.getInt("tournament_id"), result.getString("team1name"), result.getString("team2name"), result.getString("winner_name"));
                listData.add(match);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void showPlayedMatches() {
        matchesList = addPlayedMatchesListData();

        pmId.setCellValueFactory(new PropertyValueFactory<>("matchId"));
        pmHomeTeam.setCellValueFactory(new PropertyValueFactory<>("team1"));
        pmAwayTeam.setCellValueFactory(new PropertyValueFactory<>("team2"));
        pmWinner.setCellValueFactory(new PropertyValueFactory<>("winner"));

        pmTable.setItems(matchesList);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showTournaments();
    }
}
