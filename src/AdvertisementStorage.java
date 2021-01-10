import java.util.HashMap;
import java.util.Iterator;

public class AdvertisementStorage {

    HashMap<String, Advertisement> list = new HashMap<>();

    public AdvertisementStorage() {
    }

    public void printMyAllAds(String phone) {

        for (String s : list.keySet()) {
            if(s.contains(phone)){
                System.out.println(list.get(s));
            }
        }
    }

    public void printAllAds() {
        System.out.println(list.values());
    }

    public void printAdByCategory(String category){

        for (Advertisement value : list.values()) {
            if (value.getCategory().contains(category)) {
                String s = value.getIdNumber();
                System.out.println(list.get(s));
            }
        }
    }

    public void deleteMyAllAds(String phone) {

        Iterator<String> iterator = list.keySet().iterator();
        while (iterator.hasNext()) {
            String key = iterator.next();
            if (key.contains(phone)) {
                iterator.remove();
            }
        }
    }

    public void deleteAdByTitle(String title, String phone) {
        Iterator<String> iterator_1 = list.keySet().iterator();
        Iterator<Advertisement> iterator_2 = list.values().iterator();
        while (iterator_2.hasNext() && iterator_1.hasNext()){
            String keyValue = iterator_2.next().toString();
            String key = iterator_1.next();
            if (key.contains(phone) && keyValue.contains(title)){
                iterator_1.remove();
            }
        }
    }
}
