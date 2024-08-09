package com.atguigu01.file;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import com.pcsetting.sample.MyHello;

class FileTest1 {
	/*
    獲取文件和目錄基本信息
    * public String getName() ：獲取名稱
    * public String getPath() ：獲取路徑
    * `public String getAbsolutePath()`：獲取絕對路徑
    * public File getAbsoluteFile()：獲取絕對路徑表示的文件
    * `public String getParent()`：獲取上層文件目錄路徑。若無，返回null
    * public long length() ：獲取文件長度（即：字節數）。不能獲取目錄的長度。
    * public long lastModified() ：獲取最後一次的修改時間，毫秒值
    * */
	
	@Test
	public void test1() {
		File file1 = new File("hello.txt");
		System.out.println(file1.getName());
		System.out.println(file1.getPath());
		System.out.println(file1.getAbsolutePath());
		System.out.println(file1.getAbsoluteFile());
		System.out.println(file1.getParent());
		System.out.println(file1.getAbsoluteFile().getParent());
		System.out.println(file1.length());
		System.out.println(file1.lastModified());
	}

	@Test
	public void test2() {
		File file1 = new File("D:\\io\\io1");
		System.out.println(file1.getName());
		System.out.println(file1.getPath());
		System.out.println(file1.getAbsolutePath());
		System.out.println(file1.getAbsoluteFile());
		System.out.println(file1.getParent());
		System.out.println(file1.getAbsoluteFile().getParent());
		System.out.println(file1.length());
		System.out.println(file1.lastModified());
	}
	
	/*
    列出目錄的下一級
    * public String[] list() ：返回一個String數組，表示該File目錄中的所有子文件或目錄。
    * public File[] listFiles() ：返回一個File數組，表示該File目錄中的所有的子文件或目錄。
    * */
	@Test
	public void test3() {
		//public String[] list()
		File file1 = new File("D:\\io");
		String[] fileArr = file1.list();
		for(String s: fileArr) {
			System.out.println(s);
		}
		
		System.out.println();
		File[] files = file1.listFiles();
		for(File f: files) {
			System.out.println(f.getName());
		}
	}
	
	
	/*
    File類的重命名功能
    - public boolean renameTo(File dest):把文件重命名為指定的文件路徑。

    舉例：
    file1.renameTo(file2):要想此方法執行完，返回true。要求：
       file1(old)必須存在，且file2(new)必須不存在，且file2所在的文件目錄需要存在。
    * */
	
	@Test
	public void test4() {
		File file1 = new File("hello.txt");
		File file2 = new File("d:\\io\\abc.txt");
		
		boolean renameSuccess = file1.renameTo(file2);
		System.out.println(renameSuccess ? "rename成功" : "rename失敗");
	}
	
	/*
    判斷功能的方法
    - `public boolean exists()` ：此File表示的文件或目錄是否實際存在。
    - `public boolean isDirectory()` ：此File表示的是否為目錄。
    - `public boolean isFile()` ：此File表示的是否為文件。
    - public boolean canRead() ：判斷是否可讀
    - public boolean canWrite() ：判斷是否可寫
    - public boolean isHidden() ：判斷是否隱藏
    * */
	
	@Test
	public void test5() {
		File file1 = new File("d:\\io\\abc.txt");
		System.out.println(file1.exists());
		System.out.println(file1.isDirectory());
		System.out.println(file1.isFile());
		System.out.println(file1.canRead());
		System.out.println(file1.canWrite());
		System.out.println(file1.isHidden());
		
		System.out.println();
		File file2 = new File("d:\\io");
		System.out.println(file2.exists());
		System.out.println(file2.isDirectory());
		System.out.println(file2.isFile());
		System.out.println(file2.canRead());
		System.out.println(file2.canWrite());
		System.out.println(file2.isHidden());
	}
	
	/*
    創建、刪除功能
    - `public boolean createNewFile()` ：創建文件。若文件存在，則不創建，返回false。
    - `public boolean mkdir()` ：創建文件目錄。如果此文件目錄存在，就不創建了。如果此文件目錄的上層目錄不存在，也不創建。
    - `public boolean mkdirs()` ：創建文件目錄。如果上層文件目錄不存在，一並創建。
    - `public boolean delete()` ：刪除文件或者文件夾
      刪除注意事項：① Java中的刪除不走回收站。② 要刪除一個文件目錄，請注意該文件目錄內不能包含文件或者文件目錄。
    * */
	
	@Test
	public void test6() throws IOException {
		File file1 = new File("d:\\io\\def.txt");
		if(!file1.exists()) {
			boolean isSuccessed = file1.createNewFile();
			if(isSuccessed) {
				System.out.println("創建成功");
			}
		} else {
			System.out.println("此文件已存在");
			
			System.out.println(file1.delete() ? "文件刪除成功" : "文件刪除失敗");
		}
	}
	
	@Test 
	public void test7() throws IOException {
		//前提：d:\\io文件目錄存在，io2或io3目錄是不存在的。
		File file1 = new File("d:\\io\\dir1");
		System.out.println(file1.mkdir()); //true
		
		File file2 = new File("d:\\io\\dir2");
		System.out.println(file2.mkdirs()); //true
		
	}
	
	@Test
	public void test8() {
		//前提：d:\\io文件目錄存在，io2或io3目錄是不存在的。
		File file1 = new File("d:\\io\\io2\\io4");
		System.out.println(file1.mkdir()); //false
		
		File file2 = new File("d:\\io\\io3\\io5");
		System.out.println(file2.mkdirs()); //false
	}
	
	@Test
	public void test9() {
		File file1 = new File("d:\\io\\io3");
		System.out.println(file1.delete()); //false, io3裡有東西, 刪不掉
	}
	
}
