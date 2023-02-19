package Project.Controller.HoKhau;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import Project.Controller.Main;
import Project.DAO.DataAccess;
import Project.Manager.HoKhauManager;
import Project.Manager.ThanhVienManager;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;
import Project.Model.ThanhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;

public class SuaHoKhauController implements Initializable{
    @FXML
    private TextField maHoKhauField;

    @FXML
    private TextField maKhuVucField;

    @FXML
    private TextField diaChiField;

    @FXML
    private TextField chuHoField;

    @FXML
    private TextField ngaySinhChuHoField;

    @FXML
    private TextField cmtChuHoField;

    @FXML
    private TableView<ThanhVien> thanhVienTable;

    @FXML
    private TableColumn<ThanhVien, String> tvHoTenColumn, tvNgaySinhColumn, tvQuanHeChuHoColumn;

    ObservableList<ThanhVien> thanhVienList = FXCollections.observableArrayList();
    ObservableList<ThanhVien> themThanhVienList = FXCollections.observableArrayList();
    ObservableList<ThanhVien> xoaThanhVienList = FXCollections.observableArrayList();
    HoKhau hoKhauChon;
    NhanKhau chuHo;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tvHoTenColumn.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("hoVaTen"));
        tvNgaySinhColumn.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("ngaySinh"));
        tvQuanHeChuHoColumn.setCellValueFactory(new PropertyValueFactory<ThanhVien, String>("quanHeVoiChuHo"));
    }

    public void setup(HoKhau hoKhau) {
        hoKhauChon = hoKhau;
        chuHo = hoKhauChon.getChuHo();
        maHoKhauField.setText(hoKhauChon.getMaHoKhau());
        maKhuVucField.setText(hoKhauChon.getMaKhuVuc());
        diaChiField.setText(hoKhauChon.getDiaChi());
        chuHoField.setText(chuHo.getHoTen());
        ngaySinhChuHoField.setText(new SimpleDateFormat("dd.MM.yyyy").format(chuHo.getNgaySinh()));
        cmtChuHoField.setText(Integer.toString(chuHo.getSoCMT_CCCD()));
        for(ThanhVien tv : ThanhVienManager.List) {
            if(tv.getIdHoKhau() == hoKhauChon.getID()) {
                thanhVienList.add(tv);
            }
        }
        thanhVienTable.setItems(thanhVienList);
    }

    public void chonChuHo(ActionEvent event) {
//        ThanhVien chuHoChon = thanhVienTable.getSelectionModel().getSelectedItem();
//        chuHo = NhanKhauManager.nhanKhauList.get(chuHoChon.getIdNhanKhau());
//        chuHoField.setText(chuHo.getHoTen());
//        ngaySinhChuHoField.setText(new SimpleDateFormat("dd.MM.yyyy").format(chuHo.getNgaySinh()));
//        cmtChuHoField.setText(Integer.toString(chuHo.getSoCMT_CCCD()));
//        xoaTV(chuHoChon);
    }

    public void themThanhVien(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("HoKhau/ChonNhanKhauView.fxml"));
        Parent root = loader.load();
        ChonNhanKhauController chonNhanKhau = loader.getController();
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        NhanKhau nhanKhauThem = chonNhanKhau.getNhanKhauChon();
        System.out.println("them nua");
        for(ThanhVien tv : ThanhVienManager.List) {
            if(tv.getIdNhanKhau() == nhanKhauThem.getID()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Nhân khẩu này đã nằm trong một hộ.");
                alert.show();
                return;
            }
        }
        for(HoKhau hk : HoKhauManager.List) {
            if(hk.getIdChuHo() == nhanKhauThem.getID()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Nhân khẩu này đã nằm trong một hộ.");
                alert.show();
                return;
            }
        }
        for(ThanhVien tv : themThanhVienList) {
            if(nhanKhauThem.getID() == tv.getIdNhanKhau()) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Bạn đã chọn nhân khẩu này trước đó.");
                alert.show();
                return;
            }
        }
        loader = new FXMLLoader(Main.class.getResource("HoKhau/QHeVoiChuHo.fxml"));
        root = loader.load();
        QHeVoiChuHo chonQhe = (QHeVoiChuHo) loader.getController();
        scene = new Scene(root);
        stage = new Stage();
        stage.setScene(scene);
        stage.showAndWait();
        String quanHe = chonQhe.getQheVsChuHo();
        ThanhVien thanhVienThem = new ThanhVien(nhanKhauThem.getID(), hoKhauChon.getID(), quanHe, nhanKhauThem.getHoTen(), nhanKhauThem.getNgaySinh());
        themThanhVienList.add(thanhVienThem);
        if(xoaThanhVienList.contains(thanhVienThem)) xoaThanhVienList.remove(thanhVienThem);
        thanhVienList.add(thanhVienThem);
    }

    public void xoaThanhVien(ActionEvent event) {
        ThanhVien thanhVienXoa = thanhVienTable.getSelectionModel().getSelectedItem();
        xoaTV(thanhVienXoa);
    }

    public void xoaTV(ThanhVien thanhVienXoa) {
        xoaThanhVienList.add(thanhVienXoa);
        if(themThanhVienList.contains(thanhVienXoa)) themThanhVienList.remove(thanhVienXoa);
        thanhVienList.remove(thanhVienXoa);
    }

    public void luu(ActionEvent event) {
        hoKhauChon.setMaKhuVuc(maKhuVucField.getText());
        hoKhauChon.setDiaChi(diaChiField.getText());
        hoKhauChon.setChuHo(chuHo);
        hoKhauChon.setIdChuHo(chuHo.getID());
        DataAccess.hoKhauDAO.update(hoKhauChon);
        for(ThanhVien tv : themThanhVienList) {
            DataAccess.thanhVienDAO.insert(tv);
            ThanhVienManager.List.add(tv);
        }
        for(ThanhVien tv : xoaThanhVienList) {
            DataAccess.thanhVienDAO.delete(tv);
            if(ThanhVienManager.List.contains(tv)) ThanhVienManager.List.remove(tv);
        }
        huy(event);
    }

    public void huy(ActionEvent event) {
        ((Stage)(((Node) event.getSource()).getScene().getWindow())).close();
    }
}