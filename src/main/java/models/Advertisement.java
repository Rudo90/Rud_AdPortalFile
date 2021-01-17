package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import java.io.Serializable;

@Getter
@EqualsAndHashCode
@ToString

public class Advertisement implements Serializable {

    private String title;
    private String text;
    private double price;
    private String date;
    private String category;
    private String idNumber;
    private User user;

    public Advertisement(){

    }

    public Advertisement(String title, String text, double price, String date, String category, String idNumber, User user) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.date = date;
        this.category = category;
        this.idNumber = idNumber;
        this.user = user;
    }
}
