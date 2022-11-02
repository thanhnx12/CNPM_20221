
package Project.Controller;
import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Manager.TamTruManager;
import Project.Model.NhanKhau;
import Project.Model.TamTru;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DKTamTruController implements Initializable {

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
    private TextField DienThoai;

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

    }

    @FXML
    void actTuNgay(ActionEvent event) {

    }



    @FXML
    public  void actXacNhan(ActionEvent event){
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
        nhanKhau.setGhiChu("Tạm trú");
        NhanKhauManager.nhanKhauList.add(nhanKhau);
        DataAccess.nhanKhauDAO.insert(nhanKhau);
        DataAccess.tamTruDAO.insert(tamTru);

        Stage stage = (Stage) btnXacNhan.getScene().getWindow();
        stage.close();

    }
    boolean checkValid(){
        /**
         * check du lieu nhap vao
         */
        return true;
    }
    public TamTru getTamTru(){
        return tamTru;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
