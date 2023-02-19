package Project.Controller.NhanKhau;

import Project.Controller.Main;
import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Model.NhanKhau;
import Project.Model.TamTru;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.EventListener;
import java.util.ResourceBundle;

public class NhanKhauController implements Initializable {

    @FXML
    private Button btnDKTamVang;

    @FXML
    private Button btnDKTamtru;

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
    private Button btnTimKiem;

    @FXML
    private TableColumn<NhanKhau, String> cDiaChi;

    @FXML
    private TableColumn<NhanKhau, String> cDienThoai;

    @FXML
    private TableColumn<NhanKhau, String> cHoVaTen;

    @FXML
    private TableColumn<NhanKhau, Integer> cID;
    @FXML
    private TableColumn<NhanKhau,String> cGhiChu;
    @FXML
    private TableView<NhanKhau> tableView;

    @FXML
    private TextField txtSearch;

    ObservableList<NhanKhau> data = FXCollections.observableArrayList();


    public void setCellTable(){
        cDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChiHienTai"));
        cDienThoai.setCellValueFactory(new PropertyValueFactory<>("dienThoai"));
        cID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cHoVaTen.setCellValueFactory(new PropertyValueFactory<>("hoTen"));
        cGhiChu.setCellValueFactory(new PropertyValueFactory<>("ghiChu"));
    }
    public void readDataFromDB() throws SQLException{
        data = FXCollections.observableArrayList();
        data.addAll(DataAccess.nhanKhauDAO.selectAll());
        tableView.setItems(data);
    }
    @FXML
    void atxtSearch(KeyEvent event) {
        FilteredList<NhanKhau> filteredData = new FilteredList<>(data, b->true);

        txtSearch.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(NhanKhau->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if((NhanKhau.getID()+"").toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else if (NhanKhau.getHoTen().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else if(NhanKhau.getDiaChiHienTai().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                }else return false;
            });
        }));
        SortedList<NhanKhau> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }

    @FXML
    void actDKTamTru(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(NhanKhauController.class.getResource("DKTamTruView.fxml"));
        Parent parent = fxmlLoader.load();
        Project.Controller.NhanKhau.DKTamTruController controller = (Project.Controller.NhanKhau.DKTamTruController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Đăng ký tạm trú");
        stage1.setScene(scene1);
        stage1.showAndWait();
        readDataFromDB();

    }

    @FXML
    void actDKTamVang(ActionEvent event) throws IOException, SQLException{
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NhanKhau/DKTamVangView.fxml"));
        Parent parent = fxmlLoader.load();
        Project.Controller.NhanKhau.DKTamVangController controller = (Project.Controller.NhanKhau.DKTamVangController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Đăng ký tạm vắng");
        stage1.setScene(scene1);
        stage1.showAndWait();
        readDataFromDB();

    }

    @FXML
    void actDSTamTru(ActionEvent event) {
        ObservableList<NhanKhau> dataTamTru = FXCollections.observableArrayList();
        for(NhanKhau x : NhanKhauManager.nhanKhauList){
            if(x.getGhiChu().equals("Tam tru")){
                dataTamTru.add(x);
            }
        }
        tableView.setItems(dataTamTru);
    }

    @FXML
    void actDSTamVang(ActionEvent event) {
        ObservableList<NhanKhau> dataTamVang = FXCollections.observableArrayList();
        for(NhanKhau x : NhanKhauManager.nhanKhauList){
            if(x.getGhiChu().equals("Tam vang")){
                dataTamVang.add(x);
            }
        }
        tableView.setItems(dataTamVang);
    }

    @FXML
    void actKhaiTu(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NhanKhau/KhaiTuView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Khai Tu");
        stage1.setScene(scene1);
        stage1.showAndWait();
        readDataFromDB();
    }

    @FXML
    void actTatCa(ActionEvent event) throws SQLException {
        readDataFromDB();
    }

    @FXML
    void actThemMoi(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NhanKhau/ThemNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Thêm nhân khẩu");
        stage1.setScene(scene1);
        stage1.showAndWait();
        readDataFromDB();
    }
    public static NhanKhau nhanKhauClick = new NhanKhau();
    @FXML
    void clickAction(MouseEvent event) throws IOException, SQLException {
        if(event.getClickCount() == 2){
            nhanKhauClick = tableView.getSelectionModel().getSelectedItem();
            if(nhanKhauClick.getGhiChu().equals("Tam tru")){
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NhanKhau/ThongTinTamTruView.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage1 = new Stage();
                Scene scene1 = new Scene(parent);
                stage1.setTitle("Thông tin Tạm trú");
                stage1.setScene(scene1);
                stage1.showAndWait();
                readDataFromDB();
            }else if(nhanKhauClick.getGhiChu().equals("Tam vang")){
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NhanKhau/ThongTinTamVangView.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage1 = new Stage();
                Scene scene1 = new Scene(parent);
                stage1.setTitle("Thông tin Tạm vắng");
                stage1.setScene(scene1);
                stage1.showAndWait();
                readDataFromDB();
            }else{
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("NhanKhau/SuaNhanKhauView.fxml"));
                Parent parent = fxmlLoader.load();
                Stage stage1 = new Stage();
                Scene scene1 = new Scene(parent);
                stage1.setTitle("Thông tin chi tiết nhân khẩu");
                stage1.setScene(scene1);
                stage1.showAndWait();
                readDataFromDB();
            }
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setCellTable();
        try {
            readDataFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
