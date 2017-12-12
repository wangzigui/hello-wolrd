package com.nf;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidDemo implements Serializable {
	public static void main(String[] args) {
		Student xiaoming = getBean();
		// xiaoming.setName("sdf");
		// xiaoming.setClassNum("1234");
		xiaoming.setId(6);
		List<String> validate = validate(xiaoming);
		if (!validate.isEmpty()) {
			validate.forEach(new Consumer<String>() {
				public void accept(String row) {
					System.out.println(row.toString());
				}
			});
		}

	}

	private static Student getBean() {
		Student bean = new Student();
		bean.setName(null);
		bean.setClassNum("北京");
		bean.setSchoolName(null);
		bean.setId(30);
		return bean;
	}

	private static ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

	public static <T> List<String> validate(T t) {
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);

		List<String> messageList = new ArrayList<String>();
		for (ConstraintViolation<T> constraintViolation : constraintViolations) {

			messageList.add(constraintViolation.getPropertyPath().toString() + ":" + constraintViolation.getMessage());
		}
		return messageList;
	}

}
