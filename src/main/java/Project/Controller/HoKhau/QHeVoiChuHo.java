package Project.Controller.HoKhau;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class QHeVoiChuHo {
    private String qheVsChuHo;
    @FXML
    private Button btnHuy;

    public String getQheVsChuHo() {
        return qheVsChuHo;
    }

    public void setQheVsChuHo(String qheVsChuHo) {
        this.qheVsChuHo = qheVsChuHo;
    }

    @FXML
    private Button btnXacNhan;

    @FXML
    private TextField inpQhe;

    @FXML
    void huy(ActionEvent event) {
        System.out.println("huy");
    }

    @FXML
    void xacnhan(ActionEvent event) {
        qheVsChuHo = inpQhe.getText();
        try{
            if(qheVsChuHo == ""){
                throw new Exception("Ban nhap khong day du cac gia tri");
            }
        }catch (Exception e){
            Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Lỗi");
            alert.setContentText("Sửa lại");
            alert.setHeaderText(e.getMessage());
            alert.show();
            return;
        }
        ((Stage)(((Node)event.getSource()).getScene().getWindow())).close();
    }

}
