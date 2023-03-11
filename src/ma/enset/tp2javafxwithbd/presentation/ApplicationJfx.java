package ma.enset.tp2javafxwithbd.presentation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ApplicationJfx extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        BorderPane root= FXMLLoader.load(getClass().getResource("views/productViews.fxml"));
        Scene scene=new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Gestion De products");
        primaryStage.show();
    }
}
