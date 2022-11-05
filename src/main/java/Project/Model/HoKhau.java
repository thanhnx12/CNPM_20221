package Project.Model;

import java.sql.Date;

public class HoKhau {
    private int ID;
    private String maHoKhau;
    private int idChuHo;
    private String maKhuVuc;
    private String diaChi;
    private Date ngayLap;
    private NhanKhau chuHo;

    public NhanKhau getChuHo() {
        return chuHo;
    }

    public void setChuHo(NhanKhau chuHo) {
        this.chuHo = chuHo;
    }

    public HoKhau(int ID, String maHoKhau, int idChuHo, String maKhuVuc, String diaChi, Date ngayLap, NhanKhau chuHo) {
        this.ID = ID;
        this.maHoKhau = maHoKhau;
        this.idChuHo = idChuHo;
        this.maKhuVuc = maKhuVuc;
        this.diaChi = diaChi;
        this.ngayLap = ngayLap;
        this.chuHo = chuHo;
    }

    public HoKhau() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public int getIdChuHo() {
        return idChuHo;
    }

    public void setIdChuHo(int idChuHo) {
        this.idChuHo = idChuHo;
    }

    public String getMaKhuVuc() {
        return maKhuVuc;
    }

    public void setMaKhuVuc(String maKhuVuc) {
        this.maKhuVuc = maKhuVuc;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }
}
