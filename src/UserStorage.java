import java.io.FileOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserStorage implements Serializable {

    Map<String, User> map;

    public UserStorage(){
        map = FileUtil.deserializeUserStorage();
    }

    public void addUser(User user) {

        if (user != null) {
            String key = user.getPhoneNumber();
            map.put(key, user);
            FileUtil.UserStorageSerialize(map);
        }

    }
}
