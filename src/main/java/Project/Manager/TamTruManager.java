package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.TamTru;

import java.util.ArrayList;

public class TamTruManager implements Manager<TamTru>{
    public static ArrayList<TamTru> List = new ArrayList<>();
    @Override
    public void add(TamTru o) {
        List.add(o);
    }

    @Override
    public TamTru get(int id) {
        for(TamTru x : List) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(TamTru x : List) if(x.getID() == id ) return true;
        return false;
    }
}
