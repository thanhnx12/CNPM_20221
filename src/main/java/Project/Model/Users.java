package Project.Model;

public class Users {
    private int ID;
    private String userName;
    private String password;
    private boolean loginState;

    public boolean isLoginState() {
        return loginState;
    }

    public void setLoginState(boolean loginState) {
        this.loginState = loginState;
    }

    public Users(int ID, String userName, String password, boolean loginState) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
        this.loginState = loginState;
    }

    public Users() {
        this.setID(1);
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Users(int ID, String userName, String password) {
        this.ID = ID;
        this.userName = userName;
        this.password = password;
    }
}
