package footballcupmanager;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;


public class MainController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button playBtn;
    @FXML
    private Label titleLabel;
    @FXML
    private Button settingsBtn;
    @FXML
    private Button teamsBtn;
    @FXML
    private Button analyticsBtn;

    public void switchToScene(ActionEvent event,String scenePath) throws IOException {
        root = FXMLLoader.load(getClass().getResource(scenePath));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void PlayTournamentBtnClick(ActionEvent event) throws IOException {
    switchToScene(event,"/FxmlFiles/Tournament.fxml");
    }

    public void TeamsBtnClick(ActionEvent event) throws IOException {
        switchToScene(event,"/FxmlFiles/Teams.fxml");
    }
    public void AnalyticsBtnClick(ActionEvent event) throws IOException {
        switchToScene(event,"/FxmlFiles/Analytics.fxml");
    }
    public void SettingsBtnClick(ActionEvent event) throws IOException {
        switchToScene(event,"/FxmlFiles/Settings.fxml");
    }
    public void BacktoHomeBtnClick(ActionEvent event) throws IOException {
        switchToScene(event,"/FxmlFiles/HomeScreen.fxml");
    }




}
