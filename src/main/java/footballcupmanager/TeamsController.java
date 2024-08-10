package footballcupmanager;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class TeamsController implements Initializable {
    // Main Controller instance
    MainController mainController = new MainController();

    // FXML Containers
    @FXML
    private TextField countryTxtField;

    @FXML
    private TextField nameTxtField;
    @FXML
    private TextField teamIdTxtField;

    @FXML
    private TableView<Team> teamsTable;

    @FXML
    private TableColumn<Team, String> ttCountry;

    @FXML
    private TableColumn<Team, Integer> ttID;

    @FXML
    private TableColumn<Team, String> ttName;

    private ObservableList<Team> teamsList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Initialize the table when the controller is initialized
        showTeamsTable();
    }

    public void addNewTeam() {
        String sqlQuery = "INSERT INTO TEAM (team_name, team_country) VALUES (?, ?)";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {

            if (nameTxtField.getText().isEmpty() || countryTxtField.getText().isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText("Empty fields!");
                alert.setContentText("Please enter all information!");
                alert.showAndWait();
            } else {
                prepare.setString(1, nameTxtField.getText());
                prepare.setString(2, countryTxtField.getText());
                prepare.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText("Successful addition!");
                alert.setContentText("You successfuly added the team!");
                alert.showAndWait();
                showTeamsTable(); // Refresh the table after adding a new team
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public ObservableList<Team> addTeamListData() {
        ObservableList<Team> listData = FXCollections.observableArrayList();
        String sqlQuery = "SELECT * FROM team";

        try (Connection connect = Database.connectDb();
             PreparedStatement prepare = connect.prepareStatement(sqlQuery);
             ResultSet result = prepare.executeQuery()) {

            while (result.next()) {
                Team team = new Team(result.getInt("team_id"), result.getString("team_name"), result.getString("team_country"));
                listData.add(team);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listData;
    }

    public void showTeamsTable() {
        teamsList = addTeamListData();

        ttID.setCellValueFactory(new PropertyValueFactory<>("teamId"));
        ttName.setCellValueFactory(new PropertyValueFactory<>("teamName"));
        ttCountry.setCellValueFactory(new PropertyValueFactory<>("teamCountry"));

        teamsTable.setItems(teamsList);
    }

    public void updateTeam() {
        String sqlQuery = "UPDATE team SET team_name = ?, team_country = ? WHERE team_id = ?;";
        if (nameTxtField.getText().isEmpty() || countryTxtField.getText().isEmpty() || teamIdTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty fields!");
            alert.setContentText("Please enter all information!");
            alert.showAndWait();
        } else {
            try (Connection connect = Database.connectDb();
                 PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {

                prepare.setString(1, nameTxtField.getText());
                prepare.setString(2, countryTxtField.getText());
                prepare.setInt(3, Integer.parseInt(teamIdTxtField.getText()));  // Set the team ID as an integer
                prepare.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText("Successful update!");
                alert.setContentText("You successfuly updated the team!");
                alert.showAndWait();
                showTeamsTable();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void deleteTeam() {
        String sqlQuery = "DELETE FROM team WHERE team_id = ?;";
        if (teamIdTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty ID field!");
            alert.setContentText("Please enter ID!");
            alert.showAndWait();
        } else {
            try (Connection connect = Database.connectDb();
                 PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {

                prepare.setString(1, teamIdTxtField.getText());
                prepare.executeUpdate();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Successful");
                alert.setHeaderText("Successful deletion!");
                alert.setContentText("You successfuly deleted the team!");
                alert.showAndWait();
                showTeamsTable();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void selectTeam() {
        String sqlQuery = "SELECT * FROM team WHERE team_id = ?;";

        if (teamIdTxtField.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Empty ID field!");
            alert.setContentText("Please enter ID!");
            alert.showAndWait();
        } else {

            try (Connection connect = Database.connectDb();
                 PreparedStatement prepare = connect.prepareStatement(sqlQuery)) {

                prepare.setString(1, teamIdTxtField.getText());
                ResultSet result = prepare.executeQuery();

                if (result.next()) {
                    nameTxtField.setText(result.getString("team_name"));
                    countryTxtField.setText(result.getString("team_country"));
                }else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Team Not Found");
                    alert.setHeaderText(null);
                    alert.setContentText("No team found with the given ID.");
                    alert.showAndWait();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void clearFields() {
        teamIdTxtField.setText("");
        nameTxtField.setText("");
        countryTxtField.setText("");
    }

    public void TeamsBackBtnClick(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/HomeScreen.fxml");
    }
}
