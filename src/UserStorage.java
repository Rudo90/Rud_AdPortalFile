import java.util.HashMap;

public class UserStorage {

    HashMap<String, User> map = new HashMap<>();

    public void addUser(User user) {

        if (user != null) {
            String key = user.getPhoneNumber();
            map.put(key, user);

        }

    }
}
