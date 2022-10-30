package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.NhanKhau;

import java.util.ArrayList;

public class NhanKhauManager implements Manager<NhanKhau>{
    public static ArrayList<NhanKhau> nhanKhauList = new ArrayList<>();
    @Override
    public void add(NhanKhau o) {
        nhanKhauList.add(o);
    }

    @Override
    public NhanKhau get(int id) {
        for(NhanKhau x : nhanKhauList) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(NhanKhau x : nhanKhauList) if(x.getID() == id) return true;
        return false;
    }
}
