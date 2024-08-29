package link;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class LinkTest {
	public static void main(String[] args)  {
		Properties properties = new Properties();
		String configFile = "config.properties";
		try {
			properties.load(new FileInputStream(configFile));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String filepath = properties.getProperty("filepath");
		String outputpath = properties.getProperty("outputpath");
		
		// 指定excel文件，創建緩存輸入流
		BufferedInputStream inputStream = null;
		try {
			inputStream = new BufferedInputStream(
					new FileInputStream(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// 直接傳入輸入流即可，此時excel就已經解析了
		XSSFWorkbook workbook = null;
		try {
			workbook = new XSSFWorkbook(inputStream);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// 選擇要處理的sheet名稱
		XSSFSheet sheet = workbook.getSheet("頁面清單( 地球調整版本 )");
		
		Map<String, MenuItem> topLevelMap = new LinkedHashMap<>(); //創建map
		HashSet<String> adSet = new HashSet<String>(); //Set儲存重複條目的廣告
		
		for(int i=76; i<977; i++) {
			XSSFRow row = sheet.getRow(i);
			if(row.getCell(23) != null && row.getCell(23).getCellType() == CellType.STRING
					&& !Objects.equals(row.getCell(6).getStringCellValue(), "#")) 
				continue; //非選單項目跳過
			int len = getLength(row);
			
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
            
            if(Objects.equals(row.getCell(6).getStringCellValue(), "#")) {  //處理ad
            	String FullTitle = topTitle + secondTitle;
            	if(!adSet.contains(FullTitle)) { 
            		secondLevel.ad = new ArrayList<Ad>();
            		adSet.add(FullTitle); //儲存看過的條目
            	} 
            	if(!Objects.equals(row.getCell(23).getStringCellValue(),"v")) {
            		//設定廣告屬性
            		secondLevel.ad.add(new Ad(row.getCell(9).getStringCellValue(),
                			row.getCell(8).getStringCellValue(),row.getCell(6).getStringCellValue()));
            	}		
            }			
		}
		// Convert to output format
        MenuData menuData = new MenuData();
        menuData.setData(new ArrayList<>(topLevelMap.values()));
        
        // Serialize to JSON
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Writer writer = new FileWriter(outputpath)) {
            gson.toJson(menuData, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str = gson.toJson(menuData);
        System.out.println(str);	
		try {
			if(workbook != null)
				workbook.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private static MenuItem getOrCreateChild(MenuItem parent, String title, String grandChild , XSSFRow row) {
        // Find existing child or create a new one if it doesn't exist
		if(title == null) { //title為null表示沒child
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
	            	} else if(link.startsWith("/http")) { //外部連結
	            		newItem = new MenuItem(title, true);
	            	} else {
	            		newItem = new MenuItem(title, link);//child是leaf
	            	}	                
	                parent.getMenu().add(newItem);
	                return newItem;
	            });
    	}
    }
	
	public static String getLink(XSSFRow row) {
		int len = 0;
		while(row.getCell(len+11) != null) {
			if(row.getCell(len+11).getCellType() == CellType.BLANK)
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
	
	public static int getLength(XSSFRow row) {
		int len = 0;
		while(row.getCell(len) != null) {
			if(row.getCell(len).getCellType() == CellType.BLANK)
				break;
			else { len++; }		
		}
		return len;
	}
}

