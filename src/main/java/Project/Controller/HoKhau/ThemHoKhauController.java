package Project.Controller.HoKhau;

import Project.Controller.Main;
import Project.DAO.HoKhauDAO;
import Project.DAO.ThanhVienDAO;
import Project.Manager.HoKhauManager;
import Project.Manager.TamTruManager;
import Project.Manager.ThanhVienManager;
import Project.Model.*;
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
    NhanKhau chuHo = new NhanKhau();
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
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ChonNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        ChonNhanKhauController controller = (ChonNhanKhauController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chon chu ho");
        stage1.setScene(scene1);
        stage1.showAndWait();
        chuHo = controller.nhanKhauChon;
        if(chuHo != null){
            for (HoKhau hoKhau: HoKhauManager.List) {
                if(hoKhau.getChuHo().equals(chuHo)){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la chu ho cua 1 ho khac");
                    alert.showAndWait();
                    return;
                }
            }
            for (ThanhVien thanhVien : ThanhVienManager.List) {
                if(thanhVien.getIdNhanKhau() == chuHo.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la thanh vien cua 1 ho khac");
                    alert.showAndWait();
                    return;
                }
            }
            for (ThanhVien thanhVien : listThanhVien) {
                if(thanhVien.getIdNhanKhau() == chuHo.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhân khẩu này đang được chọn làm thành viên");
                    alert.showAndWait();
                    return;
                }
            }
            for (TamTru tamTru : TamTruManager.List){
                if(tamTru.getIdNhanKhau() == chuHo.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhân khẩu này đang ở trạng thái tạm trú nên không chọn được");
                    alert.showAndWait();
                    return;
                }
            }
            inpChuHo.setText(chuHo.getHoTen());
            inpNgaySinh.setText((chuHo.getNgaySinh()).toString());
            inpSoCmtCccd.setText(Integer.toString (chuHo.getSoCMT_CCCD()));
        }
    }

    @FXML
    void themThanhVien(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("HoKhau/ChonNhanKhauView.fxml"));
        Parent parent = fxmlLoader.load();
        ChonNhanKhauController controller = (ChonNhanKhauController) fxmlLoader.getController();
        Stage stage1 = new Stage();
        Scene scene1 = new Scene(parent);
        stage1.setTitle("Chon thanh vien");
        stage1.setScene(scene1);
        stage1.showAndWait();
        NhanKhau nhanKhauChon = controller.getNhanKhauChon();

        if(nhanKhauChon!=null){
            for (TamTru tamTru : TamTruManager.List){
                if(tamTru.getIdNhanKhau() == nhanKhauChon.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhân khẩu này đang ở trạng thái tạm trú nên không chọn được");
                    alert.showAndWait();
                    return;
                }
            }
            for (ThanhVien thanhVien : ThanhVienManager.List) {
                if(thanhVien.getIdNhanKhau() == nhanKhauChon.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la thanh vien cua 1 ho khac");
                    alert.showAndWait();
                    return;
                }
            }
            for (HoKhau hoKhau : HoKhauManager.List) {
                if(hoKhau.getChuHo().getID() == nhanKhauChon.getID()){
                    Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
                    alert.setTitle("Lỗi");
                    alert.setContentText("Sửa lại");
                    alert.setHeaderText("Nhan khau nay dang la chu ho cua 1 ho khac");
                    alert.showAndWait();
                    return;
                }
            }
            for(ThanhVien tv : listThanhVien) {
                if(nhanKhauChon.getID() == tv.getIdNhanKhau()) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setContentText("Bạn đã chọn nhân khẩu này trước đó.");
                    alert.showAndWait();
                    return;
                }
            }
            if(nhanKhauChon.getID() == chuHo.getID()) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Nhân khẩu này đang được chọn làm chủ hộ");
                alert.showAndWait();
                return;
            }
            FXMLLoader fxmlLoader2 = new FXMLLoader(Main.class.getResource("HoKhau/QHeVoiChuHo.fxml"));
            Parent parent2 = fxmlLoader2.load();
            QHeVoiChuHo controller2 = (QHeVoiChuHo) fxmlLoader2.getController();
            Stage stage2 = new Stage();
            Scene scene2 = new Scene(parent2);
            stage2.setTitle("Nhap quan he voi chu ho");
            stage2.setScene(scene2);
            stage2.showAndWait();
            ThanhVien thanhVienTam = new ThanhVien(nhanKhauChon.getID(), HoKhauManager.List.size(), controller2.getQheVsChuHo(), nhanKhauChon.getHoTen(), nhanKhauChon.getNgaySinh());
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
            hoKhauMoi.setID(HoKhauManager.List.size());
            hoKhauMoi.setMaHoKhau(inpMaHoKhau.getText());
            hoKhauMoi.setMaKhuVuc(inpMaKhuVuc.getText());
            hoKhauMoi.setDiaChi(inpDiaChi.getText());
            hoKhauMoi.setChuHo(chuHo);
            hoKhauMoi.setNgayLap(new java.sql.Date(System.currentTimeMillis()));
            hoKhauMoi.setIdChuHo(chuHo.getID());

        }catch (Exception exception){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Sửa lại");
            alert.setHeaderText(exception.getMessage());
            alert.showAndWait();
            return;
        }
        new HoKhauDAO().insert(hoKhauMoi);
        for (ThanhVien thanhVien : listThanhVien) {
            new ThanhVienDAO().insert(thanhVien);
            ThanhVienManager.List.add(thanhVien);
        }
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
        HoKhauManager.List.add(hoKhauMoi);
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
