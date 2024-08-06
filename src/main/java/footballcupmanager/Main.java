package footballcupmanager;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FxmlFiles/HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/CssFiles/main.css").toExternalForm());
        stage.setTitle("Football Cup Manager!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}