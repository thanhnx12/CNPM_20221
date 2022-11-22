package Project.Model;

import java.sql.Date;

public class Phi {
    private int ID;
    private String tenLoaiPhi;
    private  boolean tuNguyen;
    private boolean thuTheoHo; // phan biet soTien thu theo ho GD hay thu theo nguoi
    private double soTien; // cho phi bat buoc
    private Date hanNop;
    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }

    public Date getHanNop() {
        return hanNop;
    }

    public void setHanNop(Date hanNop) {
        this.hanNop = hanNop;
    }

    public Phi(int ID, String tenLoaiPhi, boolean tuNguyen, boolean thuTheoHo, double soTien, Date hanNop) {
        this.ID = ID;
        this.tenLoaiPhi = tenLoaiPhi;
        this.tuNguyen = tuNguyen;
        this.thuTheoHo = thuTheoHo;
        this.soTien = soTien;
        this.hanNop = hanNop;
    }

    public boolean isThuTheoHo() {
        return thuTheoHo;
    }

    public void setThuTheoHo(boolean thuTheoHo) {
        this.thuTheoHo = thuTheoHo;
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
