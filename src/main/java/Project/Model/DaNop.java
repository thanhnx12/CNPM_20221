package Project.Model;

import java.sql.Date;

public class DaNop {
    private int ID;
    private int idPhi;
    private int idHoKhau;
    private Date ngayNop;
    private double soTien;

    public DaNop(int ID, int idPhi, int idHoKhau, Date ngayNop, double soTien) {
        this.ID = ID;
        this.idPhi = idPhi;
        this.idHoKhau = idHoKhau;
        this.ngayNop = ngayNop;
        this.soTien = soTien;
    }

    public Date getNgayNop() {
        return ngayNop;
    }



    public void setNgayNop(Date ngayNop) {
        this.ngayNop = ngayNop;
    }

    public DaNop() {
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdPhi() {
        return idPhi;
    }

    public void setIdPhi(int idPhi) {
        this.idPhi = idPhi;
    }

    public int getIdHoKhau() {
        return idHoKhau;
    }

    public void setIdHoKhau(int idHoKhau) {
        this.idHoKhau = idHoKhau;
    }

    public double getSoTien() {
        return soTien;
    }

    public void setSoTien(double soTien) {
        this.soTien = soTien;
    }
}
