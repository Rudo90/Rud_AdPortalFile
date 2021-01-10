import java.util.Objects;

public class Advertisement {

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

    public String getIdNumber() {
        return idNumber;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public double getPrice() {
        return price;
    }

    public String getDate() {
        return date;
    }

    public String getCategory() {
        return category;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Advertisement that = (Advertisement) o;
        return Double.compare(that.price, price) == 0 &&
                Objects.equals(title, that.title) &&
                Objects.equals(text, that.text) &&
                Objects.equals(date, that.date) &&
                Objects.equals(category, that.category) &&
                Objects.equals(idNumber, that.idNumber) &&
                Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, text, price, date, category, idNumber, user);
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", idNumber=" + idNumber +
                ", user=" + user +
                '}';
    }
}
