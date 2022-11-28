package Project.Controller;

import Project.DAO.*;
import Project.Manager.*;
import Project.Model.NhanKhau;
import Project.Model.Users;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;


public class Main extends Application {
    public static Users user = new Users(); // Người đang sử dụng hệ thống
    @Override
    public void start(Stage primaryStage) {
        try{
            Parent root = FXMLLoader.load(this.getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Quản lý thu phí");
            primaryStage.show();
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();
        TamTruDAO tamTruDAO = new TamTruDAO();
        TamVangDAO tamVangDAO = new TamVangDAO();
        TamTruManager.List = tamTruDAO.selectAll();
        TamVangManager.List = tamVangDAO.selectAll();
        NhanKhauManager.nhanKhauList = nhanKhauDAO.selectAll();
        HoKhauManager.List =new HoKhauDAO().selectAll();
        NhanKhauManager.nhanKhauList = new NhanKhauDAO().selectAll();
        ThanhVienManager.List =  new ThanhVienDAO().selectAll();
        UsersManager.List = new UsersDAO().selectAll();
        DaNopManager.List = DataAccess.daNopDAO.selectAll();
        PhiManager.List = DataAccess.phiDAO.selectAll();
        launch();
    }
//    public void close()
}