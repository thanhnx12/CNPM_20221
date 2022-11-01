package Project.Controller;

import Project.DAO.NhanKhauDAO;
import Project.DAO.TamTruDAO;
import Project.Manager.NhanKhauManager;
import Project.Manager.TamTruManager;
import Project.Model.NhanKhau;
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
//            URL url = new File("src/main/resources/Project.Controller/NhanKhauView.fxml").toURI().toURL();
//            Parent root = FXMLLoader.load(url);
//            //Parent root = FXMLLoader.load(this.getClass().getResource("TaskBar.fxml"));
//            Scene scene = new Scene(root);
//            primaryStage.setScene(scene);
//            primaryStage.setTitle("Quan Ly Thu Phi");
//            primaryStage.show();
            Parent root = FXMLLoader.load(this.getClass().getResource("NhanKhauView.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("One Media");
            primaryStage.show();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();
        TamTruDAO tamTruDAO = new TamTruDAO();
        TamTruManager.List = tamTruDAO.selectAll();
        NhanKhauManager.nhanKhauList = nhanKhauDAO.selectAll();
        launch();
    }
//    public void close()
}
