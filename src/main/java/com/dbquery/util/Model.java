/**
 * 实体模型包
 */
package com.dbquery.util;

import com.imp.system.Column;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Frank
 * 用户对模型的快速生成和解析
 */
public class Model {


	/**
	 * 将Map键值对模型化
	 * @param mapPair 键值对
	 * @param className 目标类型
	 * @return 返回创建的对象
	 * */
	public static Object parseObject(Map<String, Object> mapPair, Class<?> className) {
		if (null == mapPair) {
			return null;
		}
		try {
			Object destObj = className.newInstance();// 创建对象实例
			Field[] fields = null;
			Class<?> clazz = className;
			//循环获取父类属性
			while (clazz != Object.class){
				fields = clazz.getDeclaredFields(); // 获取声明的属性域
				setValue( mapPair, destObj, fields);
				clazz = clazz.getSuperclass();
			}
			return destObj;
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void  setValue(Map<String, Object> mapPair, Object destObj, Field[] fields){
		for (Field field : fields) {
			int mod = field.getModifiers();
			if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
				continue;
			}
			String column = null;
			field.setAccessible(true);
			Column annot = field.getAnnotation(Column.class);
			if (annot != null){
				column = annot.value();
			}
			if(column == null || column.equals("")){
				column = field.getName();
			}
			Object fieldValue = mapPair.get(column);// 获取属性值
			if (null == fieldValue) { // 属性值为空不加入到属性中
				continue;
			}
			try {
				if (fieldValue.getClass() == BigDecimal.class ) {
					field.set(destObj, ((BigDecimal)fieldValue).intValue());
				}else if (fieldValue.getClass() == Long.class) {
					field.set(destObj, (int)(((Long)fieldValue).longValue()));
				}else if (fieldValue.getClass() == Timestamp.class && field.getType() == String.class) {
					field.set(destObj, DatetimeUtil.toDefaultDateString((Timestamp)fieldValue));
				}else if (fieldValue.getClass() == Float.class && field.getType() == String.class) {
					field.set(destObj, String.valueOf(fieldValue));
				}else if (fieldValue.getClass() == Float.class && field.getType() == BigDecimal.class) {
					field.set(destObj, new BigDecimal(String.valueOf(fieldValue)));
				}else if (fieldValue.getClass() == Double.class && field.getType() == BigDecimal.class) {
					field.set(destObj, new BigDecimal(String.valueOf(fieldValue)));
				}else if (fieldValue.getClass() == String.class && field.getType() == BigDecimal.class) {
					field.set(destObj, new BigDecimal((String)fieldValue));
				}else {
					field.set(destObj, fieldValue);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				System.out.println("field : " + field + ", destObj " + destObj + ", fieldValue "  + fieldValue);
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 将实体类序列化为键值对
	 * @param object 要序列化的实体
	 * @return 返回序列化后的键值对
	 * */
	public static Map<String, Object> serializeModel(Object object) {
		if (null == object) {
			return null; // 防止空对象
		}
		try {
			Field[] allDeclaredFields = object.getClass().getDeclaredFields();// 获取以声明的属性域
			Map<String, Object> objectPair = new HashMap<>();// 键值对容器

			for (Field field : allDeclaredFields) { // 遍历属性
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}

				// 设置可访问性
				field.setAccessible(true);
				Object fieldValue = field.get(object); // 获取到object的该属性值
				if (null == fieldValue) {
					// 属性值为空
					continue;
				}

				// 属性值非空
				String fieldName = field.getName();
				objectPair.put(fieldName, fieldValue);

			}
			return objectPair;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
