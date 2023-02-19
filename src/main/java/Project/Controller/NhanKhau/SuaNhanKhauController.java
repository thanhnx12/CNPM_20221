package Project.Controller.NhanKhau;

import Project.Controller.Main;
import Project.Controller.NhanKhau.NhanKhauController;
import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SuaNhanKhauController implements Initializable {

    @FXML
    private Button btnXoaNhanKhau;

    @FXML
    private TextField danToc;

    @FXML
    private DatePicker dateNgaySinh;

    @FXML
    private TextField diaChiHienTai;

    @FXML
    private TextField dienThoai;

    @FXML
    private TextArea ghiChu;

    @FXML
    private TextField gioiTinh;

    @FXML
    private TextField hoChieuSo;

    @FXML
    private TextField hoVaTen;

    @FXML
    private Button huy;

    @FXML
    private TextField nguyenQuan;

    @FXML
    private TextField noiSinh;

    @FXML
    private TextField noiThuongTru;

    @FXML
    private TextField quocTich;

    @FXML
    private TextField soCMT_CCCD;

    @FXML
    private TextField tonGiao;

    @FXML
    private Button xacNhan;

    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) huy.getScene().getWindow();
        stage.close();
    }
    NhanKhau nhanKhau = new NhanKhau();
    @FXML
    void actXacNhan(ActionEvent event) {
        if(checkValid()){
            nhanKhau.setIdNguoiTao(Main.user.getID());
            nhanKhau.setHoTen(hoVaTen.getText());
            nhanKhau.setGhiChu(ghiChu.getText());
            nhanKhau.setDienThoai(dienThoai.getText());
            nhanKhau.setDiaChiHienTai(diaChiHienTai.getText());
            nhanKhau.setNgayTao(java.sql.Date.valueOf(java.time.LocalDate.now()));
            nhanKhau.setTonGiao(tonGiao.getText());
            nhanKhau.setNoiThuongTru(noiThuongTru.getText());
            nhanKhau.setSoHoChieu(hoChieuSo.getText());
            nhanKhau.setSoCMT_CCCD(Integer.parseInt(soCMT_CCCD.getText()));
            nhanKhau.setQuocTich(quocTich.getText());
            nhanKhau.setNguyenQuan(nguyenQuan.getText());
            nhanKhau.setGioiTinh(gioiTinh.getText());
            nhanKhau.setNoiSinh(noiSinh.getText());
            nhanKhau.setNgaySinh(java.sql.Date.valueOf(dateNgaySinh.getValue()));
            nhanKhau.setDanToc(danToc.getText());
            nhanKhau.setID(NhanKhauController.nhanKhauClick.getID());
            DataAccess.nhanKhauDAO.update(nhanKhau);
            for(NhanKhau x : NhanKhauManager.nhanKhauList){
                if(x.getID() == nhanKhau.getID()) {
                    NhanKhauManager.nhanKhauList.remove(x);
                    NhanKhauManager.nhanKhauList.add(nhanKhau);
                    break;
                }
            }

            Stage stage = (Stage) xacNhan.getScene().getWindow();
            stage.close();
        }else{
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Lỗi nhập thiếu");
            alert.setContentText("Vui lòng nhập đầy đủ thông tin");
            ButtonType isOK = new ButtonType("OK");
            alert.getButtonTypes().setAll(isOK);
            Optional<ButtonType> Result = alert.showAndWait();
            if (Result.get() == isOK) {
                alert.close();
            }
        }

    }

    @FXML
    void actXoaNhanKhau(ActionEvent event) {


        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Xóa nhân khẩu");
        alert.setHeaderText("Bạn có chắc chắn muốn xóa ?");
//        alert.setContentText("C:/MyFile.txt");

        // option != null.
        Optional<ButtonType> option = alert.showAndWait();

        if (option.get() == null) {

        } else if (option.get() == ButtonType.OK) {
            for(NhanKhau x : NhanKhauManager.nhanKhauList){
                if(x.getID() == NhanKhauController.nhanKhauClick.getID()){
                    x.setGhiChu("Đã xóa !");
                    DataAccess.nhanKhauDAO.update(x);
                    break;
                }
            }
            alert.close();
            Stage stage = (Stage) huy.getScene().getWindow();
            stage.close();
        } else if (option.get() == ButtonType.CANCEL) {
            alert.close();
        } else {
            alert.close();
        }
    }


    @FXML
    void txtNoiSinh(ActionEvent event) {

    }
    boolean checkValid(){
        if(hoVaTen.getText().isEmpty()){
            return false;
        }else if(dateNgaySinh.getValue() == null){
            return false;
        }else if(nguyenQuan.getText().isEmpty()){
            return false;
        }else if(soCMT_CCCD.getText().isEmpty()){
            return false;
        }else if(noiThuongTru.getText().isEmpty()){
            return false;
        }else if(noiSinh.getText().isEmpty()){
            return false;
        }else if(gioiTinh.getText().isEmpty()){
            return false;
        }else if(tonGiao.getText().isEmpty()){
            return false;
        }else if(quocTich.getText().isEmpty()){
            return false;
        }else if(diaChiHienTai.getText().isEmpty()){
            return false;
        }else{
            return true;
        }

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NhanKhau a = new NhanKhau();
        a = NhanKhauController.nhanKhauClick;
        try{
            hoVaTen.setText(a.getHoTen() + "");
            if(a.getNgaySinh() != null){
                dateNgaySinh.setValue(a.getNgaySinh().toLocalDate());
            }
            danToc.setText(a.getDanToc() + "");
            nguyenQuan.setText(a.getNguyenQuan() + "");
            soCMT_CCCD.setText(a.getSoCMT_CCCD() + "");
            noiThuongTru.setText(a.getNoiThuongTru() + "");
            noiSinh.setText(a.getNoiSinh() + "");
            ghiChu.setText(a.getGhiChu()+ "");
            gioiTinh.setText(a.getGioiTinh() + "");
            tonGiao.setText(a.getTonGiao() + "");
            quocTich.setText(a.getQuocTich() + "");
            hoChieuSo.setText(a.getSoHoChieu() + "");
            diaChiHienTai.setText(a.getDiaChiHienTai() + "");
            dienThoai.setText(a.getDienThoai() + "");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
