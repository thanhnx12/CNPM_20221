package Project.Controller.Phi;

import Project.DAO.DataAccess;
import Project.Model.Phi;
import javafx.beans.property.ReadOnlyStringWrapper;
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
import java.sql.Date;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

public class ChonKhoanThuController implements Initializable {

    @FXML
    private TableColumn<Phi, Date> colHanNop;

    @FXML
    private TableColumn<Phi, String> colKieuThu;

    @FXML
    private TableColumn<Phi, String> colLoaiKhoanThu;

    @FXML
    private TableColumn<Phi, Double> colSoTien;

    @FXML
    private TableColumn<Phi, String> colTenKhoanThu;

    @FXML
    private TableView<Phi> tableView;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    ObservableList<Phi> data = FXCollections.observableArrayList();
    @FXML
    private TextField txtSearch;

    @FXML
    void actSearch(ActionEvent event) {
        FilteredList<Phi> filteredData = new FilteredList<>(data, b->true);

        txtSearch.textProperty().addListener(((observableValue, oldValue, newValue) -> {
            filteredData.setPredicate(Phi->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if( Phi.getTenLoaiPhi().toLowerCase().indexOf(lowerCaseFilter)!= -1){
                    return true;
                }else return false;
            });
        }));
        SortedList<Phi> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedData);
    }
    public static Phi phiSelected = new Phi();
    @FXML
    void actHuy(ActionEvent event) {
        phiSelected.setID(-1);
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }
    @FXML
    void actXacNhan(ActionEvent event) {
        try{
            phiSelected = tableView.getSelectionModel().getSelectedItem();
            Stage stage = (Stage) txtSearch.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            phiSelected.setID(-1);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dữ liệu không hợp lệ");
            alert.setContentText("Vui lòng chọn khoản thu");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }
    }

    public void setCellTable(){
        colTenKhoanThu.setCellValueFactory(new PropertyValueFactory<>("tenLoaiPhi"));
        colHanNop.setCellValueFactory(new PropertyValueFactory<>("hanNop"));
        colSoTien.setCellValueFactory(new PropertyValueFactory<>("soTien"));
        colLoaiKhoanThu.setCellValueFactory(cellData->{
            boolean loaiPhi = cellData.getValue().isTuNguyen();
            String loaiPhiAsString;
            if(loaiPhi){
                loaiPhiAsString = "Tự nguyện";
            }else loaiPhiAsString = "Bắt buộc";
            return new ReadOnlyStringWrapper(loaiPhiAsString);
        });
        colKieuThu.setCellValueFactory(cellData->{
            boolean kieuThu = cellData.getValue().isThuTheoHo();
            String kieuThuAsString;
            if(kieuThu){
                kieuThuAsString = "Theo hộ";
            }else kieuThuAsString = "Theo người";
            return new ReadOnlyStringWrapper(kieuThuAsString);
        });
    }
    public void readDataFromDB() throws SQLException {
        data = FXCollections.observableArrayList();
        data.addAll(DataAccess.phiDAO.selectAll());
        tableView.setItems(data);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        phiSelected.setID(-1);
        setCellTable();
        try {
            readDataFromDB();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
