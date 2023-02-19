package Project.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class TaskBarController implements Initializable {

    @FXML
    private Button HoKhau;

    @FXML
    private Button KhoanThu;

    @FXML
    private Button NhanKhau;

    @FXML
    private Button NopTien;

    @FXML
    private Button TrangChu;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private VBox vbox;

    @FXML
    void aHoKhau(ActionEvent event) {
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("HoKhau/HoKhau.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void aKhoanThu(ActionEvent event) {
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("Phi/KhoanThu.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void aNhanKhau(ActionEvent event) {
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("NhanKhau/NhanKhauView.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void aNopTien(ActionEvent event) {
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("Phi/NopTien.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void aTrangChu(ActionEvent event) {
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("HomePageView.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            anchorPane.getChildren().clear();
            Parent rootChild = FXMLLoader.load(this.getClass().getResource("HomePageView.fxml"));
            anchorPane.getChildren().add(rootChild);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
