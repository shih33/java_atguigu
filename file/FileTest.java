package com.atguigu01.file;

import java.io.File;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class FileTest {
	/*
     * `public File(String pathname) ` ：以pathname為路徑創建File對象，可以是絕對路徑或者相對路徑，如果pathname是相對路徑，則默認的當前路徑在系統屬性user.dir中存儲。
     * `public File(String parent, String child) ` ：以parent為父路徑，child為子路徑創建File對象。
     * `public File(File parent, String child)` ：根據一個父File對象和子文件路徑創建File對象
     *
     * 文件的路徑表示方式：
     * 方式1：絕對路徑：以windows操作系統為例，包括盤符在內的文件或文件目錄的完整路徑。
     * 方式2：相對路徑：相對於某一個文件目錄來講的相對的位置。
     *          在IDEA中，如果使用單元測試方法：相對於當前的module來講
     *                  如果使用main()方法：相對於當前的project來講
    * */
	
	@Test
	public void test1() {
		//public File(String pathname)
		File file1 = new File("d:/io/hello.txt");
		
		File file2 = new File("ab");
		System.out.println(file2.getAbsolutePath());
	}
	
	
	@Test
	public void test2() {
		//public File(String parent, String child)
		//参数1：一定是一個文件目錄
        //参数2：可以是一個文件，也可以是一個文件目錄
		File file1 = new File("d:\\io, abc.txt");
		File file2 = new File("abc", "a12");
		
		//public File(File parent, String child)
		File file3 = new File(file2, "ab.txt");
	}

}
