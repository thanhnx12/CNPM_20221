package Project.Controller.NhanKhau;

import Project.Controller.NhanKhau.NhanKhauController;
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


public class ThongTinTamTruController implements Initializable {


    @FXML
    private TextField DienThoai;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private Button btnXoaTamTru;

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

    @FXML
    void actDenNgay(ActionEvent event) {

    }
    TamTru tamTru = new TamTru();
    NhanKhau nhanKhau = new NhanKhau();
    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actTuNgay(ActionEvent event) {

    }

    @FXML
    void actXacNhan(ActionEvent event) {
        if(checkValid()){
            tamTru.setMaGiayTamTru(txtMaGiay.getText());
            tamTru.setNoiTamTru(txtNoiTamTru.getText());
            tamTru.setTuNgay(java.sql.Date.valueOf(dateTuNgay.getValue()));
            tamTru.setDenNgay(java.sql.Date.valueOf(dateDenNgay.getValue()));
            tamTru.setLyDo(txtLyDo.getText());
            tamTru.setIdNhanKhau(NhanKhauController.nhanKhauClick.getID());
            tamTru.setHoTen(txtHoTen.getText());
            DataAccess.tamTruDAO.update(tamTru);

            nhanKhau.setDiaChiHienTai(txtNoiTamTru.getText());
            nhanKhau.setHoTen(txtHoTen.getText());
            nhanKhau.setSoCMT_CCCD(Integer.parseInt(txtCCCD.getText()));
            nhanKhau.setDienThoai(DienThoai.getText());
            nhanKhau.setID(tamTru.getIdNhanKhau());
            nhanKhau.setGhiChu("Tam tru");
            DataAccess.nhanKhauDAO.update(nhanKhau);

            for(TamTru x : TamTruManager.List){
                if(x.getID() == tamTru.getID()){
                    TamTruManager.List.remove(x);
                    TamTruManager.List.add(tamTru);
                    break;
                }
            }

            for(NhanKhau x : NhanKhauManager.nhanKhauList){
                if(x.getID() == nhanKhau.getID()) {
                    NhanKhauManager.nhanKhauList.remove(x);
                    NhanKhauManager.nhanKhauList.add(nhanKhau);
                    break;
                }
            }
            Stage stage = (Stage) btnXacNhan.getScene().getWindow();
            stage.close();

        }
    }

    @FXML
    void actXoaTamTru(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa tạm trú");
        alert.setHeaderText("Bạn có chắc muốn xóa người này");

        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == null){

        }else if(option.get() == ButtonType.OK){
            for(NhanKhau x : NhanKhauManager.nhanKhauList){
                if(x.getSoCMT_CCCD() == NhanKhauController.nhanKhauClick.getSoCMT_CCCD()){
                    TamTruManager.List.remove(tamTru);
                    DataAccess.tamTruDAO.delete(tamTru);
                    NhanKhauManager.nhanKhauList.remove(x);
                    DataAccess.nhanKhauDAO.delete(x);
                    break;
                }
            }
            alert.close();
            Stage stage = (Stage) btnHuy.getScene().getWindow();
            stage.close();
        } else if (option.get() == ButtonType.CANCEL) {
            alert.close();
        } else {
            alert.close();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (TamTru x : TamTruManager.List) {
            if (x.getIdNhanKhau() == NhanKhauController.nhanKhauClick.getID()) {
                tamTru = x;
//                txtMaGiay.setText("Co nhe");
                break;
            }
        }
        if (tamTru != null) {
            txtHoTen.setText(String.valueOf(TamTruManager.List.size()));
            DienThoai.setText(NhanKhauController.nhanKhauClick.getDienThoai());
            txtCCCD.setText(String.valueOf(NhanKhauController.nhanKhauClick.getSoCMT_CCCD()));
//            txtHoTen.setText(NhanKhauController.nhanKhauClick.getHoTen());
            txtLyDo.setText(tamTru.getLyDo());
//            txtMaGiay.setText(tamTru.getMaGiayTamTru());
            txtNoiTamTru.setText(tamTru.getNoiTamTru());
            dateDenNgay.setValue(tamTru.getDenNgay().toLocalDate());
            dateTuNgay.setValue(tamTru.getTuNgay().toLocalDate());
        }
    }
}


