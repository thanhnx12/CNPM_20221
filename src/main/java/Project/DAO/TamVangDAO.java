package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.TamVang;
import Project.Model.ThanhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TamVangDAO implements DAO<TamVang> {

    @Override
    public int insert(TamVang o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO tam_vang(id,idNhanKhau,maGiayTamVang,tuNgay,denNgay,lyDo)"
                    +" VALUES(?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setInt(2,o.getIdNhanKhau());
            st.setString(3,o.getMaGiayTamVang());
            st.setDate(4,o.getTuNgay());
            st.setDate(5,o.getDenNgay());
            st.setString(6,o.getLyDo());
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
    public int update(TamVang o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE tam_vang "+"SET idNhanKhau = ?, maGiayTamVang = ?,tuNgay = ?, denNgay = ?, lyDo = ? "
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getIdNhanKhau());
            st.setString(2,o.getMaGiayTamVang());
            st.setDate(3,o.getTuNgay());
            st.setDate(4,o.getDenNgay());
            st.setString(5,o.getLyDo());
            st.setInt(6,o.getID());
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
    public int delete(TamVang o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from tam_vang "+"WHERE id=?";
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
            String sql="SELECT MAX(id) as maxID FROM tam_vang";
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
    public ArrayList<TamVang> selectAll() {
        ArrayList<TamVang> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM tam_vang";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                TamVang o=new TamVang();
                o.setID(rs.getInt("id"));
                o.setIdNhanKhau(rs.getInt("idNhanKhau"));
                o.setMaGiayTamVang(rs.getString("maGiayTamVang"));
                o.setTuNgay(rs.getDate("tuNgay"));
                o.setDenNgay(rs.getDate("denNgay"));
                o.setLyDo(rs.getString("lyDo"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
