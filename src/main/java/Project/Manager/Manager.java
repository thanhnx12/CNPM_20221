package Project.Manager;

public interface Manager <T>{
    public void add(T o);
    public Object get(int id);
    public boolean contains(int id);

}
