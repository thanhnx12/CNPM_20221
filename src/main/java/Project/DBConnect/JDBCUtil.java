package Project.DBConnect;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtil {
    public static Connection getConnection() {
        Connection connection=null;
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quan_ly_so_ho_khau","root","12092002");
            //System.out.println("connected");
        } catch (Exception e ) {
            System.out.println(e);
        }
        return connection;
    }
    public static void closeConnection(Connection c) throws SQLException{
    }
    public static void printInfo(Connection c) throws SQLException{
        if (c!=null){
            DatabaseMetaData mtdt=c.getMetaData();
            System.out.println(mtdt.getDatabaseProductName());
            System.out.println(mtdt.getDatabaseProductVersion());
        }
    }

//    public static void main(String[] args) {
//        Connection conn = getConnection();
//    }
}

