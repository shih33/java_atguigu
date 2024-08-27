package link;

import java.util.ArrayList;
import java.util.List;

public class MenuData {
	private List<MenuItem> data = new ArrayList<>();

    public List<MenuItem> getData() {
		return data;
	}

	public void setData(List<MenuItem> data) {
        this.data = data;
    }
}
