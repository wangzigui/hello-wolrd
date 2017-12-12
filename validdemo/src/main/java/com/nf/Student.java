package com.nf;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class Student {
	
	@NotNull(message="name 不能为null")
	private String name;
	
	@Max(value = 5)
	private int id;
	
	@Pattern(regexp = "1234")
	private String classNum;
	private String schoolName;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	@Override
	public String toString() {
		return "Student [name=" + name + ", id=" + id + ", classNum=" + classNum + ", schoolName=" + schoolName + "]";
	}
	
}
