package Project.Manager;

import Project.Model.KhaiTu;

import java.util.ArrayList;

public class KhaiTuManager implements Manager<KhaiTu>{
    public static ArrayList<KhaiTu> List = new ArrayList<>();
    @Override
    public void add(KhaiTu o) {
        List.add(o);
    }

    @Override
    public KhaiTu get(int id) {
        for(KhaiTu x : List) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(KhaiTu x : List) if(x.getID() == id) return true;
        return false;
    }
}
