package Project.Model;

public class Phi {
    private int ID;
    private String tenLoaiPhi;
    private  boolean tuNguyen;

    public Phi(int ID, String tenLoaiPhi, boolean tuNguyen) {
        this.ID = ID;
        this.tenLoaiPhi = tenLoaiPhi;
        this.tuNguyen = tuNguyen;
    }

    public Phi() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTenLoaiPhi() {
        return tenLoaiPhi;
    }

    public void setTenLoaiPhi(String tenLoaiPhi) {
        this.tenLoaiPhi = tenLoaiPhi;
    }

    public boolean isTuNguyen() {
        return tuNguyen;
    }

    public void setTuNguyen(boolean tuNguyen) {
        this.tuNguyen = tuNguyen;
    }
}
