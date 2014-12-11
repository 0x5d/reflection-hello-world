package com.talosdigital.reflection;

import java.lang.reflect.Field;

/**
 * 
 */

/**
 * @author castillobg
 *
 */
public class Logger {
	
	/**
	 * 
	 * @param object
	 * @return
	 * @throws IllegalAccessException
	 */
	public String inspectObject(Object object) throws IllegalAccessException{
		return inspectObject(object, 0);
	}
	
	/**
	 * 
	 * @param object
	 * @param levelsDeep
	 * @return
	 * @throws IllegalAccessException
	 */
	private String inspectObject(Object object, int levelsDeep) throws IllegalAccessException{
		StringBuilder description = new StringBuilder();
		description.append(getTabs(levelsDeep));
		description.append(getClassDescription(object, levelsDeep)).append("\n");
		description.append(getFieldsDescription(object, levelsDeep)).append("\n");
		
		return description.toString();
	}

	/**
	 * 
	 * @param object
	 * @param levelsDeep
	 * @return
	 * @throws IllegalAccessException
	 */
	private String getFieldsDescription(Object object, int levelsDeep)
			throws IllegalAccessException{
		
		StringBuilder fieldsDescription = new StringBuilder();
		Field[] fields = object.getClass().getFields();
		fieldsDescription.append(getTabs(levelsDeep));
		fieldsDescription.append("Fields:\n");
		if(object instanceof String){
			fieldsDescription.append(getTabs(levelsDeep + 1));
			fieldsDescription.append("value: ");
			fieldsDescription.append((String)object);
		}
		else if(object.getClass().isArray()){
			Object[] arrayObject = (Object[])object;
			for(Object obj : arrayObject){
				fieldsDescription.append(getTabs(levelsDeep + 1));
				fieldsDescription.append("[\n");
				fieldsDescription.append(inspectObject(obj, levelsDeep + 2));
				fieldsDescription.append(getTabs(levelsDeep + 1));
				fieldsDescription.append("]");
			}
		}
		else {
			for(int i = 0; i < fields.length; i++){
				Field field = fields[i];
				fieldsDescription.append(getTabs(levelsDeep + 1));
				fieldsDescription.append(field.getName());
				fieldsDescription.append(": ");
				if(field.get(object) != null){
					if(field.getClass().isPrimitive()){
						fieldsDescription.append(field.get(object).toString());
					}
					else{
						fieldsDescription.append("\n");
						fieldsDescription.append(inspectObject(field.get(object),
								levelsDeep + 2));
					}
				}
				else{
					String nullFieldValue =
							(i < fields.length - 1) ? "null\n" : "null";
					fieldsDescription.append(nullFieldValue);
				}
			}
		}
		return fieldsDescription.toString();
	}

	/**
	 * 
	 * @param object
	 * @param levelsDeep
	 * @return
	 */
	private String getClassDescription(Object object, int levelsDeep) {
		StringBuilder classDescription = new StringBuilder();
		classDescription.append("Class: ");
		classDescription.append(object.getClass().getSimpleName());
		return classDescription.toString();
	}
	
	/**
	 * 
	 * @param level
	 * @return
	 */
	private String getTabs(int level){
		StringBuilder tabs = new StringBuilder();
		tabs.append("");
		for(int i = 0; i < level; i++){
			tabs.append("\u0020\u0020");
		}
		return tabs.toString();
	}
}
