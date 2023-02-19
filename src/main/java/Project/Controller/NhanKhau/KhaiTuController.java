package Project.Controller.NhanKhau;

import Project.Controller.Main;
import Project.Controller.NhanKhau.NhanKhauController;
import Project.DAO.KhaiTuDAO;
import Project.DAO.NhanKhauDAO;
import Project.Manager.NhanKhauManager;
import Project.Model.KhaiTu;
import Project.Model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

public class KhaiTuController {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private Button chonNguoiKhai;

    @FXML
    private Button chonNguoiMat;

    @FXML
    private DatePicker dateNgayMat;

    @FXML
    private TextArea txtLyDo;

    @FXML
    private TextField txtNguoiKhai;

    @FXML
    private TextField txtNguoiMat;

    @FXML
    private TextField txtSo;

    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actXacNhan(ActionEvent event) {
        try{
            txtNguoiKhai.setText(nguoiKhai.getHoTen());
            khaiTu.setIdNguoiKhai(nguoiKhai.getID());
            khaiTu.setIdNguoiChet(nguoiMat.getID());
            khaiTu.setNgayKhai(java.sql.Date.valueOf(LocalDate.now()));
            khaiTu.setNgayChet(java.sql.Date.valueOf(dateNgayMat.getValue()));
            khaiTu.setSoGiayKhaiTu(Integer.parseInt(txtSo.getText()));
            khaiTu.setLyDoChet(txtLyDo.getText());
            new KhaiTuDAO().insert(khaiTu);
            nguoiMat = new NhanKhauManager().get(nguoiMat.getID());
            nguoiMat.setGhiChu("Da Mat");
            new NhanKhauDAO().update(nguoiMat);
            Stage stage = (Stage) btnHuy.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi");
            alert.setContentText("Có lỗi xảy ra , vui lòng kiểm tra lại thông tin !");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }
    static KhaiTu khaiTu = new KhaiTu();
    static NhanKhau nguoiKhai;
    @FXML
    void actChonNguoiKhai(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ChonNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chọn người khai");
        stage1.setScene(scene1);
        stage1.showAndWait();
        Project.Controller.HoKhau.ChonNhanKhauController controller = (Project.Controller.HoKhau.ChonNhanKhauController) fxmlLoader.getController();
        nguoiKhai = new NhanKhau();
        nguoiKhai.setID(controller.nhanKhauChon.getID());
        nguoiKhai.setHoTen(controller.nhanKhauChon.getHoTen());
        txtNguoiKhai.setText(controller.nhanKhauChon.getHoTen());


    }
    static NhanKhau nguoiMat = new NhanKhau();
    @FXML
    void actChonNguoiMat(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ChonNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chọn người mất");
        stage1.setScene(scene1);
        stage1.showAndWait();
        Project.Controller.HoKhau.ChonNhanKhauController controller = (Project.Controller.HoKhau.ChonNhanKhauController) fxmlLoader.getController();
        nguoiMat.setID(controller.nhanKhauChon.getID());
        nguoiMat.setHoTen(controller.nhanKhauChon.getHoTen());
        txtNguoiMat.setText(controller.nhanKhauChon.getHoTen());

    }

}
