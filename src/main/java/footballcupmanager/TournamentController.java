package footballcupmanager;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TournamentController implements Initializable {

    @FXML
    private ChoiceBox<Integer> noOfTeams;
    @FXML
    private Label winnerMessage1;

    @FXML
    private Label winnerMessage10;

    @FXML
    private Label winnerMessage11;

    @FXML
    private Label winnerMessage12;

    @FXML
    private Label winnerMessage13;

    @FXML
    private Label winnerMessage14;

    @FXML
    private Label winnerMessage15;

    @FXML
    private Label winnerMessage16;

    @FXML
    private Label winnerMessage2;

    @FXML
    private Label winnerMessage3;

    @FXML
    private Label winnerMessage4;

    @FXML
    private Label winnerMessage5;

    @FXML
    private Label winnerMessage6;

    @FXML
    private Label winnerMessage7;

    @FXML
    private Label winnerMessage8;

    @FXML
    private Label winnerMessage9;
    private List<Label> winnerLabels;
    MainController mainController = new MainController();


    public void populateNoTeamsChoicebox() {
        //noOfTeams.getItems().clear();
        noOfTeams.getItems().addAll(32, 16, 8, 4, 2);
        System.out.println("ChoiceBox populated: " + noOfTeams.getItems());
    }

    public void TournamentBackBtnClick(ActionEvent event) throws IOException {
        mainController.switchToScene(event, "/FxmlFiles/HomeScreen.fxml");
    }


    public void StartTheTournament(ActionEvent event) throws IOException {

        if (noOfTeams.getValue() == null) {
            System.out.println("Choose right type of tournament!");
            return;
        }
        switch (noOfTeams.getValue()) {
            case 32:
                mainController.switchToScene(event, "/FxmlFiles/RoundOf32.fxml");
                break;
            case 16:
                mainController.switchToScene(event, "/FxmlFiles/RoundOf16.fxml");
                break;
            case 8:
                mainController.switchToScene(event, "/FxmlFiles/QuarterFinals.fxml");
                break;
            case 4:
                mainController.switchToScene(event, "/FxmlFiles/SemiFinals.fxml");
                break;
            case 2:
                mainController.switchToScene(event, "/FxmlFiles/Final.fxml");
                break;
            default:
                System.out.println("Choose right type of tournament!");
        }


    }

    public void showWinnerTeams() {

    }

    public void showWinnerMessages() {
        for (Label label : winnerLabels) {
            label.setVisible(true);
            // Add any additional initialization here
        }
    }
    public void play32Matches(){
        showWinnerMessages();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (noOfTeams == null) {
            System.out.println("noOfTeams ChoiceBox is null!");
        } else {
            System.out.println("noOfTeams ChoiceBox is initialized");
            populateNoTeamsChoicebox();
        }

        winnerLabels = new ArrayList<>();

        // Dynamically populate the list with the labels
        for (int i = 1; i <= 16; i++) {
            Label label = null;
            try {
                label = (Label) this.getClass().getDeclaredField("winnerMessage" + i).get(this);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e);
            }
            winnerLabels.add(label);
        }
    }
}
