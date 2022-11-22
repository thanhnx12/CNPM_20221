package Project.Controller;

import Project.DAO.DataAccess;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
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
    @FXML
    void actSearch(ActionEvent event) {

    }

    public void setCellTable(){
        colChuHo.setCellValueFactory(cellData->{
            int idHoKhau = cellData.getValue().getIdHoKhau();
            HoKhauManager hoKhauManager = new HoKhauManager();
            HoKhau hoKhau = hoKhauManager.get(idHoKhau);
            int idChuHo = hoKhau.getIdChuHo();
            NhanKhauManager nhanKhauManager = new NhanKhauManager();
            NhanKhau chuHo = nhanKhauManager.get(idChuHo);
            return new ReadOnlyStringWrapper(chuHo.getHoTen());
        });
        colKhoanThu.setCellValueFactory(cellData->{
            int idPhi = cellData.getValue().getIdPhi();
            PhiManager phiManager = new PhiManager();
            Phi phi = phiManager.get(idPhi);
            return new ReadOnlyStringWrapper(phi.getTenLoaiPhi());
        });
        colLoaiKhoanThu.setCellValueFactory(cellData->{
            int idPhi = cellData.getValue().getIdPhi();
            PhiManager phiManager = new PhiManager();
            Phi phi = phiManager.get(idPhi);
            String res;
            if(phi.isTuNguyen()) res = "Tự nguyện";
            else res = "Bắt buộc";
            return new ReadOnlyStringWrapper(res);
        });
        colCanNop.setCellValueFactory(new PropertyValueFactory<>("canNop"));
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
