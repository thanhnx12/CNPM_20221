package Project.Controller.Phi;

import Project.DAO.DataAccess;
import Project.DAO.PhiDAO;
import Project.Manager.PhiManager;
import Project.Model.Phi;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class TaoLoaiPhiMoiController implements Initializable {

    @FXML
    private RadioButton batBuoc;

    @FXML
    private Button btnHuy;

    @FXML
    private Button btnXacNhan;

    @FXML
    private DatePicker dateHanNop;

    @FXML
    private MenuItem menuItem1;

    @FXML
    private MenuItem menuItem2;

    @FXML
    private MenuButton menuTien;

    @FXML
    private TextField txtSoTien;

    @FXML
    private TextField txtTenLoaiPhi;
    Phi phi = new Phi(DataAccess.phiDAO.getNewID(), "No name", true, true, 0.0,java.sql.Date.valueOf("2030-05-05"));
    @FXML
    void actBatBuoc(ActionEvent event) {
        if(batBuoc.isSelected()){
            txtSoTien.setEditable(true);
        }else{
            txtSoTien.setText("0.0");
            txtSoTien.setEditable(false);
        }
    }

    @FXML
    void actHuy(ActionEvent event) {
        Stage stage = (Stage) btnHuy.getScene().getWindow();
        stage.close();
    }

    @FXML
    void actXacNhan(ActionEvent event) {
        try{
            phi.setHanNop(java.sql.Date.valueOf(dateHanNop.getValue()));
            phi.setSoTien(Double.valueOf(txtSoTien.getText()));
            phi.setTenLoaiPhi(txtTenLoaiPhi.getText());
            if(batBuoc.isSelected()){
                phi.setTuNguyen(false);
            }else{
                phi.setTuNguyen(true);
                phi.setSoTien(0.0);
            }
            DataAccess.phiDAO.insert(phi);
            PhiManager.List.add(phi);
            Stage stage = (Stage) txtSoTien.getScene().getWindow();
            stage.close();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Dữ liệu không hợp lệ");
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
    void actmenuItem1(ActionEvent event) {
        menuTien.setText(menuItem1.getText());
        phi.setThuTheoHo(false);
    }

    @FXML
    void actmenuItem2(ActionEvent event) {
        menuTien.setText(menuItem2.getText());
        phi.setThuTheoHo(true);
    }
    boolean checkValid(){
//        if(txtSoTien.getText())
        return false;
    }

//    public static void main(String[] args) {
//        System.out.println(Double.valueOf("ahah"));
//    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Phi phi = new Phi(DataAccess.phiDAO.getNewID(), "No name", true, true, 0.0,java.sql.Date.valueOf("2030-05-05"));
    }
}
