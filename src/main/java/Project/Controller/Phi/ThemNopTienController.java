package Project.Controller.Phi;

import Project.Controller.HoKhau.ChonHoGDController;
import Project.Controller.Main;
import Project.DAO.DataAccess;
import Project.Manager.DaNopManager;
import Project.Manager.NhanKhauManager;
import Project.Model.DaNop;
import Project.Model.HoKhau;
import Project.Model.Phi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThemNopTienController implements Initializable {

    @FXML
    private Button btnChonHoGD;

    @FXML
    private Button btnChonKhoanThu;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private DatePicker dateNgayNop;

    @FXML
    private Text txtIDhoKhau;

    @FXML
    private Text txtKhoanThu;

    @FXML
    private Text txtLoaiKhoanThu;

    @FXML
    private Text txtTenChuHo;

    @FXML
    private Text txtTien;
    @FXML
    private TextField txtSoTienNop;

    HoKhau hoKhau = new HoKhau();
    Phi phi = new Phi();
    DaNop daNop = new DaNop();
    @FXML
    void actChonHoGD(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ChonHoGD.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chọn hộ gia đình");
        stage1.setScene(scene1);
        stage1.showAndWait();
        hoKhau = ChonHoGDController.hoKhauSelected;
        NhanKhauManager nhanKhauManager = new NhanKhauManager();
        if(hoKhau.getID() != -1){
            txtIDhoKhau.setText(hoKhau.getID()+"");
            txtTenChuHo.setText(nhanKhauManager.get(hoKhau.getIdChuHo()).getHoTen());
            if(phi.getID() != -1){
                double soTienPhaiDong = 0;
                if(phi.isThuTheoHo()){
                    soTienPhaiDong = phi.getSoTien();
                }else{
                    soTienPhaiDong = DataAccess.hoKhauDAO.soThanhVien(hoKhau) * phi.getSoTien();
                }
                txtTien.setText(soTienPhaiDong + "");
            }
        }
    }

    @FXML
    void actChonKhoanThu(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(KhoanThuController.class.getResource("ChonKhoanThu.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chọn khoản thu");
        stage1.setScene(scene1);
        stage1.showAndWait();
        phi = ChonKhoanThuController.phiSelected;
        if(phi.getID() != -1){
            txtKhoanThu.setText(phi.getTenLoaiPhi());
            if(phi.isTuNguyen()) txtLoaiKhoanThu.setText("Tự nguyện");
            else txtLoaiKhoanThu.setText("Bắt buộc");
            if(phi.isTuNguyen()) txtSoTienNop.setVisible(true);
            else txtSoTienNop.setVisible(false);
            if(hoKhau.getID() != -1){
                double soTienPhaiDong = 0;
                if(phi.isThuTheoHo()){
                    soTienPhaiDong = phi.getSoTien();
                }else{
                    soTienPhaiDong = DataAccess.hoKhauDAO.soThanhVien(hoKhau) * phi.getSoTien();
                }
                txtTien.setText(soTienPhaiDong + "");
            }
        }
    }

    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actXacNhan(ActionEvent event) {
        try{
            if(phi.getID() == -1 || hoKhau.getID() == -1){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Dữ liệu không hợp lệ");
                alert.setContentText("Vui lòng chọn đầy đủ thông tin");
                ButtonType isOK = new ButtonType("OK");
                alert.getButtonTypes().setAll(isOK);
                Optional<ButtonType> Result = alert.showAndWait();
                if (Result.get() == isOK) {
                    alert.close();
                }
            }else{
                daNop.setNgayNop(java.sql.Date.valueOf(dateNgayNop.getValue()));
                if(phi.isTuNguyen()){
                    daNop.setSoTien(Double.valueOf(txtSoTienNop.getText()));
                }else{
                    daNop.setSoTien(Double.valueOf(txtTien.getText()));
                }
                daNop.setIdHoKhau(hoKhau.getID());
                daNop.setID(DataAccess.daNopDAO.getNewID());
                daNop.setIdPhi(phi.getID());
                DataAccess.daNopDAO.insert(daNop);
                DaNopManager.List.clear();
                DaNopManager.List = DataAccess.daNopDAO.selectAll();
                Stage stage = (Stage) txtSoTienNop.getScene().getWindow();
                stage.close();
            }
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dữ liệu không hợp lệ");
            alert.setContentText("Vui lòng chọn đầy đủ thông tin");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateNgayNop.setValue(LocalDate.now());
        phi.setID(-1);
    }
}
