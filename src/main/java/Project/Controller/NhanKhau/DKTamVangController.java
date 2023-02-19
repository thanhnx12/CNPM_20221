package Project.Controller.NhanKhau;

import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Manager.TamTruManager;
import Project.Manager.TamVangManager;
import Project.Model.NhanKhau;
import Project.Model.TamTru;
import Project.Model.TamVang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.Date;
import java.util.Optional;

public class DKTamVangController {
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
    private TextArea txtLyDo;

    @FXML
    private TextField txtMaGiay;

    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    TamVang tamVang = new TamVang();
    NhanKhau nhanKhau = new NhanKhau();
    NhanKhauManager nhanKhauManager = new NhanKhauManager();
    TamTruManager tamTruManager = new TamTruManager();

    @FXML
    void actXacNhan(ActionEvent event) {
        System.out.println(dateTuNgay.getValue());
        if(checkvalid()) {
            if(checkisNhanKhau()==0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi tên người");
                alert.setContentText("Người này không có trong danh sách, vui lòng xem lại");
                ButtonType isOK = new ButtonType("OK");
                alert.getButtonTypes().setAll(isOK);
                Optional<ButtonType> Result = alert.showAndWait();
                if (Result.get() == isOK) {
                    alert.close();
                }
            } else if (checkisNhanKhau()==2) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Lỗi tên người");
                alert.setContentText("Người này đang là tạm trú, vui lòng xem lại");
                ButtonType isOK = new ButtonType("OK");
                alert.getButtonTypes().setAll(isOK);
                Optional<ButtonType> Result = alert.showAndWait();
                if (Result.get() == isOK) {
                    alert.close();
                }
            } else {
                nhanKhau = editNktoTV();
                tamVang.setLyDo(txtLyDo.getText());
                tamVang.setMaGiayTamVang(txtMaGiay.getText());
                tamVang.setID(DataAccess.tamVangDAO.getNewID());
                tamVang.setTuNgay(java.sql.Date.valueOf(dateTuNgay.getValue()));
                tamVang.setDenNgay(java.sql.Date.valueOf(dateDenNgay.getValue()));
                tamVang.setIdNhanKhau(nhanKhau.getID());
                DataAccess.tamVangDAO.insert(tamVang);
                TamVangManager.List.add(tamVang);

                Stage stage = (Stage) btnXacNhan.getScene().getWindow();
                stage.close();
            }
        }else {
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

    boolean checkvalid(){
        if(txtLyDo.getText().isEmpty()){
            return false;
        } else if (txtCCCD.getText().isEmpty()) {
            return false;
        } else if(txtMaGiay.getText().isEmpty()){
            return false;
        } else if (dateDenNgay.getValue() == null) {
            return false;
        } else if (dateTuNgay.getValue() == null){
            return false;
        } else return true;
    }

    int checkisNhanKhau(){
        for(NhanKhau x : NhanKhauManager.nhanKhauList){
            if(x.getSoCMT_CCCD() == Integer.parseInt(txtCCCD.getText())) {
                if(x.getGhiChu().equals("Tam tru")) {
                    return 2;
                }else {
                    return 1;
                }
            }
        }
        return 0;
    }
    NhanKhau editNktoTV(){
        for(NhanKhau x : NhanKhauManager.nhanKhauList){
            if(x.getSoCMT_CCCD() == Integer.parseInt(txtCCCD.getText())){
                x.setGhiChu("Tam vang");
                DataAccess.nhanKhauDAO.update(x);
                return x;
            }
        }
        return null;
    }
}
