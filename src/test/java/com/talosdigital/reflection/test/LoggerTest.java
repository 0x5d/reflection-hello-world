package com.talosdigital.reflection.test;

import org.junit.Assert;
import org.junit.Test;
import com.talosdigital.reflection.Logger;

public class LoggerTest {

	@Test
	public void testPerson() throws IllegalAccessException {
		Person p = new Person();
		p.name = "John";
		p.lastName = "Doe";
		Person so = new Person();
		so.name = "Jane";
		so.lastName = "Doe";
		p.significantOther = so;
		Person child = new Person();
		child.name = "Johnny";
		child.lastName = "Doe";
		p.children = new Person[]{child};
		Logger log = new Logger();
		
		String expected = 
				"Class: Person\n"
				+ "Fields:\n"
				+ "  name:\n"
				+ "    Class: String\n"
				+ "    Fields:\n"
				+ "      value: John\n"
				+ "  lastName:\n"
				+ "    Class: String\n"
				+ "    Fields:\n"
				+ "      value: Doe\n"
				+ "  significantOther"
				+ "    Class: Person\n"
				+ "    Fields:"
				+ "      name:\n"
				+ "        Class: String\n"
				+ "        Fields:\n"
				+ "          value: Jane\n"
				+ "      lastName:\n"
				+ "        Class: String\n"
				+ "        Fields:\n"
				+ "          value: Doe\n"
				+ "      significantOther: null\n"
				+ "      children: null\n\n"
				+ "  children:\n"
				+ "    Class: Person[]\n"
				+ "    Fields:\n"
				+ "      [\n"
				+ "        Class: Person\n"
				+ "        Fields:\n"
				+ "          name:\n"
				+ "            Class: String"
				+ "            Fields:"
				+ "              value: Johnny"
				+ "          lastName:\n"
				+ "            Class: String\n"
				+ "            Fields:\n"
				+ "              value: Doe"
				+ "          significantOther: null\n"
				+ "          children: null\n\n"
				+ "      ]\n\n";
		
		String desc = log.inspectObject(p);
		System.out.println(desc);
		Assert.assertEquals(expected, desc);
	}

}
