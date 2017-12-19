package com.nf;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class IOUtil {

	public static void writeFile(String str) {
		FileOutputStream fis = null;
		OutputStreamWriter isr = null;
		PrintWriter br = null;
		try {
			fis = new FileOutputStream("aa1.txt", true);
			isr = new OutputStreamWriter(fis);
			br = new PrintWriter(isr);
			br.write(str);
			br.flush();
			br.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public static boolean copyFile(String copyFilePath,String newFilePath) throws IOException {
		FileOutputStream fos = null;
		BufferedOutputStream bos = null;
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {

			fos = new FileOutputStream(newFilePath);
			bos = new BufferedOutputStream(fos);
			byte[] bytes = new byte[2048];
			// kk_image.png
			fis = new FileInputStream(copyFilePath);
			bis = new BufferedInputStream(fis);	
			int by = 0;
			while ((by = bis.read(bytes)) != -1) {

				System.out.println("a:" + by);
				bos.write(bytes,0,by);

				bos.flush();
			}
			bis.close();
			bos.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			bis.close();
			bos.close();
		}
		
		return true;
	}

	public static void main(String[] args) throws IOException {

		boolean flag = copyFile("kk_image.png","image/kk_image1.png");
		System.out.println(flag);
		// writeFile(str);
	}
}
