package SixLayer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;
import java.util.*;

public class SixTest {
	public static void main(String[] args) {
		// Initialize data structure
        Map<String, MenuItem> topLevelMap = new LinkedHashMap<>();

        // Read the input file
        try (BufferedReader reader = new BufferedReader(new FileReader("inputSixLayer.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length < 1) continue;

                String topTitle = parts[0];
                String secondTitle = parts.length > 1 ? parts[1] : null;
                String thirdTitle = parts.length > 2 ? parts[2] : null;
                String fourthTitle = parts.length > 3 ? parts[3] : null;
                String fifthTitle = parts.length > 4 ? parts[4] : null;
                String sixthTitle = parts.length > 5 ? parts[5] : null;

                // Ensure top-level item exists 
                //如果key對應的value不存在，則使用獲取remappingFunction重新計算後的值，並保存為該key的value，否則返回value。
                MenuItem topLevel = topLevelMap.computeIfAbsent(topTitle, MenuItem::new); 

                // Ensure second-level item exists
                MenuItem secondLevel = getOrCreateChild(topLevel, secondTitle);

                // Ensure third-level item exists
                MenuItem thirdLevel = getOrCreateChild(secondLevel, thirdTitle);
                
                // Fourth-level
                MenuItem fourthLevel = getOrCreateChild(thirdLevel, fourthTitle);
                
                // Fifth-level
                MenuItem fifthLevel = getOrCreateChild(fourthLevel, fifthTitle);
                
                // Sixth-level
                getOrCreateChild(fifthLevel, sixthTitle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // Convert to output format
        MenuData menuData = new MenuData();
        menuData.setData(new ArrayList<>(topLevelMap.values()));

        // Serialize to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter("output.json")) {
            gson.toJson(menuData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = gson.toJson(menuData);
        System.out.println(str);
        System.out.println("output.json generated successfully.");     
	}
	
	private static MenuItem getOrCreateChild(MenuItem parent, String title) {
        // Find existing child or create a new one if it doesn't exist
		if(title == null) {
    		return null;
    	} else {
	        return parent.getMenu().stream()
	            .filter(item -> item.getTitle().equals(title))
	            .findFirst()
	            .orElseGet(() -> {
	                MenuItem newItem = new MenuItem(title);
	                parent.getMenu().add(newItem);
	                return newItem;
	            });
    	}
    }
}
