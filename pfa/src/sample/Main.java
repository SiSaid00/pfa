package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    private static Stage stg;

    @Override
    public void start(Stage primaryStage) throws Exception {
        stg = primaryStage;
        primaryStage.setResizable(true);
        Parent root = FXMLLoader.load(getClass().getResource("front/LogIn.fxml"));
        primaryStage.setTitle("LOGIN");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }

    public void changeScene(String fxml, double h, double w) throws IOException {
        Parent pane = FXMLLoader.load(getClass().getResource(fxml));
        stg.getScene().setRoot(pane);
        stg.setHeight(h);
        stg.setWidth(w);
    }
}
