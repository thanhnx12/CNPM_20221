package Project.Model;

import java.sql.Date;

public class TamVang {
    private int ID;
    private int idNhanKhau;
    private String maGiayTamVang;
    private Date tuNgay;
    private Date denNgay;
    private String lyDo;

    public TamVang(int ID, int idNhanKhau, String maGiayTamVang, Date tuNgay, Date denNgay, String lyDo) {
        this.ID = ID;
        this.idNhanKhau = idNhanKhau;
        this.maGiayTamVang = maGiayTamVang;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.lyDo = lyDo;
    }

    public TamVang() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIdNhanKhau() {
        return idNhanKhau;
    }

    public void setIdNhanKhau(int idNhanKhau) {
        this.idNhanKhau = idNhanKhau;
    }

    public String getMaGiayTamVang() {
        return maGiayTamVang;
    }

    public void setMaGiayTamVang(String maGiayTamVang) {
        this.maGiayTamVang = maGiayTamVang;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public String getLyDo() {
        return lyDo;
    }

    public void setLyDo(String lyDo) {
        this.lyDo = lyDo;
    }
}
