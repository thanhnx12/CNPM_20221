package Project.DAO;

import Project.DBConnect.JDBCUtil;
import Project.Model.Phi;
import Project.Model.ThanhVien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ThanhVienDAO implements DAO<ThanhVien>{

    @Override
    public int insert(ThanhVien o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="INSERT INTO thanh_vien_cua_ho(idNhanKhau,idHoKhau,quanHeVoiChuHo)"
                    +" VALUES(?,?,?)";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getIdNhanKhau());
            st.setInt(2,o.getIdHoKhau());
            st.setString(3,o.getQuanHeVoiChuHo());
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
    public int update(ThanhVien o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="UPDATE thanh_vien_cua_ho "+"SET idHoKhau = ?,quanHeVoiChuHo = ? "
                    +"WHERE idNhanKhau = ?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getIdHoKhau());
            st.setString(2,o.getQuanHeVoiChuHo());
            st.setInt(3,o.getIdNhanKhau());
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
    public int delete(ThanhVien o) {
        int ans=0;
        try{
            Connection con= JDBCUtil.getConnection();
            String sql="DELETE from thanh_vien_cua_ho "+"WHERE idNhanKhau=?";
            PreparedStatement st=con.prepareStatement(sql);
            st.setInt(1,o.getIdNhanKhau());
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
            String sql="SELECT MAX(id) as maxID FROM thanh_vien_cua_ho";
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
    public ArrayList<ThanhVien> selectAll() {
        ArrayList<ThanhVien> ans=new ArrayList<>();
        try{
            Connection con=JDBCUtil.getConnection();
            String sql="SELECT * FROM thanh_vien_cua_ho";
            PreparedStatement st=con.prepareStatement(sql);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                ThanhVien o=new ThanhVien();
                o.setIdNhanKhau(rs.getInt("idNhanKhau"));
                o.setIdHoKhau(rs.getInt("idHoKhau"));
                o.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
                String sql1="SELECT * FROM nhan_khau WHERE ID = ?";
                PreparedStatement st1 = con.prepareStatement(sql1);
                st1.setInt(1, o.getIdNhanKhau());
                ResultSet rs1 = st1.executeQuery();
                if(rs1.next()) {
                    o.setNgaySinh(rs1.getDate("ngaySinh"));
                    o.setHoVaTen(rs1.getString("hoTen"));
                }
                ans.add(o);
            }
            JDBCUtil.closeConnection(con);
        } catch(SQLException e){
            e.printStackTrace();
        }
        return ans;
    }
}
