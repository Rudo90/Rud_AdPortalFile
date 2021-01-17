package util;

import models.*;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileUtil implements Serializable {

    private static final String AD_STORAGE_PATH = "AdList.txt";
    private static final String USER_STORAGE_PATH = "UserList.txt";

    public static void AdStorageSerialize (Map<String, Advertisement> adMap){

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(AD_STORAGE_PATH))){
            objectOutputStream.writeObject(adMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void UserStorageSerialize (Map<String, User> userMap){

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(USER_STORAGE_PATH))){
            objectOutputStream.writeObject(userMap);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Map<String, Advertisement> deserializeAdStorage (){
        createFile(AD_STORAGE_PATH);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(AD_STORAGE_PATH))){
           return (Map<String, Advertisement>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    public static Map<String, User> deserializeUserStorage (){
        createFile(USER_STORAGE_PATH);
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(USER_STORAGE_PATH))){
            return (Map<String, User>) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new HashMap<>();
    }

    private static void createFile (String path){

        File file = new File(path);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("File can not be created");
            }
        }

    }





}

