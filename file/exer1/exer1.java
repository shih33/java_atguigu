package com.atguigu01.file.exer1;

import java.io.File;

//創建一個與hello.txt文件在相同文件目錄下的另一個名為abc.txt文件
public class exer1 {
	public static void main(String[] args) {
		File file1 = new File("Hello.txt");
		System.out.println(file1.getAbsolutePath());
		
		//獲取file1的絕對路徑，獲取此路徑的上層文件目錄。
		//System.out.println(file1.getAbsoluteFile().getParent());
		File file2 = new File(file1.getAbsoluteFile().getParent(), "abc.txt");
		
		System.out.println(file2.getAbsolutePath());
		
	}
}
