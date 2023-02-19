package Project.Controller.NhanKhau;

import Project.Controller.NhanKhau.NhanKhauController;
import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Manager.TamVangManager;
import Project.Model.NhanKhau;
import Project.Model.TamVang;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class
ThongTinTamVangController implements Initializable {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnHuyTamVang;

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

    NhanKhauManager nhanKhauManager1 = new NhanKhauManager();
    NhanKhau nhanKhau1 = new NhanKhau();
    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actHuyTamVang(ActionEvent event) {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa tạm trú");
        alert.setHeaderText("Bạn có chắc muốn xóa người này");

        Optional<ButtonType> option = alert.showAndWait();

        if(option.get() == null){

        }else if(option.get() == ButtonType.OK){
            for(NhanKhau x : NhanKhauManager.nhanKhauList){
                if(x.getSoCMT_CCCD() == NhanKhauController.nhanKhauClick.getSoCMT_CCCD()){
                    nhanKhau1 = x;
                    nhanKhau1.setGhiChu("Huy Tam Vang");
                    x.setGhiChu("Huy tam vang");
                    TamVangManager.List.remove(tamVang);
                    DataAccess.tamVangDAO.delete(tamVang);
                    NhanKhauManager.nhanKhauList.remove(x);
//                    DataAccess.nhanKhauDAO.delete(x);
                    NhanKhauManager.nhanKhauList.add(nhanKhau1);
                    DataAccess.nhanKhauDAO.update(x);
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
    TamVang tamVang = new TamVang();
    NhanKhau nhanKhau = new NhanKhau();
    @FXML
    void actXacNhan(ActionEvent event) {
        if(checkvalid()){
            tamVang.setLyDo(txtLyDo.getText());
            tamVang.setMaGiayTamVang(txtMaGiay.getText());
            tamVang.setID(DataAccess.tamVangDAO.getNewID());
            tamVang.setTuNgay(java.sql.Date.valueOf(dateTuNgay.getValue()));
            tamVang.setDenNgay(java.sql.Date.valueOf(dateDenNgay.getValue()));
            tamVang.setIdNhanKhau(NhanKhauController.nhanKhauClick.getID());
            DataAccess.tamVangDAO.update(tamVang);

            nhanKhau.setSoCMT_CCCD(Integer.parseInt(txtCCCD.getText()));
            nhanKhau.setGhiChu("Tạm vắng");
            nhanKhau.setID(tamVang.getIdNhanKhau());
            DataAccess.nhanKhauDAO.update(nhanKhau);

            for(TamVang x : TamVangManager.List){
                if(x.getID() == tamVang.getID()){
                    TamVangManager.List.remove(x);
                    TamVangManager.List.add(tamVang);
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (TamVang x : TamVangManager.List) {
            if (x.getIdNhanKhau() == NhanKhauController.nhanKhauClick.getID()) {
                tamVang = x;
                txtMaGiay.setText("Co nhe");
                break;
            }
        }
        if(tamVang != null) {
            txtCCCD.setText(String.valueOf(NhanKhauController.nhanKhauClick.getSoCMT_CCCD()));
            txtLyDo.setText(tamVang.getLyDo());
            txtMaGiay.setText(tamVang.getMaGiayTamVang());
            dateDenNgay.setValue(tamVang.getDenNgay().toLocalDate());
            dateTuNgay.setValue(tamVang.getTuNgay().toLocalDate());
        }
    }
}
