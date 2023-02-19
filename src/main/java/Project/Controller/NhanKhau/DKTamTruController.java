
package Project.Controller.NhanKhau;
import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Manager.TamTruManager;
import Project.Model.NhanKhau;
import Project.Model.TamTru;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DKTamTruController implements Initializable {

    @FXML
    private TextField DienThoai;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private DatePicker dateDenNgay;

    @FXML
    private DatePicker dateTuNgay;

    @FXML
    private TextField txtCCCD;

    @FXML
    private TextField txtHoTen;

    @FXML
    private TextArea txtLyDo;

    @FXML
    private TextField txtMaGiay;

    @FXML
    private TextField txtNoiTamTru;

    TamTru tamTru = new TamTru();
    NhanKhau nhanKhau = new NhanKhau();

    @FXML
    void actDenNgay(ActionEvent event) {

    }

    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actTuNgay(ActionEvent event) {

    }


    @FXML
    public void actXacNhan(ActionEvent event) {
        if (checkValid()) {
            tamTru.setMaGiayTamTru(txtMaGiay.getText());
            tamTru.setNoiTamTru(txtNoiTamTru.getText());
            tamTru.setTuNgay(java.sql.Date.valueOf(dateTuNgay.getValue()));
            tamTru.setDenNgay(java.sql.Date.valueOf(dateDenNgay.getValue()));
            tamTru.setLyDo(txtLyDo.getText());
            tamTru.setID(DataAccess.tamTruDAO.getNewID());
            tamTru.setIdNhanKhau(DataAccess.nhanKhauDAO.getNewID());
            tamTru.setHoTen(txtHoTen.getText());
            TamTruManager.List.add(tamTru);


            nhanKhau.setDiaChiHienTai(txtNoiTamTru.getText());
            nhanKhau.setHoTen(txtHoTen.getText());
            nhanKhau.setDienThoai(DienThoai.getText());
            nhanKhau.setID(tamTru.getIdNhanKhau());
            nhanKhau.setGhiChu("Tam tru");
            NhanKhauManager.nhanKhauList.add(nhanKhau);
            DataAccess.nhanKhauDAO.insert(nhanKhau);
            DataAccess.tamTruDAO.insert(tamTru);
            Stage stage = (Stage) btnXacNhan.getScene().getWindow();
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi nhập thiếu");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }
    boolean checkValid(){
        /**
         *
         * check du lieu nhap vao
         */
        if (DienThoai.getText().isEmpty()){
            return false;
        } else if (txtCCCD.getText().isEmpty()) {
            return false;
        } else if (txtHoTen.getText().isEmpty()) {
            return false;
        } else if (txtNoiTamTru.getText().isEmpty()) {
            return false;
        } else if (txtLyDo.getText().isEmpty()){
            return false;
        } else if (txtMaGiay.getText().isEmpty()) {
            return false;
        } else if (dateDenNgay.getValue() == null) {
            return false;
        } else if ((dateTuNgay.getValue() == null)) {
            return false;
        }else return true;
    }
    public TamTru getTamTru(){
        return tamTru;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
