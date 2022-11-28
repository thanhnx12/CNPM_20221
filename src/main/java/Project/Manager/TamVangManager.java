package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.TamTru;
import Project.Model.TamVang;

import java.util.ArrayList;

public class TamVangManager implements Manager<TamVang>{
    public static ArrayList<TamVang> List = new ArrayList<>();
    @Override
    public void add(TamVang o) {
        List.add(o);
    }

    @Override
    public TamVang get(int id) {
        for(TamVang x : List) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(TamVang x : List) if(x.getID() == id ) return true;
        return false;
    }
}
