package com.dao.client;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


public class ORMObject<T> {

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public void insertObject(T t) throws IllegalArgumentException, IllegalAccessException {
		StringBuffer sql = new StringBuffer();

		sql.append("insert into " + t.getClass().getSimpleName());

		Field[] fields = t.getClass().getDeclaredFields();
		StringBuffer fieldsString = new StringBuffer();
		StringBuffer valuesString = new StringBuffer();
		for (int i = 0; i < fields.length; i++) {

			fieldsString.append(fields[i].getName());

			fields[i].setAccessible(true);
			
			if(fields[i].get(t) instanceof String)
			{
				valuesString.append("'"+fields[i].get(t)+"'");
			}
			else
			{
				valuesString.append(fields[i].get(t));
			}

			if (i != fields.length - 1) {
				fieldsString.append(",");
				valuesString.append(",");
			}

		}

		sql.append("(").append(fieldsString.toString()).append(") ").append("values(").append(valuesString.toString())
				.append(")");
		jdbcTemplate.execute(sql.toString());
				
	}

	public void updateObject(Class clazz ,Map<String, Object> tableFields,Map<String, Object> params) throws IllegalArgumentException, IllegalAccessException {
		StringBuffer sql = new StringBuffer();

		sql.append("update " + clazz.getSimpleName() + " set ");

		StringBuffer values = new StringBuffer();
		StringBuffer paramStr = new StringBuffer();
		Set<Entry<String, Object>> tableFieldsSet = tableFields.entrySet();
		Set<Entry<String, Object>> paramsparams = params.entrySet();
		for(Entry<String, Object> tableField:tableFieldsSet)
		{
			if(tableField.getValue() instanceof String)
			{
				values.append(tableField.getKey()).append("=").append("'"+tableField.getValue()+"'");
			}
			else
			{
				values.append(tableField.getKey()).append("=").append(tableField.getValue());
			}
			
			values.append(",");
		}
		
		sql.append(values.substring(0, values.length()-1));
		
		sql.append(" where ");
		for(Entry<String, Object> param:paramsparams)
		{
			if(param.getValue() instanceof String)
			{
				paramStr.append(param.getKey()).append("=").append("'"+param.getValue()+"'");
			}
			else
			{
				paramStr.append(param.getKey()).append("=").append(param.getValue());
			}
			
			paramStr.append(" and ");
		}
		
		if(!paramsparams.isEmpty())
		{
			sql.append(paramStr.substring(0,paramStr.lastIndexOf("and")));
		}
		
		
		jdbcTemplate.execute(sql.toString());
	}
	
	@SuppressWarnings("unchecked")
	public List<TMP1> findObject(Class clazz ,Map<String, Object> tableFields,Map<String, Object> params) throws IllegalArgumentException, IllegalAccessException {
		String str = "select * from " +clazz.getSimpleName();
		
//		TMP1 obj =  jdbcTemplate.queryForObject(str,new Object[]{"aaassddd"}, new BeanPropertyRowMapper<TMP1>(TMP1.class));
//		List<TMP1> obj = jdbcTemplate.queryForList(str, new Object[]{"aaassddd"}, TMP1.class);
		
		List<TMP1> obj = jdbcTemplate.query(str, new BeanPropertyRowMapper<TMP1>(TMP1.class));
//		jdbcTemplate.e
		return obj;
	}
	
	

	public static void main(String[] args)
			throws IllegalArgumentException, IllegalAccessException, InstantiationException {
//		Account a = new Account();
//		a.setAccountId(212);
//		// a.setAddre("aaaa");
//		ORMObject obj = new ORMObject();
//		// obj.insertObject(a);
//		Map<String, Object> ss = new HashMap<String, Object>();
//		ss.put("accountid", 212);
//		ss.put("addre", "sss");
//		obj.updateObject(Account.class,ss,ss);
//		// System.out.println(getString(a, Account.class));
		
		String str = "sasd";
		System.out.println(str.substring(0,str.lastIndexOf("and")));
	}

//	public static String getString(Object o, Class<?> c) {
//		String result = c.getSimpleName() + ":";
//
//		// 获取父类，判断是否为实体类
//		if (c.getSuperclass().getName().indexOf("entity") >= 0) {
//			result += "\n<" + getString(o, c.getSuperclass()) + ">,\n";
//		}
//
//		// 获取类中的所有定义字段
//		Field[] fields = c.getDeclaredFields();
//
//		// 循环遍历字段，获取字段对应的属性值
//		for (Field field : fields) {
//			// 如果不为空，设置可见性，然后返回
//			field.setAccessible(true);
//
//			try {
//				// 设置字段可见，即可用get方法获取属性值。
//				result += field.getName() + "=" + field.get(o) + ",\n";
//			} catch (Exception e) {
//				// System.out.println("error--------"+methodName+".Reason
//				// is:"+e.getMessage());
//			}
//		}
//		if (result.indexOf(",") >= 0)
//			result = result.substring(0, result.length() - 2);
//		return result;
//	}

}
