package Project.Manager;

import Project.Model.KhaiTu;
import Project.Model.Users;

import java.util.ArrayList;

public class UsersManager implements Manager<Users>{
    public static ArrayList<Users> List = new ArrayList<>();
    @Override
    public void add(Users o) {
        List.add(o);
    }

    @Override
    public Users get(int id) {
        try {
            for (Users x  : List) {
                if (x.getID() == id) return x;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean contains(int id) {
        for(Users x : List) if(x.getID() == id) return true;
        return false;
    }
}
