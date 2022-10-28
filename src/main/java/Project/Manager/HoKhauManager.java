package Project.Manager;

import Project.Model.HoKhau;

import java.util.ArrayList;

public class HoKhauManager implements Manager<HoKhau>{
    public static ArrayList<HoKhau> List = new ArrayList<>();
    @Override
    public void add(HoKhau o) {
        List.add(o);
    }

    @Override
    public HoKhau get(int id) {
        for(HoKhau x : List) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(HoKhau x : List) if (x.getID() == id) return true;
        return false;
    }
}
