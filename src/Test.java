import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.time.format.FormatStyle;
import java.util.Scanner;

public class Test implements ExitLoginRegisterCommands, UserAccountCommands, MaleFemaleCommands {

    static Scanner scanner = new Scanner(System.in);
    static UserStorage userStorage = new UserStorage();
    static AdvertisementStorage advertisementStorage = new AdvertisementStorage();

    static void printCommands() {
        System.out.println("Input 0 to EXIT");
        System.out.println("Input 1 to LOGIN");
        System.out.println("Input 2 to REGISTER");
    }

    static void printCommandsForUser(){
        System.out.println("Input 0 to LOGOUT");
        System.out.println("Input 1 to ADD a NEW ADD");
        System.out.println("Input 2 to PRINT YOUR ALL ADS");
        System.out.println("Input 3 to PRINT ALL ADS");
        System.out.println("Input 4 to PRINT ADD BY CATEGORY");
        System.out.println("Input 5 to DELETE YOUR ALL ADS");
        System.out.println("Input 6 to DELETE AD BY TITLE");
    }

    public static void main(String[] args) {

        boolean isRun = true;

        while (isRun){
            printCommands();
            String command = scanner.nextLine();
            switch (command){
                case EXIT:
                    isRun = false;
                    System.exit(0);
                    break;
                case LOGIN:
                    login();
                    break;
                case REGISTER:
                    register();
                    break;
                default:
                    break;
            }
        }
    }

    public static void login() {
        if (userStorage.map.size() == 0) {
            System.out.println("Please register");
            register();
        } else {
            System.out.println("Input your phone number");
            try {
                String phone = scanner.nextLine();
                System.out.println("Input your password");
                String password = scanner.nextLine();
                if (!userStorage.map.containsKey(phone) || !userStorage.map.get(phone).getPassword().equals(password)) {
                    throw new LoginRegisterExceptions("Wrong phone number or password, or User has not been registered yet!");
                }
                System.out.println("Welcome to your account " + userStorage.map.get(phone).getName() + " " +
                        userStorage.map.get(phone).getSurname() + " !");
                System.out.println("Choose one of the following commands");
                for (int i = 0; i < 25; i++) {
                    printCommandsForUser();
                    String command1 = scanner.nextLine();
                    switch (command1) {
                        case LOGOUT:
                            return;
                        case ADD_NEW_AD:
                            addAd();
                            break;
                        case PRINT_MY_ALL_ADS:
                            printMyAllAds();
                            break;
                        case PRINT_ALL_ADS:
                            printAllAds();
                            break;
                        case PRINT_AD_BY_CATEGORY:
                            printAdByCategory();
                            break;
                        case DELETE_MY_ALL_ADS:
                            deleteMyAllAds();
                            break;
                        case DELETE_AD_BY_TITLE:
                            deleteAdByTitle();
                            break;
                        default:
                            System.err.println("Wrong command!");
                            printCommandsForUser();
                            break;
                    }

                }
            } catch (LoginRegisterExceptions e) {
                System.out.println(e.getMessage());
                login();
            }
        }
    }

    public static void register() {
        System.out.println("Input user name");
        String name = scanner.nextLine();
        System.out.println("Input user surname");
        String surname = scanner.nextLine();
        System.out.println("Choose user gender, input 0 if Male, input 1 if Female");
        String gender = scanner.nextLine();
        switch (gender) {
            case Male:
                gender = "Male";
                break;
            case Female:
                gender = "Female";
                break;
            default:
                System.err.println("Register failed! Try again!");
                register();
                break;
        }
        System.out.println("Input user age");
        try {
            int age = Integer.parseInt(scanner.nextLine());
            if (age < 18 || age >= 70){
                System.out.println("User must be older than 18");
                return;
            }
            System.out.println("Input user phone number");
            String phone = scanner.nextLine();
            System.out.println("Create user password");
            String password = scanner.nextLine();
            if (password.isEmpty()){
                System.out.println("Password field is empty, please create a password!");
                return;
            }
            if (userStorage.map.containsKey(phone)) {
                throw new LoginRegisterExceptions("Account already exist!");
            }
            User user = new User(name, surname, gender, age, phone, password);
            System.out.println(user.toString());
            userStorage.addUser(user);
            System.out.println("User account was created successfully!");
        } catch (LoginRegisterExceptions e) {
            System.out.println(e.getMessage());
            register();
        }
    }

    public static void addAd() {
        System.out.println("Please input a count of ads to create");
        try {
            String count = scanner.nextLine();
            Integer number = Integer.parseInt(count);
            if (number instanceof Number) {
                for (int i = 0; i < number; i++) {
                    System.out.println("Input Ad title");
                    String title = scanner.nextLine();
                    System.out.println("Input Ad text");
                    String text = scanner.nextLine();
                    System.out.println("Input Ad price");
                    Double price = Double.parseDouble(scanner.nextLine());
                    if (price instanceof Number) {
                        DateTimeFormatter mediumDate = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                        String dateTime = LocalDateTime.now().format(mediumDate);
                        System.out.println("Input Ad category");
                        String category = scanner.nextLine();
                        System.out.println("Please input keyword for your ad");
                        String idNumber = scanner.nextLine();
                        System.out.println("Please confirm by your phone number");
                        String phone = scanner.nextLine();
                        String idNumber1 = category.concat(idNumber.concat(phone));
                        Advertisement ad = new Advertisement(title, text, price, dateTime, category, idNumber1,
                                userStorage.map.get(phone));
                        advertisementStorage.list.put(idNumber1, ad);
                        System.out.println("Ad was added successfully");
                        System.out.println(ad);
                        FileUtil.AdStorageSerialize(advertisementStorage.list);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("Please input a number");
            addAd();
        }
    }

    public static void printMyAllAds(){

        System.out.println("Input your phone to see the ads list");
        String phone = scanner.nextLine();
        System.out.println("Here is your ads list:");
        advertisementStorage.printMyAllAds(phone);
    }

    public static void printAllAds(){

        advertisementStorage.printAllAds();
    }

    public static void printAdByCategory(){
        System.out.println("Please input category");
        String category = scanner.nextLine();
        advertisementStorage.printAdByCategory(category);
    }

    public static void deleteMyAllAds(){

        System.out.println("Please input your phone number to delete your ads");
        String phone = scanner.nextLine();
        advertisementStorage.deleteMyAllAds(phone);

    }

    public static void deleteAdByTitle (){

        System.out.println("Input ad title and your phone number to delete");
        System.out.print("ad title: ");
        String title = scanner.nextLine();
        System.out.print("phone number: ");
        String phone = scanner.nextLine();
            advertisementStorage.deleteAdByTitle(title, phone);
        }

}



