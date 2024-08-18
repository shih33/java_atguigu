package nestedObject;

import com.google.gson.Gson;

public class gsonTest {
    public static void main(String[] args) {
        UserAddress address1 = new UserAddress("YangGuan", "Taipei");
        String[] month = {"Jan", "Feb"};
        People p1 = new People("Tim", 12, address1, month);
        Gson gson = new Gson();
        String json = gson.toJson(p1);
        System.out.println(json);
    }
}
