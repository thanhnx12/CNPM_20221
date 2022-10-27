module Project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    exports Project.Model;
    opens Project.Model to javafx.fxml;
    exports Project.Controller;
    opens Project.Controller to javafx.fxml;
    exports Project.Manager;
    opens Project.Manager to javafx.fxml;
    exports Project.DBConnect to javafx.fxml;
    opens Project.DBConnect;
}