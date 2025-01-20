package model;

public interface Visitable {

    public Object accept(Visitor visit);

}
