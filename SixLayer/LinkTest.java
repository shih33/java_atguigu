package link;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class LinkTest {
	public static void main(String[] args) throws IOException {
		// 指定excel文件，創建緩存輸入流
		BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream("C:\\Users\\ysshih\\eclipse-workspace\\poi\\menu.xlsx"));
		// 直接傳入輸入流即可，此時excel就已經解析了
		XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
		// 選擇要處理的sheet名稱
		XSSFSheet sheet = workbook.getSheet("頁面清單( 地球調整版本 )");
		Map<String, MenuItem> topLevelMap = new LinkedHashMap<>();
		
		for(int i=76; i<100; i++) {
			XSSFRow row = sheet.getRow(i);
			int len = 0;
			while(row.getCell(len) != null) {
				if(row.getCell(len).getCellType() == CellType.BLANK)
					break;
				else { len++; }		
			}
			//System.out.println(len);
			String topTitle = row.getCell(0).getStringCellValue();
            String secondTitle = len > 1 ? row.getCell(1).getStringCellValue() : null;
            String thirdTitle = len > 2 ? row.getCell(2).getStringCellValue() : null;
            String fourthTitle = len > 3 ? row.getCell(3).getStringCellValue() : null;
            String fifthTitle = len > 4 ? row.getCell(4).getStringCellValue() : null;
            String sixthTitle = len > 5 ? row.getCell(5).getStringCellValue() : null;

            //如果key對應的value不存在，則使用獲取remappingFunction重新計算後的值，並保存為該key的value，否則返回value。
            MenuItem topLevel = topLevelMap.computeIfAbsent(topTitle, MenuItem::new); //第一層
            MenuItem secondLevel = getOrCreateChild(topLevel, secondTitle, thirdTitle, row); //第二層
            MenuItem thirdLevel = getOrCreateChild(secondLevel, thirdTitle, fourthTitle, row); //第三層
            MenuItem fourthLevel = getOrCreateChild(thirdLevel, fourthTitle, fifthTitle, row); //第四層
            MenuItem fifthLevel = getOrCreateChild(fourthLevel, fifthTitle, sixthTitle, row); //第五層
            getOrCreateChild(fifthLevel, sixthTitle, null , row); //第六層
						
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
		workbook.close();
		
	}
	
	private static MenuItem getOrCreateChild(MenuItem parent, String title, String grandChild , XSSFRow row) {
        // Find existing child or create a new one if it doesn't exist
		if(title == null) {
    		return null;
    	} else {
	        return parent.getMenu().stream()
	            .filter(item -> item.getTitle().equals(title))
	            .findFirst()
	            .orElseGet(() -> {
	            	String link = getLink(row); 
	            	MenuItem newItem;
	            	if(grandChild != null) { //child不是leaf
	            		newItem = new MenuItem(title);
	            	} else if(link.startsWith("/http")) {
	            		newItem = new MenuItem(title, true);
	            	} else {
	            		newItem = new MenuItem(title, link);
	            	}
	                
	                parent.getMenu().add(newItem);
	                return newItem;
	            });
    	}
    }
	
	public static String getLink(XSSFRow row) {
		int len = 0;
		while(row.getCell(len) != null) {
			if(row.getCell(len).getCellType() == CellType.BLANK)
				break;
			else { len++; }		
		}
		String link = new String("");
    	for(int i=0; i<len; i++) {
    		link += '/';
    		link += row.getCell(i+11);
    	}
    	return link;
	}
}

