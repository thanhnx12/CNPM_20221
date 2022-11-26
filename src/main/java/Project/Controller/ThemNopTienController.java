package Project.Controller;

import Project.Model.DaNop;
import Project.Model.HoKhau;
import Project.Model.Phi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
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
    HoKhau hoKhau = new HoKhau();
    Phi phi = new Phi();
    DaNop daNop = new DaNop();
    @FXML
    void actChonHoGD(ActionEvent event) {

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

        }catch (Exception e){

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dateNgayNop.setValue(LocalDate.now());
        phi.setID(-1);
    }
}
