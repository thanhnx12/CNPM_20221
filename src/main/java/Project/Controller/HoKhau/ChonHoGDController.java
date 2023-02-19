package Project.Controller.HoKhau;

import Project.DAO.DataAccess;
import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;
import Project.Model.HoKhau;
import Project.Model.Phi;
import javafx.beans.property.*;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChonHoGDController implements Initializable {

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private TableColumn<HoKhau, String> colDiaChi;

    @FXML
    private TableColumn<HoKhau, Integer> colID;

    @FXML
    private TableColumn<HoKhau, Integer> colSoThanhVien;

    @FXML
    private TableColumn<HoKhau, String> colTenChuHo;

    @FXML
    private TableView<HoKhau> tableView;

    @FXML
    private TextField txtTimKiem;

    ObservableList<HoKhau> data = FXCollections.observableArrayList();
    NhanKhauManager nhanKhauManager  = new NhanKhauManager();
    @FXML
    void actTimKiem(ActionEvent event) {
        FilteredList<HoKhau> filteredData = new FilteredList<>(data, b->true);

        txtTimKiem.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(hoKhau->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if( nhanKhauManager.get(hoKhau.getIdChuHo()).getHoTen().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else if(hoKhau.getDiaChi().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else return false;
            });
        }));
        SortedList<HoKhau> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
    public static HoKhau hoKhauSelected = new HoKhau();
    @FXML
    void actHuy(ActionEvent event) {
        hoKhauSelected.setID(-1);
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }
    @FXML
    void actXacNhan(ActionEvent event) {
        try{
            hoKhauSelected = tableView.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) txtTimKiem.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            hoKhauSelected.setID(-1);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dữ liệu không hợp lệ");
            alert.setContentText("Vui lòng chọn hộ gia đình");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }

    public void setCellTable(){
        colID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        colTenChuHo.setCellValueFactory(cellData->{
            int idChuHo = cellData.getValue().getIdChuHo();
            NhanKhauManager nhanKhauManager = new NhanKhauManager();
            String tenChuHo =  nhanKhauManager.get(idChuHo).getHoTen();
            return new ReadOnlyStringWrapper(tenChuHo);
        });
        colSoThanhVien.setCellValueFactory(cellData->{
            int soThanhVien = DataAccess.hoKhauDAO.soThanhVien(cellData.getValue());
            return new ReadOnlyObjectWrapper<>(soThanhVien);
        });
    }
    public void readDataFromDB() throws SQLException {
        data = FXCollections.observableArrayList();
        data.addAll(DataAccess.hoKhauDAO.selectAll());
        tableView.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        hoKhauSelected.setID(-1);
        setCellTable();
        try {
            readDataFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
