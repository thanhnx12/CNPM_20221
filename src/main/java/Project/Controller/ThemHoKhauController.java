package Project.Controller;

import Project.DAO.DAO;
import Project.DAO.HoKhauDAO;
import Project.DAO.ThanhVienDAO;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

public class ThemHoKhauController implements Initializable {
    ObservableList<ThanhVien> listThanhVien = FXCollections.observableArrayList();
    NhanKhau nhanKhauchon = new NhanKhau();
    HoKhau hoKhauMoi = new HoKhau();
    @FXML
    private Button btnChonChuHo;

    @FXML
    private Button btnThemThanhVien;

    @FXML
    private Button btnXoaThanhVien;

    @FXML
    private TableColumn<ThanhVien, String> colHoTen;

    @FXML
    private TableColumn<ThanhVien, Date> colNgaySinh;

    @FXML
    private TableColumn<ThanhVien, String> colQuanHe;

    @FXML
    private TextField inpChuHo;

    @FXML
    private TextField inpDiaChi;

    @FXML
    private TextField inpMaHoKhau;

    @FXML
    private TextField inpMaKhuVuc;

    @FXML
    private TextField inpNgaySinh;

    @FXML
    private TextField inpSoCmtCccd;

    @FXML
    private TableView<ThanhVien> tableThanhVien;

    @FXML
    void huy(ActionEvent event) {
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    @FXML
    void themChuHo(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ChonNhanKhau.fxml"));
        Parent parent = fxmlLoader.load();
        ChonNhanKhauController controller = (ChonNhanKhauController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chon chu ho");
        stage1.setScene(scene1);
        stage1.showAndWait();
        nhanKhauchon = controller.nhanKhauChon;
        if(nhanKhauchon != null){
            for (HoKhau hoKhau: HoKhauManager.listHoKhau) {
                if(hoKhau.getChuHo().equals(nhanKhauchon)){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la chu ho cua 1 ho khac");
                    alert.show();
                    return;
                }
            }
            inpChuHo.setText(nhanKhauchon.getHoTen());
            inpNgaySinh.setText((nhanKhauchon.getNgaySinh()).toString());
            inpSoCmtCccd.setText(Integer.toString (nhanKhauchon.getSoCMT_CCCD()));
        }
    }

    @FXML
    void themThanhVien(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("ChonNhanKhau.fxml"));
        Parent parent = fxmlLoader.load();
        ChonNhanKhauController controller = (ChonNhanKhauController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chon thanh vien");
        stage1.setScene(scene1);
        stage1.showAndWait();
        NhanKhau nhanKhauChon = controller.getNhanKhauChon();

        if(nhanKhauChon!=null){
            for (ThanhVien thanhVien : ThanhVienManager.List) {
                if(thanhVien.getIdNhanKhau() == nhanKhauChon.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la thanh vien cua 1 ho khac");
                    alert.show();
                    return;
                }
            }
            for (HoKhau hoKhau : HoKhauManager.listHoKhau) {
                if(hoKhau.getChuHo().getID() == nhanKhauChon.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la chu ho cua 1 ho khac");
                    alert.show();
                    return;
                }
            }
            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("QHeVoiChuHo.fxml"));
            Parent parent2 = fxmlLoader2.load();
            QHeVoiChuHo controller2 = (QHeVoiChuHo) fxmlLoader2.getController();
            Stage stage2 = new Stage();
            Scene scene2 = new Scene(parent2);
            stage2.setTitle("Nhap quan he voi chu ho");
            stage2.setScene(scene2);
            stage2.showAndWait();
            ThanhVien thanhVienTam = new ThanhVien(controller2.getQheVsChuHo(), nhanKhauChon.getHoTen(), nhanKhauChon.getNgaySinh());
            thanhVienTam.setIdHoKhau(HoKhauManager.listHoKhau.size());
            thanhVienTam.setIdNhanKhau(nhanKhauChon.getID());
            listThanhVien.add(thanhVienTam);
        }
    }

    @FXML
    void xacnhan(ActionEvent event) {
        System.out.println("xacnhan");
        boolean loi = false;
        try{
            if(inpMaHoKhau.getText()==""||inpMaHoKhau.getText()==""||inpMaKhuVuc.getText()==""){
                loi = true;
                throw new Exception("Ban nhap khong day du cac gia tri");
            }
            hoKhauMoi.setID(HoKhauManager.listHoKhau.size());
            hoKhauMoi.setMaHoKhau(inpMaHoKhau.getText());
            hoKhauMoi.setMaKhuVuc(inpMaKhuVuc.getText());
            hoKhauMoi.setDiaChi(inpDiaChi.getText());
            hoKhauMoi.setChuHo(nhanKhauchon);
            hoKhauMoi.setNgayLap(new java.sql.Date(System.currentTimeMillis()));
            hoKhauMoi.setIdChuHo(nhanKhauchon.getID());

        }catch (Exception exception){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Sửa lại");
            alert.setHeaderText(exception.getMessage());
            alert.show();
            return;
        }
        new HoKhauDAO().insert(hoKhauMoi);
        for (ThanhVien thanhVien : listThanhVien) {
            new ThanhVienDAO().insert(thanhVien);
            ThanhVienManager.List.add(thanhVien);
        }
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
        HoKhauManager.listHoKhau.add(hoKhauMoi);
    }

    @FXML
    void xoaNhanKhau(ActionEvent event) {
        ThanhVien thanhVienXoa = tableThanhVien.getSelectionModel().getSelectedItem();
        listThanhVien.remove(thanhVienXoa);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colHoTen.setCellValueFactory(new PropertyValueFactory<ThanhVien,String>("hoVaTen"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<ThanhVien,Date>("ngaySinh"));
        colQuanHe.setCellValueFactory(new PropertyValueFactory<ThanhVien,String>("quanHeVoiChuHo"));
        tableThanhVien.setItems(listThanhVien);
    }
}
