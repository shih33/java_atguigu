package com.atguigu01.file.exer2;

import java.io.File;
import java.io.FilenameFilter;

import org.junit.jupiter.api.Test;

/*
	練習：
	判斷指定目錄下是否有後綴名為.jpg的文件，如果有，就輸出該文件名稱

	提示：File類提供了文件過濾器方法(拓展)
	public String[] list(FilenameFilter filter)
*/

public class exer2 {
	@Test
	public void test1() {
		File dir = new File("d:\\io");
		
		//方式1
//		String[] fileArr = dir.list();
//		for(String s: fileArr) {
//			if(s.endsWith(".jpg")) {
//				System.out.println(s);
//			}
//		}
		
		//方式2
		//public String[] list(FilenameFilter filter)
		String[] listFiles = dir.list(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) { //name:即為子文件或子文件目錄的名稱
//				if(name.endsWith(".jpg")) {
//					return true;
//				} else {
//					return false;
//				}
				return name.endsWith(".jpg");
			}
		});
		
		for(String s : listFiles) {
			System.out.println(s);
		}
 		
	}
	
	/*
	String[] fileArr = dir.list();
		for(String s: fileArr) {
			String subStr = s.substring(s.length()-4);
			if(subStr.equals(".jpg")) {
				System.out.println(s);
			}
		}
	 */
	
}
