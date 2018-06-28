package com.nf;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TestClassLoader extends ClassLoader {

	private String classpath;

	public TestClassLoader(String classpath) {
		this.classpath = classpath;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		String fileName = getFileName(name);

		File file = new File(classpath, fileName);

		try {
			FileInputStream fis = new FileInputStream(file);
			
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte b = 0;
			int len = 0;
			try {
				while((len = fis.read()) != -1)
				{
					//将数据异或一个数字2进行解密
					b = (byte) (len ^ 2);
					bos.write(b);
				}
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] date = bos.toByteArray();
			
			fis.close();
			bos.close();
			
			return defineClass(name, date, 0, date.length);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.findClass(name);
	}

	// 获取要加载 的class文件名
	private String getFileName(String name) {
		// TODO Auto-generated method stub
		int index = name.lastIndexOf('.');
		if (index == -1) {
			return name + ".classen";
		} else {
			return name.substring(index + 1) + ".classen";
		}
	}
}
