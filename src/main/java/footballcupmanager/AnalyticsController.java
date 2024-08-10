package footballcupmanager;

import javafx.event.ActionEvent;

import java.io.IOException;

public class AnalyticsController {

    MainController mainController = new MainController();

    public void AnalyticsBackBtnClick(ActionEvent event) throws IOException {
        mainController.switchToScene(event,"/FxmlFiles/HomeScreen.fxml");
    }
}
