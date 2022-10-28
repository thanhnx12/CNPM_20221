package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.NhanKhau;

import java.util.ArrayList;

public class NhanKhauManager implements Manager<NhanKhau>{
    public static ArrayList<NhanKhau> List = new ArrayList<>();
    @Override
    public void add(NhanKhau o) {
        List.add(o);
    }

    @Override
    public NhanKhau get(int id) {
        for(NhanKhau x : List) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(NhanKhau x : List) if(x.getID() == id) return true;
        return false;
    }
}
