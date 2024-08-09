package com.atguigu01.file.exer3;

import java.io.File;

import org.junit.jupiter.api.Test;

public class exer3 {
	//public void printFileName(File file)  //file可能是文件，也可能是文件目錄
	@Test
	public void test1() {
		File dir = new File("d:\\io");
		printFileName(dir);
	}
	
	public void printFileName(File file) {
		if(file.isFile()) {
			System.out.println(file.getName());
		} else if(file.isDirectory()) {
			File[] files = file.listFiles();
			for(File f: files) {
				printFileName(f);
			}
		}
	}
	
}
