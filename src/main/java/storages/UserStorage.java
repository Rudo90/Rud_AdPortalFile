package storages;

import models.*;
import util.FileUtil;
import java.io.Serializable;
import java.util.Map;

public class UserStorage implements Serializable {

    public Map<String, User> map;

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
