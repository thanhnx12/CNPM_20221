package Project.Controller;

import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;
import Project.Manager.PhiManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Text soHoKhau;

    @FXML
    private Text soKhoanThu;

    @FXML
    private Text soNhanKhau;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        soHoKhau.setText("Số Hộ Khẩu: " + new HoKhauManager().List.size());
        soNhanKhau.setText("Số Nhân Khẩu: " + new NhanKhauManager().nhanKhauList.size());
        soKhoanThu.setText("Số Khoản Thu: " + new PhiManager().List.size());
    }
}
