package Project.Controller;

import Project.DAO.ThanhVienDAO;
import Project.Manager.ThanhVienManager;
import Project.Model.ThanhVien;
import javafx.application.Application;
import javafx.beans.Observable;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;

import Project.DAO.HoKhauDAO;
import Project.DAO.NhanKhauDAO;
import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;


public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("HoKhau.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quan Ly Thu Phi");
            primaryStage.show();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        HoKhauManager.listHoKhau =new HoKhauDAO().selectAll();
        NhanKhauManager.nhanKhauList = new NhanKhauDAO().selectAll();
        ThanhVienManager.List =  new ThanhVienDAO().selectAll();
        launch();
//        ObservableList<H>
    }
//    public void close()
}
