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
		p.age = 34;
		Person so = new Person();
		so.name = "Jane";
		so.lastName = "Doe";
		so.age = 33;
		p.significantOther = so;
		Person child = new Person();
		child.name = "Johnny";
		child.lastName = "Doe";
		child.age = 6;
		p.children = new Person[]{child};
		Logger log = new Logger();
		
		String expected =
				"Class: Person\n"
				+ "Fields:\n"
				+ "  name: \n"
				+ "    Class: String\n"
				+ "    Fields:\n"
				+ "      value: John\n"
				+ "  lastName: \n"
				+ "    Class: String\n"
				+ "    Fields:\n"
				+ "      value: Doe\n"
				+ "  age: \n"
				+ "    Class: Integer\n"
				+ "    Fields:\n"
				+ "      value: 34\n"
				+ "  significantOther: \n"
				+ "    Class: Person\n"
				+ "    Fields:\n"
				+ "      name: \n"
				+ "        Class: String\n"
				+ "        Fields:\n"
				+ "          value: Jane\n"
				+ "      lastName: \n"
				+ "        Class: String\n"
				+ "        Fields:\n"
				+ "          value: Doe\n"
				+ "      age: \n"
				+ "        Class: Integer\n"
				+ "        Fields:\n"
				+ "          value: 33\n"
				+ "      significantOther: null\n"
				+ "      children: null\n"
				+ "  children: \n"
				+ "    Class: Person[]\n"
				+ "    Fields:\n"
				+ "      [\n"
				+ "        Class: Person\n"
				+ "        Fields:\n"
				+ "          name: \n"
				+ "            Class: String\n"
				+ "            Fields:\n"
				+ "              value: Johnny\n"
				+ "          lastName: \n"
				+ "            Class: String\n"
				+ "            Fields:\n"
				+ "              value: Doe\n"
				+ "          age: \n"
				+ "            Class: Integer\n"
				+ "            Fields:\n"
				+ "              value: 6\n"
				+ "          significantOther: null\n"
				+ "          children: null\n"
				+ "      ]\n\n";
		
		String desc = log.inspectObject(p);
		System.out.println(desc);
		Assert.assertEquals(expected, desc);
	}

}
