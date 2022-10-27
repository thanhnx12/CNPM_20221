package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.DaNop;
import Project.Model.Phi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DaNopDAO implements DAO<DaNop>{

    @Override
    public int insert(DaNop o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO da_nop(ID,idPhi,idHoKhau)"
                    +" VALUES(?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getID());
            st.setInt(2,o.getIdPhi());
            st.setInt(3,o.getIdHoKhau());
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
    public int update(DaNop o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE da_nop "+"SET idPhi = ?,idHoKhau = ?"
                    +"WHERE ID = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getIdPhi());
            st.setInt(2,o.getIdHoKhau());
            st.setInt(3,o.getID());
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
    public int delete(DaNop o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from da_nop "+"WHERE id=?";
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
    public ArrayList<DaNop> selectAll() {
        ArrayList<DaNop> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM da_nop";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                DaNop o=new DaNop();
                o.setID(rs.getInt("ID"));
                o.setIdHoKhau(rs.getInt("idHoKhau"));
                o.setIdPhi(rs.getInt("idPhi"));
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
