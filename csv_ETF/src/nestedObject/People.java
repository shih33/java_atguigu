package nestedObject;

import java.math.BigDecimal;

public class People {
    private String name;
    private int age;
    private UserAddress address;
    private String[] month;

    public People(String name, int age, UserAddress address, String[] month) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.month = month;
    }
}
