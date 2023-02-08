package Project.Controller;

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
            khaiTu.setNgayKhai(java.sql.Date.valueOf(dateNgayMat.getValue()));
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
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }
    KhaiTu khaiTu = new KhaiTu();
    NhanKhau nguoiKhai;
    @FXML
    void actChonNguoiKhai(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NhanKhauController.class.getResource("ChonNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chọn người khai");
        stage1.setScene(scene1);
        stage1.showAndWait();
        Project.Controller.ChonNhanKhauController controller = (Project.Controller.ChonNhanKhauController) fxmlLoader.getController();
        nguoiKhai = new NhanKhau();
        nguoiKhai.setID(controller.getNhanKhauChon().getID());
        nguoiKhai.setHoTen(controller.getNhanKhauChon().getHoTen());
        txtNguoiKhai.setText(controller.getNhanKhauChon().getHoTen());


    }
    NhanKhau nguoiMat = new NhanKhau();
    @FXML
    void actChonNguoiMat(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(NhanKhauController.class.getResource("ChonNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chọn người mất");
        stage1.setScene(scene1);
        stage1.showAndWait();
        Project.Controller.ChonNhanKhauController controller = (Project.Controller.ChonNhanKhauController) fxmlLoader.getController();

        nguoiMat.setID(controller.getNhanKhauChon().getID());
        nguoiMat.setHoTen(controller.getNhanKhauChon().getHoTen());
        txtNguoiMat.setText(controller.getNhanKhauChon().getHoTen());

    }

}
