import java.util.ArrayList;
import java.util.List;


abstract class Isubscriber{
    public abstract void notify1(String msg);
}
class User extends Isubscriber{
    private int userId;

    public User(int userId) {
        this.userId = userId;
    }
    @Override
    public void notify1(String msg){
        System.out.println("msg "+msg+" received to user "+userId);
    }
}
class Group{
    private List<Isubscriber> users = new ArrayList<Isubscriber>();
    public void subscribe(Isubscriber user){
        users.add(user);
    }
    public void unsubscribe(Isubscriber user){
        users.remove(user);
    }
    public void notify1(String msg){
        for (Isubscriber i : users){
            i.notify1(msg);

        }
    }
}

public class App {
    public static void main(String[] args) throws Exception {
        Group group = new Group();

        User user1 = new User(1);
        User user2 = new User(2);
        User user3 = new User(3);

        group.subscribe(user1);
        group.subscribe(user2);
        group.subscribe(user3);

        group.notify1("new msg");

        group.unsubscribe(user1);

        group.notify1("new new msg");

        }
}
