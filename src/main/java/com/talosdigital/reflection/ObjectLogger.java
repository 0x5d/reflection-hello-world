package com.talosdigital.reflection;

import java.lang.reflect.Field;

/**
 * 
 */

/**
 * @author castillobg
 *
 */
public class ObjectLogger {
	
	/**
	 * 
	 * @param object The object to inspect
	 * @return The object description string.
	 * @throws IllegalAccessException
	 */
	public static String inspectObject(Object object) throws IllegalAccessException{
		// Root object. Call overloaded method with hierarchy level 0.
		return inspectObject(object, 0);
	}
	
	/**
	 * 
	 * @param object The object to inspect
	 * @param hierarchyLevel The current object hierarchy level.
	 * @return The object description string.
	 * @throws IllegalAccessException
	 */
	private static String inspectObject(Object object, int hierarchyLevel) throws IllegalAccessException{
		// description will hold the object's description.
		StringBuilder description = new StringBuilder();
		// Format accordingly.
		description.append(getTabs(hierarchyLevel));
		// Get the object's class' description.
		description.append(getClassDescription(object, hierarchyLevel)).append("\n");
		// Get the object's fields' description and values.
		description.append(getFieldsDescription(object, hierarchyLevel)).append("\n");
		// Return the object's description.
		return description.toString();
	}

	/**
	 * 
	 * @param object The object whose fields are to be described.
	 * @param hierarchyLevel The current object hierarchy level.
	 * @return The object's fields' description.
	 * @throws IllegalAccessException
	 */
	private static String getFieldsDescription(Object object, int hierarchyLevel)
			throws IllegalAccessException{
		// fieldsDescription will hold the object's fields' description.
		StringBuilder fieldsDescription = new StringBuilder();
		// fields holds the object's public fields.
		Field[] fields = object.getClass().getFields();
		// format according to the hierarchy level.
		fieldsDescription.append(getTabs(hierarchyLevel));
		fieldsDescription.append("Fields:\n");
		// If an object is a String instance or a wrapped primitive,
		// just append its value.
		if(object instanceof String
				|| InspectionUtils.isWrapperType(object.getClass())){
			fieldsDescription.append(getTabs(hierarchyLevel + 1));
			fieldsDescription.append("value: ");
			fieldsDescription.append(object);
		}
		// If it's an array, run through it and call inspectObject recursively
		// for each object in it.
		else if(object.getClass().isArray()){
			Object[] arrayObject = (Object[])object;
			fieldsDescription.append(getTabs(hierarchyLevel + 1));
			// Array formatting.
			fieldsDescription.append("[\n");
			for(Object obj : arrayObject){
				fieldsDescription.append(inspectObject(obj, hierarchyLevel + 2));
			}
			fieldsDescription.append(getTabs(hierarchyLevel + 1));
			fieldsDescription.append("]");
		}
		// Else, it's a regular object.
		else {
			// Go through the fields and append each object's description by
			// calling inspectObject recursively.
			for(int i = 0; i < fields.length; i++){
				Field field = fields[i];
				fieldsDescription.append(getTabs(hierarchyLevel + 1));
				fieldsDescription.append(field.getName());
				fieldsDescription.append(": ");
				// If the object's been initialized with an instance:
				if(field.get(object) != null){
					fieldsDescription.append("\n");
					fieldsDescription.append(inspectObject(field.get(object),
							hierarchyLevel + 2));
				}
				//If it's null:
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
	 * @param object The object whose class will be described.
	 * @param hierarchyLevel The current object hierarchy level.
	 * @return The class' description.
	 */
	private static String getClassDescription(Object object, int hierarchyLevel) {
		StringBuilder classDescription = new StringBuilder();
		classDescription.append("Class: ");
		classDescription.append(object.getClass().getSimpleName());
		return classDescription.toString();
	}
	
	/**
	 * 
	 * @param level The hierarchy level to append tabs to.
	 * @return
	 */
	private static String getTabs(int level){
		StringBuilder tabs = new StringBuilder();
		tabs.append("");
		for(int i = 0; i < level; i++){
			tabs.append("\u0020\u0020");
		}
		return tabs.toString();
	}
}
