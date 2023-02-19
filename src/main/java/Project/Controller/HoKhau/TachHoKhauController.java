package Project.Controller.HoKhau;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.ResourceBundle;

import Project.DAO.DataAccess;
import Project.DAO.HoKhauDAO;
import Project.Manager.HoKhauManager;
import Project.Manager.NhanKhauManager;
import Project.Manager.ThanhVienManager;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;
import Project.Model.ThanhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class TachHoKhauController implements Initializable{

    @FXML
    private TextField maHoKhauField;

    @FXML
    private TextField chuHoField;

    @FXML
    private TextField maHoKhauMoiField;

    @FXML
    private TextField chuHoMoiField;

    @FXML
    private TableView<ThanhVien> hoKhauTable;

    @FXML
    private TableColumn<ThanhVien, String> hoTenCol, ngaySinhCol, quanHeChuHoCol;

    @FXML
    private TableView<ThanhVien> hoKhauMoiTable;

    @FXML
    private TableColumn<ThanhVien, String> hoTenMoiCol, ngaySinhMoiCol, quanHeChuHoMoiCol;

    private HoKhau hoKhauChon, hoKhauMoi;
    private NhanKhau chuHo, chuHoMoi;
    private ThanhVien tvChuHoMoi;
    ObservableList<ThanhVien> thanhVienList = FXCollections.observableArrayList();
    ObservableList<ThanhVien> thanhVienMoiList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hoTenCol.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("hoVaTen"));
        ngaySinhCol.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("ngaySinh"));
        quanHeChuHoCol.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("quanHeVoiChuHo"));

        hoTenMoiCol.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("hoVaTen"));
        ngaySinhMoiCol.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("ngaySinh"));
        quanHeChuHoMoiCol.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("quanHeVoiChuHo"));
    }

    public void setup(HoKhau hoKhau) {
        hoKhauChon = hoKhau;
        chuHo = hoKhauChon.getChuHo();
        maHoKhauField.setText(hoKhauChon.getMaHoKhau());
        chuHoField.setText(chuHo.getHoTen());
        for(ThanhVien tv : ThanhVienManager.List) {
            if(tv.getIdHoKhau() == hoKhauChon.getID()) thanhVienList.add(tv);
        }
        hoKhauTable.setItems(thanhVienList);
        hoKhauMoiTable.setItems(thanhVienMoiList);
    }

    public void chonChuHoMoi(ActionEvent event) {
        ThanhVien tvChon = hoKhauMoiTable.getSelectionModel().getSelectedItem();
        try{
            if(tvChon == null){
                throw new Exception("Bạn chưa chọn chủ hộ mới");
            }
        }catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Bạn chưa chọn chủ hộ mới");
            alert.setHeaderText(e.getMessage());
            alert.show();
            return;
        }
        if(tvChuHoMoi != null) thanhVienMoiList.add(tvChuHoMoi);
        tvChuHoMoi = tvChon;
        thanhVienMoiList.remove(tvChon);
        for(NhanKhau nk : NhanKhauManager.nhanKhauList) {
            if(nk.getID() == tvChon.getIdNhanKhau()) chuHoMoi = nk;
        }
        chuHoMoiField.setText(chuHoMoi.getHoTen());
    }

    public void chuyenSangMoi(ActionEvent event) {
        ThanhVien tvChon = hoKhauTable.getSelectionModel().getSelectedItem();
        thanhVienList.remove(tvChon);
        thanhVienMoiList.add(tvChon);
    }

    public void chuyenSangCu(ActionEvent event) {
        ThanhVien tvChon = hoKhauMoiTable.getSelectionModel().getSelectedItem();
        thanhVienMoiList.remove(tvChon);
        thanhVienList.add(tvChon);
    }

    public void xacNhan(ActionEvent event) {
        String maHoKhauMoi = maHoKhauMoiField.getText();
        if(maHoKhauMoi == null || maHoKhauMoi.isBlank()) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Vui long nhap ma ho khau cho ho khau moi");
            alert.show();
            return;
        }
        else if(chuHoMoi == null) {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Vui long chon chu ho cho ho khau moi");
            alert.show();
            return;
        }
        for(HoKhau hk : HoKhauManager.List) {
            if(maHoKhauMoi.strip().equals(hk.getMaHoKhau())) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Ma ho khau bi trung lap voi ho khac");
                alert.show();
                return;
            }
        }
        hoKhauMoi = new HoKhau(HoKhauManager.List.size(), maHoKhauMoi, chuHoMoi.getID(), hoKhauChon.getMaKhuVuc(), hoKhauChon.getDiaChi(), Date.valueOf(LocalDate.now()), chuHoMoi);
        DataAccess.hoKhauDAO.insert(hoKhauMoi);
        HoKhauManager.List.add(hoKhauMoi);
        for(ThanhVien tv : thanhVienMoiList) {
            DataAccess.thanhVienDAO.delete(tv);
            ThanhVienManager.List.remove(tv);
            tv.setIdHoKhau(hoKhauMoi.getID());
            tv.setQuanHeVoiChuHo(null);
            DataAccess.thanhVienDAO.delete(tv);
            ThanhVienManager.List.remove(tv);
        }
        DataAccess.thanhVienDAO.delete(tvChuHoMoi);
        ThanhVienManager.List.remove(tvChuHoMoi);
        huy(event);
    }

    public void huy(ActionEvent event) {
        ((Stage)(((Node) event.getSource()).getScene().getWindow())).close();
    }
}
