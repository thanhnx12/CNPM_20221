package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.ThanhVien;

import java.util.ArrayList;

public class ThanhVienManager implements Manager<ThanhVien> {
    public static ArrayList<ThanhVien> List = new ArrayList<>();
    
    @Override
    public void add(ThanhVien o) {
        List.add(o);
    }

    @Override
    public ThanhVien get(int id) {
        for(ThanhVien x : List) if(x.getIdNhanKhau() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(ThanhVien x : List) if(x.getIdNhanKhau() == id) return true;
        return false;
    }
}
