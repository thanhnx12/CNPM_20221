package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.KhaiTu;
import Project.Model.TamTru;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class KhaiTuDAO implements DAO<KhaiTu> {

    @Override
    public int insert(KhaiTu o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO khai_tu(id,soGiayKhaiTu,idNguoiKhai,idNguoiChet,ngayKhai,ngayChet,lyDochet)"
                    +" VALUES(?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setInt(2,o.getSoGiayKhaiTu());
            st.setInt(3,o.getIdNguoiKhai());
            st.setInt(4,o.getIdNguoiChet());
            st.setDate(5,o.getNgayKhai());
            st.setDate(6,o.getNgayChet());
            st.setString(7,o.getLyDoChet());
            ans=st.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex){
            ex.printStackTrace();
        }
        return ans;
    }

    @Override
    public int update(KhaiTu o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE khai_tu "+"SET soGiayKhaiTu = ?, idNguoiKhai = ?,idNguoiChet = ? ,ngayKhai = ?, ngayChet = ?, lyDoChet = ? "
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getSoGiayKhaiTu());
            st.setInt(2,o.getIdNguoiKhai());
            st.setInt(3,o.getIdNguoiChet());
            st.setDate(4,o.getNgayKhai());
            st.setDate(5,o.getNgayChet());
            st.setString(6,o.getLyDoChet());
            st.setInt(7,o.getID());
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
    public int delete(KhaiTu o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from khai_tu "+"WHERE id=?";
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
            String sql="SELECT MAX(id) as maxID FROM khai_tu";
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
    public ArrayList<KhaiTu> selectAll() {
        ArrayList<KhaiTu> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM khai_tu";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                KhaiTu o=new KhaiTu();
                o.setID(rs.getInt("id"));
                o.setSoGiayKhaiTu(rs.getInt("soGiayKhaiTu"));
                o.setIdNguoiKhai(rs.getInt("idNguoiKhai"));
                o.setIdNguoiChet(rs.getInt("idNguoiChet"));
                o.setNgayKhai(rs.getDate("ngayKhai"));
                o.setNgayChet(rs.getDate("ngayChet"));
                o.setLyDoChet(rs.getString("lyDoChet"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
