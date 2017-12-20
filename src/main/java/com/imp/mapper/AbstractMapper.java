package com.imp.mapper;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dbquery.util.Model;
import com.imp.dao.QueryObject;
import com.imp.system.Column;
import com.imp.system.Table;


public abstract class AbstractMapper extends QueryObject {

	public Object insertModel(Object object) {
		Class<?> clazz =  object.getClass();
		
		StringBuilder sb = new StringBuilder();
		StringBuilder values = new StringBuilder();
		List<Object> list = new ArrayList<>();
		
		Table annotation = clazz.getAnnotation(Table.class);
		String tableName = annotation == null ? "" : annotation.value(); 
		
		sb.append("INSERT INTO ").append(tableName).append(" (");
		values.append(" )VALUES(");
		
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				Column annot = field.getAnnotation(Column.class);
				Object fieldValue = field.get(object);
				if (annot != null && fieldValue != null && !field.getName().equals("id")) {
					sb.append(", ").append(field.getName());
					values.append(", ? ");
					list.add(fieldValue);
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取到object的该属性值
		
		values.append(")");
		sb.append(values.toString().replaceFirst(",", ""));
		String sql = sb.toString().replaceFirst(",", "");
		System.out.println(sql);
		System.out.println("prepareStatement: "+list.toString());
		try {
			Method m1 = clazz.getDeclaredMethod("setId",Integer.class);
			m1.invoke(object, this.insert(sql, list.toArray(new Object[list.size()])));
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return object;
		
	}
	
	public List<Object> selectByModel(Object object){
		return selectByModel(object, null,null,false);
	}
	public List<Object> selectByModel(Object object,Set<String> conditionSet){
		return selectByModel(object, conditionSet,null,false);
	}
	
	public List<Object> selectByModel(Object object,Set<String> conditionSet, String orderColumn ,boolean desc) {
		Class<?> clazz =  object.getClass();
		List<Object> models = new ArrayList<>();
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>();
		
		Table annotation = clazz.getAnnotation(Table.class);
		String tableName = annotation == null ? "" : annotation.value(); 
		
		sb.append("SELECT * FROM ").append(tableName).append(" WHERE 0=0 ");

		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				Column annot = field.getAnnotation(Column.class);
				Object fieldValue = field.get(object);
				if (annot != null && field.getType() != byte[].class && fieldValue != null && (conditionSet == null || conditionSet != null && conditionSet.contains(field.getName()))) {
					sb.append(" AND ").append(field.getName()).append(" = ?");
					list.add(fieldValue);
				}
			}
			
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取到object的该属性值
		
		if (orderColumn != null && orderColumn != "") {
			if (desc) {
				sb.append(" order by ").append(orderColumn).append(" desc");
			}else{
				sb.append(" order by ").append(orderColumn);
			}
		}
		String sql = sb.toString();
		System.out.println(sql);
		System.out.println("prepareStatement: "+list.toString());
		List<Map<String, Object>> maps = this.select(sql, list.toArray(new Object[list.size()]));
		if (maps != null) {
			for (Map<String,Object> map : maps) {
				models.add(Model.parseObject(map, object.getClass()));
			}
		}
		return models;
	}
	
	public Object updateByModel(Object object,Set<String> conditionSet) {
		Integer id = null;
		Class<?> clazz =  object.getClass();
		
		StringBuilder sb = new StringBuilder();
		StringBuilder condition = new StringBuilder();
		List<Object> list = new ArrayList<>();
		List<Object> conditionValues = new ArrayList<>();
		
		Table annotation = clazz.getAnnotation(Table.class);
		String tableName = annotation == null ? "" : annotation.value(); 
		
		sb.append("UPDATE ").append(tableName).append(" SET ");
		
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				Column annot = field.getAnnotation(Column.class);
				Object fieldValue = field.get(object);
				if (annot != null && fieldValue != null && (conditionSet == null || conditionSet != null && !conditionSet.contains(field.getName()))) {
					sb.append(", ").append(field.getName()).append(" = ? ");
					list.add(fieldValue);
					if (conditionSet == null && field.getName().equals("id")) {
						id = (Integer) fieldValue;
					}
				}else if (field.getType() != Byte[].class && (conditionSet != null && conditionSet.contains(field.getName()))) {
					condition.append(" AND ").append(field.getName()).append(" = ?");
					conditionValues.add(fieldValue);
				}

			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取到object的该属性值
		
		String sql = sb.toString().replaceFirst(",", "") + " WHERE 0 = 0 " + condition.toString();
		System.out.println(sql);
		for(Object o : conditionValues){
			list.add(o);
		}
		System.out.println("prepareStatement: " + list.toString());
		return this.update(sql, list.toArray(new Object[list.size()])) >=0 ? object : null;
	}
	
	
	public Object updateById(Object object) {
		Integer id = null;
		Class<?> clazz =  object.getClass();
		
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>();
		
		Table annotation = clazz.getAnnotation(Table.class);
		String tableName = annotation == null ? "" : annotation.value(); 
		
		sb.append("UPDATE ").append(tableName).append(" SET ");
		
		Field[] fields = clazz.getDeclaredFields();
		try {
			for (Field field : fields) {
				int mod = field.getModifiers();
				if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
					continue;
				}
				field.setAccessible(true);
				Column annot = field.getAnnotation(Column.class);
				Object fieldValue = field.get(object);
				if (annot != null && fieldValue != null) {
					sb.append(", ").append(field.getName()).append(" = ? ");
					list.add(fieldValue);
					if (field.getName().equals("id")) {
						id = (Integer) fieldValue;
					}
				}
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 获取到object的该属性值
		
		String sql = sb.toString().replaceFirst(",", "") + " WHERE id = " + id;
		System.out.println(sql);
		System.out.println("prepareStatement: "+list.toString());
		return this.update(sql, list.toArray(new Object[list.size()])) >= 0? object : null;
	}
	
	public int deleteById(Integer id, Class<?> clazz) {
		
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<>();
		list.add(id);
		Table annotation = clazz.getAnnotation(Table.class);
		String tableName = annotation == null ? "" : annotation.value(); 
		sb.append("DELETE FROM ").append(tableName).append(" WHERE id = ?");
		String sql = sb.toString();
		System.out.println(sql);
		System.out.println("prepareStatement: "+list.toString());
		return this.delete(sql, list.toArray(new Object[list.size()]));
	}
	
	public int deleteByIds(List<Integer> ids, Class<?> clazz) {
		
		StringBuilder sb = new StringBuilder();
		List<Integer> list = ids;
		Table annotation = clazz.getAnnotation(Table.class);
		String tableName = annotation == null ? "" : annotation.value(); 
		sb.append("DELETE FROM ").append(tableName).append(" WHERE id IN ( ");
		for(int i=0; i<ids.size(); i++){
			sb.append(", ?");
		}
		sb.append(" )");
		String sql = sb.toString().replaceFirst(",", "");
		System.out.println(sql);
		System.out.println("prepareStatement: "+list.toString());
		return this.delete(sql, list.toArray(new Object[list.size()]));
		
	}
	
	public boolean saveOrUpdate(Object object,Set<String> conditionSet){
		boolean bool = true;
		List<?> list = selectByModel(object, conditionSet);
		if (list.isEmpty()) {
			insertModel(object);
			//为了防止批量更新将表中全部记录更新  ，只有list.size() == 1时在更新
		}else if (list.size() == 1) {
			updateByModel(object, conditionSet);
		}else {
			bool = false;
		}
		return bool;
		
	}
	
	public boolean saveOrIgnore(Object object,Set<String> conditionSet){
		boolean bool = true;
		List<?> list = selectByModel(object, conditionSet);
		if (list.isEmpty()) {
			insertModel(object);
		}
		return bool;
		
	}
//	public Object updateLinkByModel(UserModel model){
//		String sql = "update tbl_project_user_link set inOutType = ? where userId = ? and projectId = ?";
//		super.update(sql, new Object[]{model.getInOutStat(), model.getUserId(), model.getProjectId()});
//		return model;
//	}


}
