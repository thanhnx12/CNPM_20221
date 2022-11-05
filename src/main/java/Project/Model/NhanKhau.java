package Project.Model;
import java.sql.*;
public class NhanKhau {
    private int ID;
    private int soCMT_CCCD;
    private String hoTen;
    private Date ngaySinh;
    private String gioiTinh;
    private String noiSinh;
    private String nguyenQuan;
    private String danToc;
    private String quocTich;
    private String soHoChieu;
    private String noiThuongTru;
    private String diaChiHienTai;
    private String tonGiao;
    private String ghiChu;
    private Date ngayTao;
    private int idNguoiTao;
    private Date ngayXoa;
    private int idNguoiXoa;
    private String lyDoXoa;
    private String dienThoai;

    public NhanKhau(int ID, int soCMT_CCCD, String hoTen, Date ngaySinh, String gioiTinh, String noiSinh, String nguyenQuan, String danToc, String quocTich, String soHoChieu, String noiThuongTru, String diaChiHienTai, String tonGiao, String ghiChu, Date ngayTao, int idNguoiTao, Date ngayXoa, int idNguoiXoa, String lyDoXoa, String dienThoai) {
        this.ID = ID;
        this.soCMT_CCCD = soCMT_CCCD;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.noiSinh = noiSinh;
        this.nguyenQuan = nguyenQuan;
        this.danToc = danToc;
        this.quocTich = quocTich;
        this.soHoChieu = soHoChieu;
        this.noiThuongTru = noiThuongTru;
        this.diaChiHienTai = diaChiHienTai;
        this.tonGiao = tonGiao;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
        this.idNguoiTao = idNguoiTao;
        this.ngayXoa = ngayXoa;
        this.idNguoiXoa = idNguoiXoa;
        this.lyDoXoa = lyDoXoa;
        this.dienThoai = dienThoai;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }

    public NhanKhau() {
        this.setGhiChu(" ");
        this.setDienThoai(" ");
        this.setIdNguoiXoa(1);
        this.setIdNguoiTao(1);
    }

    public String getLyDoXoa() {
        return lyDoXoa;
    }

    public void setLyDoXoa(String lyDoXoa) {
        this.lyDoXoa = lyDoXoa;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getSoCMT_CCCD() {
        return soCMT_CCCD;
    }

    public void setSoCMT_CCCD(int soCMT_CCCD) {
        this.soCMT_CCCD = soCMT_CCCD;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getNoiSinh() {
        return noiSinh;
    }

    public void setNoiSinh(String noiSinh) {
        this.noiSinh = noiSinh;
    }

    public String getNguyenQuan() {
        return nguyenQuan;
    }

    public void setNguyenQuan(String nguyenQuan) {
        this.nguyenQuan = nguyenQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getSoHoChieu() {
        return soHoChieu;
    }

    public void setSoHoChieu(String soHoChieu) {
        this.soHoChieu = soHoChieu;
    }

    public String getNoiThuongTru() {
        return noiThuongTru;
    }

    public void setNoiThuongTru(String noiThuongTru) {
        this.noiThuongTru = noiThuongTru;
    }

    public String getDiaChiHienTai() {
        return diaChiHienTai;
    }

    public void setDiaChiHienTai(String diaChiHienTai) {
        this.diaChiHienTai = diaChiHienTai;
    }

    public String getTonGiao() {
        return tonGiao;
    }

    public void setTonGiao(String tonGiao) {
        this.tonGiao = tonGiao;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getIdNguoiTao() {
        return idNguoiTao;
    }

    public void setIdNguoiTao(int idNguoiTao) {
        this.idNguoiTao = idNguoiTao;
    }

    public Date getNgayXoa() {
        return ngayXoa;
    }

    public void setNgayXoa(Date ngayXoa) {
        this.ngayXoa = ngayXoa;
    }

    public int getIdNguoiXoa() {
        return idNguoiXoa;
    }

    public void setIdNguoiXoa(int idNguoiXoa) {
        this.idNguoiXoa = idNguoiXoa;
    }
}
