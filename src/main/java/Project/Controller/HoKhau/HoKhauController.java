package Project.Controller.HoKhau;

import Project.Controller.HomePageController;
import Project.Controller.Main;
import Project.DAO.HoKhauDAO;
import Project.DAO.ThanhVienDAO;
import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;
import Project.Manager.ThanhVienManager;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;
import Project.Model.ThanhVien;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
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
    private Button btnChiTiet;
    @FXML
    private Button btnSuaHoKhau;

    @FXML
    private Button btnTachHoKhau;

    @FXML
    private TextField inpTimKiem;

    @FXML
    private Button btnXoa;

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
    void xoaHoKhau(ActionEvent event) {
        HoKhau hoKhauchon = tableHoKhau.getSelectionModel().getSelectedItem();
        try{
            if(hoKhauchon==null){
                throw new Exception("Bạn chưa chọn hộ khẩu");
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Mời bạn chọn một hộ khẩu");
            alert.setHeaderText(e.getMessage());
            alert.show();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cảnh báo");
        alert.setContentText("Nếu bạn nhấn đồng ý, bạn không thể hoàn tác");
        alert.setHeaderText("Cảnh báo: bạn có chắc muốn xóa hộ khẩu này không");
        alert.showAndWait();
//        System.out.println((alert.getResult().getText().getClass().getName()));
        if(alert.getResult().getText().equals("OK")){
            ArrayList<ThanhVien> thanhVienList = new ArrayList<>(ThanhVienManager.List);
            System.out.println("dang xoa");
            for (ThanhVien thanhVien: thanhVienList) {
                if(thanhVien.getIdHoKhau() == hoKhauchon.getID()){
                    ThanhVienManager.List.remove(thanhVien);
                    new ThanhVienDAO().delete(thanhVien);
                }
            }
            new HoKhauDAO().delete(hoKhauchon);
            HoKhauManager.List.remove(hoKhauchon);
        }
        tatCaHoKhau();
        return;
    }

    @FXML
    void chiTiet(ActionEvent event) throws IOException {
        HoKhau hoKhauchon = tableHoKhau.getSelectionModel().getSelectedItem();
        try{
            if(hoKhauchon==null){
                throw new Exception("Bạn chưa chọn hộ khẩu");
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Mời bạn chọn một hộ khẩu");
            alert.setHeaderText(e.getMessage());
            alert.show();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ChiTietHoKhau.fxml"));
        Parent parent = fxmlLoader.load();
        ChiTietHoKhau controller = (ChiTietHoKhau) fxmlLoader.getController();
        System.out.println(controller);
        controller.setup(hoKhauchon);
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chi tiết hộ khẩu");
        stage1.setScene(scene1);
        stage1.showAndWait();

    }
    @FXML
    void chuyenDi(ActionEvent event) {

    }

    @FXML
    void suaHoKhau(ActionEvent event) throws IOException {
        HoKhau hoKhauchon = tableHoKhau.getSelectionModel().getSelectedItem();
        try{
            if(hoKhauchon==null){
                throw new Exception("Bạn chưa chọn hộ khẩu");
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Mời bạn chọn một hộ khẩu");
            alert.setHeaderText(e.getMessage());
            alert.show();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/SuaHoKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        SuaHoKhauController controller = (SuaHoKhauController) fxmlLoader.getController();
        controller.setup(hoKhauchon);
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Sửa hộ khẩu");
        stage1.setScene(scene1);
        stage1.showAndWait();
        tatCaHoKhau();
    }

    @FXML
    void tachHoKhau(ActionEvent event) throws IOException {
        HoKhau hoKhauchon = tableHoKhau.getSelectionModel().getSelectedItem();
        try{
            if(hoKhauchon==null){
                throw new Exception("Bạn chưa chọn hộ khẩu");
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Mời bạn chọn một hộ khẩu");
            alert.setHeaderText(e.getMessage());
            alert.show();
            return;
        }
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/TachHoKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        TachHoKhauController controller = (TachHoKhauController) fxmlLoader.getController();
        controller.setup(hoKhauchon);
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Tách hộ khẩu");
        stage1.setScene(scene1);
        stage1.showAndWait();
        tatCaHoKhau();
    }

    @FXML
    void tatCaHoKhau() {
        listHoKhau.clear();
        listHoKhau.addAll(HoKhauManager.List);
    }

    @FXML
    void themMoiHoKhau(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ThemHoKhau.fxml"));
        Parent parent = fxmlLoader.load();
        ThemHoKhauController controller = (ThemHoKhauController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Thêm hộ khẩu");
        stage1.setScene(scene1);
        stage1.showAndWait();
        tatCaHoKhau();
    }

    @FXML
    public void timKiem(KeyEvent event) {
        FilteredList<HoKhau> filteredData = new FilteredList<>(listHoKhau, b->true);
        inpTimKiem.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(HoKhau->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if((HoKhau.getMaHoKhau()+"").toLowerCase().indexOf(lowerCaseFilter) != -1){
                    System.out.println(HoKhau.getChuHo().getHoTen());
                    return true;
                }else if (HoKhau.getDiaChi().toLowerCase().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(HoKhau.getChuHo().getHoTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(HoKhau.getChuHo().getDienThoai().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else return false;
            });
        }));
        SortedList<HoKhau> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableHoKhau.comparatorProperty());
        tableHoKhau.setItems(sortedData);
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
