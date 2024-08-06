package footballcupmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/FxmlFiles/HomeScreen.fxml"));
        AnchorPane root = fxmlLoader.load();

        // Load the background image
        Image backgroundImage = new Image(Main.class.getResource("/images/background.jpg").toExternalForm());
        BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, BackgroundSize.DEFAULT);
        root.setBackground(new Background(background));

        Scene scene = new Scene(root);
        scene.getStylesheets().add(Main.class.getResource("/CssFiles/main.css").toExternalForm());
        stage.setTitle("Football Cup Manager!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(Main.class);
    }
}
