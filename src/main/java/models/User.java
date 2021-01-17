package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode


public class User implements Serializable {

    private String name;
    private String surname;
    private String gender;
    private int age;
    private String phoneNumber;
    private String password;

    public User() {
    }

    public User(String name, String surname, String gender, int age, String phoneNumber, String password) {
        this.name = name;
        this.surname = surname;
        this.gender = gender;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.password = password;
    }

    @Override
    public String toString() {
        return "models.User{" +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }


}
