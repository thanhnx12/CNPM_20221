package Project.Controller;

import Project.DBConnect.JDBCUtil;
import Project.Manager.UsersManager;
import Project.Model.Users;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    @FXML
    private TextField UserID;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button submitButton;

    @FXML
    void login(ActionEvent event) throws SQLException, IOException {
        Window owner = submitButton.getScene().getWindow();

        if (UserID.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter your username");
            return;
        }
        if (passwordField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                    "Please enter a password");
            return;
        }

        String username = UserID.getText();
        String password = passwordField.getText();

        boolean check = false;
        for(Users x : UsersManager.List){
            if(x.getUserName().equals(username) && x.getPassword().equals(password)){
                check = true;
                Connection con = JDBCUtil.getConnection();
                PreparedStatement st = con.prepareStatement("update users set loginState = 1 where username =  ?");
                st.setString(1, x.getUserName());
                UsersManager usersManager = new UsersManager();
                Main.user= usersManager.get(x.getID());
                st.executeUpdate();
                break;
            }
        }
        if(check){
            // Chuy???n c???nh
            Node source = (Node)  event.getSource();
            Stage stage  = (Stage) source.getScene().getWindow();
            stage.close();

            Parent root = FXMLLoader.load(this.getClass().getResource("TaskBarView.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("Qu???n l?? thu ph??");

            stage.show();
        }
        else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Th??ng b??o");
            alert.setHeaderText("Th??ng b??o:");
            alert.setContentText("Th??ng tin ????ng nh???p kh??ng ch??nh x??c !");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Connection conn = JDBCUtil.getConnection();
        try {
            PreparedStatement sta = conn.prepareStatement("update users set loginState = 0");
            sta.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void infoBox(String infoMessage, String headerText, String title) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText(infoMessage);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.showAndWait();
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
