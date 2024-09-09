package com.atguigu03.buffered;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.Test;

/*
 * 測試FileInputStream + FileOutputStream 複製文件
 * BufferedInputStream + BufferedOutputStream 複製文件
 * 
 * 測試二者的效率。
 */

public class CopyFileTest {
	@Test
	public void testSpendTime() {
		long start = System.currentTimeMillis();
		
		String src = "D:\\Users\\ysshih\\Desktop\\100MB.bin";
		String dest = "D:\\Users\\ysshih\\Desktop\\100MB-2.bin";
		
		//copyFileWithStream(src, dest); //10130
		copyFileWithBufferedStream(src, dest); //206
		
		long end = System.currentTimeMillis();
		System.out.println("花費的時間:" + (end - start));
	}
	
	/*
     * 使用BufferedInputStream + BufferedOutputStream 複製文件
     * */
	public void copyFileWithBufferedStream(String src, String dest) {
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null; 
					
		try {
			//1. 創建File類的對象
			File srcFile = new File(src);
			File destFile = new File(dest);
			
			//2. 創建相關的字節流，緩衝流
			FileInputStream fis = new FileInputStream(srcFile);
			FileOutputStream fos = new FileOutputStream(destFile);
			
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(fos);
			
			//3. 數據的讀入與寫出
			byte[] buffer = new byte[50]; 
			int len; //紀錄每次讀入到buffer中byte的個數
			while ((len = bis.read(buffer)) != -1) {
				bos.write(buffer, 0, len);
			}
			System.out.println("複製成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//4. 關閉資源
			try {
				if(bis != null)
					bis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(bos != null)
					bos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/*
     * 使用FileInputStream + FileOutputStream 複製文件
     * */
	public void copyFileWithStream(String src, String dest) {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		
		try {
			//1. 創建File類的對象
			File srcFile = new File(src);
			File destFile = new File(dest);
			
			//2. 創建相關的字節流
			fis = new FileInputStream(srcFile);
			fos = new FileOutputStream(destFile);
			
			//3. 數據的讀入與寫出
			byte[] buffer = new byte[50]; 
			int len; //紀錄每次讀入到buffer中byte的個數
			while ((len = fis.read(buffer)) != -1) {
				fos.write(buffer, 0, len);
			} 
			System.out.println("複製成功");
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//4. 關閉資源
			try {
				if(fis != null)
					fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
