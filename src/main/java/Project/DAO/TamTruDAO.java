package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.TamTru;
import Project.Model.TamVang;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TamTruDAO implements DAO<TamTru>{

    @Override
    public int insert(TamTru o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO tam_tru(id,idNhanKhau,maGiayTamTru,noiTamTru,tuNgay,denNgay,lyDo,hoTen)"
                    +" VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setInt(2,o.getIdNhanKhau());
            st.setString(3,o.getMaGiayTamTru());
            st.setString(4,o.getNoiTamTru());
            st.setDate(5,o.getTuNgay());
            st.setDate(6,o.getDenNgay());
            st.setString(7,o.getLyDo());
            st.setString(8,o.getHoTen());
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
    public int update(TamTru o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE tam_tru "+"SET idNhanKhau = ?, maGiayTamTru = ?,noiTamTru = ? ,tuNgay = ?, denNgay = ?, lyDo = ?,hoTen = ? "
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getIdNhanKhau());
            st.setString(2,o.getMaGiayTamTru());
            st.setString(3,o.getNoiTamTru());
            st.setDate(4,o.getTuNgay());
            st.setDate(5,o.getDenNgay());
            st.setString(6,o.getLyDo());
            st.setString(7,o.getHoTen());
            st.setInt(8,o.getID());
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
    public int delete(TamTru o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from tam_tru "+"WHERE id=?";
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
            String sql="SELECT MAX(id) as maxID FROM tam_tru";
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
    public ArrayList<TamTru> selectAll() {
        ArrayList<TamTru> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM tam_tru";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                TamTru o=new TamTru();
                o.setID(rs.getInt("id"));
                o.setIdNhanKhau(rs.getInt("idNhanKhau"));
                o.setMaGiayTamTru(rs.getString("maGiayTamTru"));
                o.setNoiTamTru(rs.getString("noiTamTru"));
                o.setTuNgay(rs.getDate("tuNgay"));
                o.setDenNgay(rs.getDate("denNgay"));
                o.setLyDo(rs.getString("lyDo"));
                o.setHoTen(rs.getString("hoTen"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
