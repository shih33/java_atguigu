package link;

import java.util.ArrayList;
import java.util.List;

public class MenuItem {
	private String title;
	private String link;
	Boolean external;
    private List<MenuItem> menu = new ArrayList<>();
    
    
    public MenuItem() {}
    
	public MenuItem(String title) {
		super();
		this.title = title;
	}

	public MenuItem(String title, String link) {
		super();
		this.title = title;
		this.link = link;
	}

	public MenuItem(String title, Boolean external) {
		super();
		this.title = title;
		this.external = external;
	}

	public String getTitle() {
        return title;
    }

    public List<MenuItem> getMenu() {
        return menu;
    }

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

//	public boolean isExternal() {
//		return external;
//	}
//
//	public void setExternal(boolean external) {
//		this.external = external;
//	}

}
