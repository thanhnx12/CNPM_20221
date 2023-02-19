package Project.Controller.HoKhau;

import java.net.URL;
import java.util.ResourceBundle;

import Project.Manager.NhanKhauManager;
import Project.Model.NhanKhau;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ChonNhanKhauController implements Initializable{

    @FXML
    private TextField timKiemField;

    @FXML
    private TableView<NhanKhau> tableNhanKhau;

    @FXML
    private TableColumn<NhanKhau, String> colID;

    @FXML
    private TableColumn<NhanKhau, String> colHoTen;

    @FXML
    private TableColumn<NhanKhau, String> colCCCD;

    @FXML
    private TableColumn<NhanKhau, String> colDiaChi;

    ObservableList<NhanKhau> nhanKhauList = FXCollections.observableArrayList();
    public static NhanKhau nhanKhauChon;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //setup cac cot
        colID.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ID"));
        colHoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        colCCCD.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("soCMT_CCCD"));
        colDiaChi.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("diaChiHienTai"));

        //lay du lieu
        nhanKhauList.addAll(NhanKhauManager.nhanKhauList);
        tableNhanKhau.setItems(nhanKhauList);
    }

    public void huy(ActionEvent event) {
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

    public void xacNhan(ActionEvent event) {
        nhanKhauChon = tableNhanKhau.getSelectionModel().getSelectedItem();
        huy(event);
    }

    public void timKiem(ActionEvent event) {
        nhanKhauList.clear();
        if(timKiemField.getText().isBlank())
            nhanKhauList.addAll(NhanKhauManager.nhanKhauList);
        else {
            for(NhanKhau nk : NhanKhauManager.nhanKhauList) {
                if(Integer.toString(nk.getID()).equals(timKiemField.getText()))
                    nhanKhauList.add(nk);
            }
        }
        tableNhanKhau.setItems(nhanKhauList);
    }

    public NhanKhau getNhanKhauChon() {
        return nhanKhauChon;
    }

}
