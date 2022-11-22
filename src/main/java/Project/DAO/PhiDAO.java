package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.HoKhau;
import Project.Model.Phi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PhiDAO implements DAO<Phi>{

    @Override
    public int insert(Phi o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO phi(ID,tenLoaiPhi,tuNguyen,soTien,thuTheoHo,hanNop)"
                    +" VALUES(?,?,?,?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setString(2,o.getTenLoaiPhi());
            st.setBoolean(3,o.isTuNguyen());
            st.setDouble(4,o.getSoTien());
            st.setBoolean(5,o.isThuTheoHo());
            st.setDate(6,o.getHanNop());
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
    public int update(Phi o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE phi "+"SET tenLoaiPhi = ?,tuNguyen = ?, soTien = ? ,thuTheoHo = ?,hanNop = ?"
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setString(1,o.getTenLoaiPhi());
            st.setBoolean(2,o.isTuNguyen());
            st.setDouble(3,o.getSoTien());
            st.setBoolean(4,o.isThuTheoHo());
            st.setDate(5,o.getHanNop());
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
    public int delete(Phi o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from phi "+"WHERE id=?";
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
            String sql="SELECT MAX(id) as maxID FROM phi";
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
    public ArrayList<Phi> selectAll() {
        ArrayList<Phi> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM phi";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Phi o=new Phi();
                o.setID(rs.getInt("ID"));
                o.setTenLoaiPhi(rs.getString("tenLoaiPhi"));
                o.setTuNguyen(rs.getBoolean("tuNguyen"));
                o.setSoTien(rs.getDouble("soTien"));
                o.setThuTheoHo(rs.getBoolean("thuTheoHo"));
                o.setHanNop(rs.getDate("hanNop"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }

//    public static void main(String[] args) {
//        Phi a = new Phi(2,"hehehe",false);
//        PhiDAO b = new PhiDAO();
//        ArrayList<Phi> c = new ArrayList<>();
//        c = b.selectAll();
//        for(Phi x : c){
//            System.out.println(x.isTuNguyen());
//        }
//
//    }
}
