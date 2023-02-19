package Project.Controller.Phi;

import Project.Controller.Main;
import Project.DAO.DataAccess;
import Project.Manager.PhiManager;
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

public class KhoanThuController implements Initializable {

    @FXML
    private Button btnSua;

    @FXML
    private Button btnThem;

    @FXML
    private Button btnXoa;

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


    ObservableList<Phi> data = FXCollections.observableArrayList();
    @FXML
    private TextField txtSearch;

    @FXML
    void actSearch(KeyEvent event) {
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
    void actSua(ActionEvent event) throws IOException, SQLException {
        try{
            phiSelected = tableView.getSelectionModel().getSelectedItem();
            System.out.println(phiSelected.getTenLoaiPhi());
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Phi/SuaThongTinPhi.fxml"));
            Parent parent = fxmlLoader.load();
            Stage stage1 = new Stage();
            Scene scene1 = new Scene(parent);
            stage1.setTitle("Thêm loại phí");
            stage1.setScene(scene1);
            stage1.showAndWait();
            readDataFromDB();
        }catch (Exception e){

        }

    }

    @FXML
    void actThem(ActionEvent event) throws IOException, SQLException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("Phi/TaoLoaiPhiMoi.fxml"));
        Parent parent = fxmlLoader.load();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Thêm loại phí");
        stage1.setScene(scene1);
        stage1.showAndWait();
        readDataFromDB();
    }

    @FXML
    void actXoa(ActionEvent event) {
        try{
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Xóa khoản thu");
            alert.setHeaderText("Bạn có chắc chắn muốn xóa ?");
            Optional<ButtonType> option = alert.showAndWait();
            if (option.get() == null) {
            } else if (option.get() == ButtonType.OK) {
                Phi selected = tableView.getSelectionModel().getSelectedItem();
                DataAccess.phiDAO.delete(selected);
                PhiManager.List.clear();
                PhiManager.List = DataAccess.phiDAO.selectAll();
                alert.close();
                readDataFromDB();
            } else if (option.get() == ButtonType.CANCEL) {
                alert.close();
            } else {
                alert.close();
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
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
