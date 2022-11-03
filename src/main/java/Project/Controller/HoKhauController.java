package Project.Controller;

import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HoKhauController implements Initializable {
    ObservableList<HoKhau> listHoKhau = FXCollections.observableArrayList();

    public HoKhauController() {
        timChuHo(HoKhauManager.List);
        listHoKhau.addAll(HoKhauManager.List);
    }

    private void timChuHo(ArrayList<HoKhau> listHoKhau) {
        //tìm kiếm trong list nhân khẩu xem nhân khẩu nào là chủ hộ để lấy thông tin về số điện thoại
        for (HoKhau hoKhau : listHoKhau) {
            for (NhanKhau nhanKhau : NhanKhauManager.nhanKhauList) {
                if(hoKhau.getIdChuHo() == nhanKhau.getID()){
                    // System.out.println((nhanKhau.getDienThoai()));
                    hoKhau.setChuHo(nhanKhau);
                }
            }
        }
    }

    @FXML
    private Button btnChuyenDi;

    @FXML
    private Button btnSuaHoKhau;

    @FXML
    private Button btnTachHoKhau;
    @FXML
    private Button btnTimKiem;
    @FXML

    private TextField inpTimKiem;

    @FXML
    private Button btnTatCa;

    @FXML
    private Button btnThemMoi;

    @FXML
    private TableColumn<HoKhau, String> colChuHo;

    @FXML
    private TableColumn<HoKhau, String> colDiaChi;

    @FXML
    private TableColumn<HoKhau, String> colMaHoKhau;

   @FXML
   private TableColumn<HoKhau, String> colSoDienThoai;

    @FXML
    private TableView<HoKhau> tableHoKhau;

    @FXML
    void chuyenDi(ActionEvent event) {

    }

    @FXML
    void suaHoKhau(ActionEvent event) {

    }

    @FXML
    void tachHoKhau(ActionEvent event) {

    }

    @FXML
    void tatCaHoKhau() {
        listHoKhau.clear();
        listHoKhau.addAll(HoKhauManager.List);
    }

    @FXML
    void themMoiHoKhau(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ThemHoKhau.fxml"));
        Parent parent = fxmlLoader.load();
        ThemHoKhauController controller = (ThemHoKhauController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Them Ho Khau");
        stage1.setScene(scene1);
        stage1.showAndWait();
        tatCaHoKhau();
    }

    @FXML
    void timKiem(ActionEvent event) {
        listHoKhau.clear();
        if(inpTimKiem.getText().isBlank())
            listHoKhau.addAll(HoKhauManager.List);
        else {
            for(HoKhau hk : HoKhauManager.List) {
                if(hk.getMaHoKhau().contains(inpTimKiem.getText())){
                    listHoKhau.add(hk);
                }
            }
        }
        inpTimKiem.setText("");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colChuHo.setCellValueFactory(cd->new SimpleStringProperty(cd.getValue().getChuHo().getHoTen()));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("diaChi"));
        colMaHoKhau.setCellValueFactory(new PropertyValueFactory<HoKhau,String>("maHoKhau"));
        colSoDienThoai.setCellValueFactory(cd->new SimpleStringProperty(cd.getValue().getChuHo().getDienThoai()));
        // hoKhauList.clear();
        tableHoKhau.setItems(listHoKhau);
    }
}
