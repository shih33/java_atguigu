package SixLayer;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	private String title;
    private List<MenuItem> menu = new ArrayList<>();

    public MenuItem(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

}
