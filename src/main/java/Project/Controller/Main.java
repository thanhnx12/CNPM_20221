package Project.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try{
            URL url = new File("src/main/resources/Project.Controller/TaskBar.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            //Parent root = FXMLLoader.load(this.getClass().getResource("TaskBar.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quan Ly Thu Phi");
            primaryStage.show();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        launch();
    }
//    public void close()
}
