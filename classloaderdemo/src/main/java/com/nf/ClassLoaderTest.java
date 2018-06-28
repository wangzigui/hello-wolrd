package com.nf;

public class ClassLoaderTest {
	
	public static void main(String[] args) {
		ClassLoader cl1 = ClassLoaderTest.class.getClassLoader();
		
		System.out.println("ClassLoader is:"+ cl1.toString());
		System.out.println("ClassLoader\'s parent is:"+ cl1.getParent().toString());
		System.out.println("ClassLoader\'s grand parent is:"+ cl1.getParent().getParent().toString());
		ClassLoader int1 = int.class.getClassLoader();
		
		System.out.println("intClassLoader is:"+ int1.toString());
	}
}
