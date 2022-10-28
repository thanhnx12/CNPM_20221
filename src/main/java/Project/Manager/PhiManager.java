package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.Phi;

import java.util.ArrayList;

public class PhiManager implements Manager<Phi>{
    public static ArrayList<Phi> List = new ArrayList<>();
    @Override
    public void add(Phi o) {
        List.add(o);
    }

    @Override
    public Phi get(int id) {
        for(Phi x : List) if(x.getID() == id ) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(Phi x : List) if(x.getID() == id) return true;
        return false;
    }
}
