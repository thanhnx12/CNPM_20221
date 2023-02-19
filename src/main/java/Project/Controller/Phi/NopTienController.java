package Project.Controller.Phi;

import Project.Controller.Main;
import Project.DAO.DaNopDAO;
import Project.DAO.DataAccess;
import Project.DAO.NhanKhauDAO;
import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;
import Project.Manager.PhiManager;
import Project.Model.DaNop;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;
import Project.Model.Phi;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class NopTienController implements Initializable {

    @FXML
    private Button btnSua;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnXoa;

    @FXML
    private TableColumn<DaNop, Double> colCanNop;

    @FXML
    private TableColumn<DaNop, String> colChuHo;

    @FXML
    private TableColumn<DaNop, Double> colDaNop;

    @FXML
    private TableColumn<DaNop, String> colKhoanThu;

    @FXML
    private TableColumn<DaNop, String> colLoaiKhoanThu;

    @FXML
    private TableColumn<DaNop, Date> colNgayNop;

    @FXML
    private TableView<DaNop> tableView;

    @FXML
    private TextField txtSearch;
    ObservableList<DaNop> data = FXCollections.observableArrayList();
    NhanKhauDAO nhanKhauDAO = new NhanKhauDAO();
    PhiManager phiManager = new PhiManager();

    @FXML
    void actSearch(KeyEvent event) {
        FilteredList<DaNop> filteredData = new FilteredList<>(data, b->true);

        txtSearch.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(DaNop->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if((phiManager.get(DaNop.getIdPhi())+"").toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else return false;
            });
        }));
        SortedList<DaNop> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
    @FXML
    void actSua(ActionEvent event) {

    }

    @FXML
    void actThem(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Phi/ThemNopTien.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Nộp tiền");
        stage1.setScene(scene1);
        stage1.showAndWait();
        readDataFromDB();
    }

    @FXML
    void actXoa(ActionEvent event) {
        try{
            DaNop daNop = tableView.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setContentText("Bạn có chắc chắn muốn xóa ?");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
                new DaNopDAO().delete(daNop);
                readDataFromDB();
            }

        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Thông báo");
            alert.setContentText("Có lỗi xảy ra !");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }
    public void setCellTable(){
        colChuHo.setCellValueFactory(cellData->{
            int idHoKhau = cellData.getValue().getIdHoKhau();
            try{
                HoKhauManager hoKhauManager = new HoKhauManager();
                HoKhau hoKhau = hoKhauManager.get(idHoKhau);
                int idChuHo = hoKhau.getIdChuHo();
                NhanKhauManager nhanKhauManager = new NhanKhauManager();
                NhanKhau chuHo = nhanKhauManager.get(idChuHo);
                return new ReadOnlyStringWrapper(chuHo.getHoTen());
            }catch (Exception e){
                return new ReadOnlyStringWrapper("Đã xóa hộ khẩu này");
            }
        });
        colKhoanThu.setCellValueFactory(cellData->{
            int idPhi = cellData.getValue().getIdPhi();
            try{
                PhiManager phiManager = new PhiManager();
                Phi phi = phiManager.get(idPhi);
                return new ReadOnlyStringWrapper(phi.getTenLoaiPhi());
            }catch (Exception e){
                return new ReadOnlyStringWrapper("Đã xóa khoản thu này");
            }
        });
        colLoaiKhoanThu.setCellValueFactory(cellData->{
            int idPhi = cellData.getValue().getIdPhi();
            try{
                PhiManager phiManager = new PhiManager();
                Phi phi = phiManager.get(idPhi);
                String res;
                if(phi.isTuNguyen()) res = "Tự nguyện";
                else res = "Bắt buộc";
                return new ReadOnlyStringWrapper(res);
            }catch (Exception e){
                return new ReadOnlyStringWrapper("null");
            }
        });
        colDaNop.setCellValueFactory(new PropertyValueFactory<>("soTien"));
        colNgayNop.setCellValueFactory(new PropertyValueFactory<>("ngayNop"));
    }
    public void readDataFromDB() throws SQLException {
        data = FXCollections.observableArrayList();
        data.addAll(DataAccess.daNopDAO.selectAll());
        tableView.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellTable();
        try {
            readDataFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
