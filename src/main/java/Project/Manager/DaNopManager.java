package Project.Manager;

import Project.Model.DaNop;

import java.util.ArrayList;

public class DaNopManager implements Manager<DaNop> {
    public static ArrayList<DaNop> List = new ArrayList<>();
    @Override
    public void add(DaNop o) {
        List.add(o);
    }

    @Override
    public DaNop get(int id) {
        for(DaNop x : List) if(x.getID() == id) return x;
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(DaNop x : List) if(x.getID() == id) return true;
        return false;
    }
}
