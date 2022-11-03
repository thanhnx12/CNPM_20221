package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.NhanKhau;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;



public class NhanKhauDAO implements DAO<NhanKhau>{

    @Override
    public int insert(NhanKhau o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO nhan_khau(ID,soCMT_CCCD,hoTen,ngaySinh,gioiTinh,noiSinh," +
                    "nguyenQuan,danToc,quocTich,soHoChieu,noiThuongTru," +
                    "diaChiHienTai,tonGiao,ghiChu,ngayTao,idNguoiTao," +
                    "ngayXoa,idNguoiXoa,lyDoXoa,dienThoai)"
                    +" VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setInt(2,o.getSoCMT_CCCD());
            st.setString(3,o.getHoTen());
            st.setDate(4,o.getNgaySinh());
            st.setString(5,o.getGioiTinh());
            st.setString(6,o.getNoiSinh());
            st.setString(7,o.getNguyenQuan());
            st.setString(8,o.getDanToc());
            st.setString(9,o.getQuocTich());
            st.setString(10,o.getSoHoChieu());
            st.setString(11,o.getNoiThuongTru());
            st.setString(12,o.getDiaChiHienTai());
            st.setString(13,o.getTonGiao());
            st.setString(14,o.getGhiChu());
            st.setDate(15,o.getNgayTao());
            st.setInt(16,o.getIdNguoiTao());
            st.setDate(17,o.getNgayXoa());
            st.setInt(18,o.getIdNguoiXoa());
            st.setString(19,o.getLyDoXoa());
            st.setString(20,o.getDienThoai());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }

    @Override
    public int update(NhanKhau o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE nhan_khau "+"SET soCMT_CCCD = ? , hoTen = ? , ngaySinh = ? , gioiTinh=? , " +
                    "noiSinh = ? , nguyenQuan = ? , danToc = ? , quocTich = ? , soHoChieu = ? , noiThuongTru = ? , " +
                    "diaChiHienTai = ? , tonGiao = ? , ghiChu = ?, ngayTao = ? , idNguoiXoa = ?, ngayXoa = ?, " +
                    "idNguoiXoa = ? , lyDoXoa = ? , dienThoai = ? " +
                    "WHERE id = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getSoCMT_CCCD());
            st.setString(2,o.getHoTen());
            st.setDate(3,o.getNgaySinh());
            st.setString(4,o.getGioiTinh());
            st.setString(5,o.getNoiSinh());
            st.setString(6,o.getNguyenQuan());
            st.setString(7,o.getDanToc());
            st.setString(8,o.getQuocTich());
            st.setString(9,o.getSoHoChieu());
            st.setString(10,o.getNoiThuongTru());
            st.setString(11,o.getDiaChiHienTai());
            st.setString(12,o.getTonGiao());
            st.setString(13,o.getGhiChu());
            st.setDate(14,o.getNgayTao());
            st.setInt(15,o.getIdNguoiTao());
            st.setDate(16,o.getNgayXoa());
            st.setInt(17,o.getIdNguoiXoa());
            st.setString(18,o.getLyDoXoa());
            st.setString(19,o.getDienThoai());
            st.setInt(20,o.getID());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public int delete(NhanKhau o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from nhan_khau "+"WHERE id=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            ans=st.executeUpdate();
            System.out.println("Bạn đã thực thi: "+sql);
            System.out.println("Có "+ans+" dòng bị thay đổi");
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

    @Override
    public int getNewID() {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="SELECT MAX(id) as maxID FROM nhan_khau";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ans = rs.getInt("maxID");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans + 1;
    }

    @Override
    public ArrayList<NhanKhau> selectAll() {
        ArrayList<NhanKhau> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM nhan_khau";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                NhanKhau o=new NhanKhau();
                o.setID(rs.getInt("ID"));
                o.setSoCMT_CCCD(rs.getInt("soCMT_CCCD"));
                o.setHoTen(rs.getString("hoTen"));
                o.setNgaySinh(rs.getDate("ngaySinh"));
                o.setGioiTinh(rs.getString("gioiTinh"));
                o.setNoiSinh(rs.getString("noiSinh"));
                o.setNguyenQuan(rs.getString("nguyenQuan"));
                o.setDanToc(rs.getString("danToc"));
                o.setQuocTich(rs.getString("quocTich"));
                o.setSoHoChieu(rs.getString("soHoChieu"));
                o.setNoiThuongTru(rs.getString("noiThuongTru"));
                o.setTonGiao(rs.getString("tonGiao"));
                o.setGhiChu(rs.getString("ghiChu"));
                o.setNgayTao(rs.getDate("ngayTao"));
                o.setIdNguoiTao(rs.getInt("idNguoiTao"));
                o.setNgayXoa(rs.getDate("ngayXoa"));
                o.setIdNguoiXoa(rs.getInt("idNguoiXoa"));
                o.setLyDoXoa(rs.getString("lyDoXoa"));
                o.setDienThoai(rs.getString("dienThoai"));
                o.setDiaChiHienTai(rs.getString("diaChiHienTai"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

//    public static void main(String[] args) {
//        NhanKhau a = new NhanKhau(8,12345,"asdfawe",java.sql.Date.valueOf("2002-9-12"),"Nam","BacGiang","BacGiang","Kinh","Viet Nam",
//                "123", "hoho", "haha","Khong","hohooh",java.sql.Date.valueOf("2002-9-12"),1,java.sql.Date.valueOf("2002-9-12"),1,"asdf" ,"09999");
//        NhanKhauDAO b = new NhanKhauDAO();
//        b.insert(a);
//    }
}
