package viewcontroller;

/**
 * An interface declaring the operations that a ConcreteCommand needs to implement
 *
 * @author Vishrut Kevadiya
 *
 */
public interface Command {

    void execute();
    int getRow();
    int getCol();
}
