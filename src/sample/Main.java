package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("ui/sample.fxml"));
        primaryStage.setTitle("Divisor counter");
        primaryStage.setScene(new Scene(root, 500, 200));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
