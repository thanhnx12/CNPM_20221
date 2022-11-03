package Project.Manager;

import Project.Model.HoKhau;

import java.util.ArrayList;

public class HoKhauManager implements Manager<HoKhau>{
    public static ArrayList<HoKhau> listHoKhau= new ArrayList<>();
    @Override
    public void add(HoKhau o) {
        listHoKhau.add(o);
    }

    @Override
    public HoKhau get(int id) {
        for(HoKhau x : listHoKhau) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(HoKhau x : listHoKhau) if (x.getID() == id) return true;
        return false;
    }
}
