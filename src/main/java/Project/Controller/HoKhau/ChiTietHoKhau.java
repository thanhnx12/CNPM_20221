package Project.Controller.HoKhau;


import Project.Manager.ThanhVienManager;
import Project.Model.HoKhau;
import Project.Model.NhanKhau;
import Project.Model.ThanhVien;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class ChiTietHoKhau implements Initializable {
    private ObservableList<ThanhVien> listThanhVien = FXCollections.observableArrayList();
    private HoKhau hoKhauChon;
    private NhanKhau chuHo;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        colHoTen.setCellValueFactory(new PropertyValueFactory<ThanhVien,String>("hoVaTen"));
        colNgaySinh.setCellValueFactory(new PropertyValueFactory<ThanhVien,Date>("ngaySinh"));
        colQuanHe.setCellValueFactory(new PropertyValueFactory<ThanhVien,String>("quanHeVoiChuHo"));
    }
    public void dong(ActionEvent actionEvent) {
        ((Stage)(((Node)actionEvent.getSource()).getScene().getWindow())).close();
    }

    public void setup(HoKhau hokhau) {
        this.hoKhauChon = hokhau;
        chuHo = hoKhauChon.getChuHo();
        inpMaHoKhau.setText(hoKhauChon.getMaHoKhau());
        inpMaKhuVuc.setText(hoKhauChon.getMaKhuVuc());
        inpDiaChi.setText(hoKhauChon.getDiaChi());
        inpChuHo.setText(chuHo.getHoTen());
        inpNgaySinh.setText(new SimpleDateFormat("dd.MM.yyyy").format(chuHo.getNgaySinh()));
        inpSoCmtCccd.setText(Integer.toString(chuHo.getSoCMT_CCCD()));
        for(ThanhVien tv : ThanhVienManager.List) {
            if(tv.getIdHoKhau() == hoKhauChon.getID()) {
                listThanhVien.add(tv);
            }
        }
        tableThanhVien.setItems(listThanhVien);
    }
}
