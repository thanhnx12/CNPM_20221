package Project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {

    @FXML
    private Button btnDK;

    @FXML
    private Button btnDKTamVang;

    @FXML
    private Button btnDSTamTru;

    @FXML
    private Button btnDSTamVang;

    @FXML
    private Button btnKhaiTu;

    @FXML
    private Button btnTatCa;

    @FXML
    private Button btnThemMoi;

    @FXML
    private TableColumn<?, ?> cDiaChi;

    @FXML
    private TableColumn<NhanKhauController, String> cDienThoai;

    @FXML
    private TableColumn<NhanKhauController, String> cHoVaTen;

    @FXML
    private TableColumn<NhanKhauController, Integer> cID;

    @FXML
    private TableView<NhanKhauController> tableView;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
