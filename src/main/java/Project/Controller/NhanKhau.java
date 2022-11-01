package Project.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NhanKhau implements Initializable {

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
    private TableColumn<NhanKhau, String> cDienThoai;

    @FXML
    private TableColumn<NhanKhau, String> cHoVaTen;

    @FXML
    private TableColumn<NhanKhau, Integer> cID;

    @FXML
    private TableView<NhanKhau> tableView;

    @FXML
    private TextField txtSearch;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }


}
