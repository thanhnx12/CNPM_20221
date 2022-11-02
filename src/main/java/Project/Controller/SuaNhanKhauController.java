package Project.Controller;

import Project.DAO.DataAccess;
import Project.Manager.NhanKhauManager;
import Project.Model.NhanKhau;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.time.LocalDate;
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

    @FXML
    void actXacNhan(ActionEvent event) {

    }

    @FXML
    void actXoaNhanKhau(ActionEvent event) {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("Xác nhận xóa nhân khẩu");
//        alert.setContentText("Bạn có chắc chắn muốn xóa không");
//        ButtonType okButton = new ButtonType("Yes", ButtonBar.ButtonData.YES);
//        ButtonType cancelButton = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
//        alert.getButtonTypes().setAll(okButton, cancelButton);
//        alert.showAndWait().ifPresent(type -> {
//            if (type == ButtonType.YES) {
//                for(NhanKhau x : NhanKhauManager.nhanKhauList){
//                    if(x.getID() == NhanKhauController.nhanKhauClick.getID()){
//                        NhanKhauManager.nhanKhauList.remove(x);
//                        break;
//                    }
//                }
//                DataAccess.nhanKhauDAO.delete(NhanKhauController.nhanKhauClick);
//                alert.close();
//                Stage stage = (Stage) huy.getScene().getWindow();
//                stage.close();
//            } else {
//                alert.close();
//            }
//        });

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

        return false;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        NhanKhau a = new NhanKhau();
        a = NhanKhauController.nhanKhauClick;
        try{
            hoVaTen.setText(a.getHoTen() + " ");
            if(a.getNgaySinh() != null){
                dateNgaySinh.setValue(a.getNgaySinh().toLocalDate());
            }
            danToc.setText(a.getDanToc() + " ");
            nguyenQuan.setText(a.getNguyenQuan() + " ");
            soCMT_CCCD.setText(a.getSoCMT_CCCD() + " ");
            noiThuongTru.setText(a.getNoiThuongTru() + " ");
            noiSinh.setText(a.getNoiSinh() + " ");
            ghiChu.setText(a.getGhiChu()+ " ");
            gioiTinh.setText(a.getGioiTinh() + " ");
            tonGiao.setText(a.getTonGiao() + " ");
            quocTich.setText(a.getQuocTich() + " ");
            hoChieuSo.setText(a.getSoHoChieu() + " ");
            diaChiHienTai.setText(a.getDiaChiHienTai() + " ");
            dienThoai.setText(a.getDienThoai() + " ");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }



    }
}
