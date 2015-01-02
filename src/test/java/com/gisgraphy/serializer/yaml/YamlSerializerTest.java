/*******************************************************************************
 * Gisgraphy Project 
 *  
 *    This library is free software; you can redistribute it and/or
 *    modify it under the terms of the GNU Lesser General Public
 *    License as published by the Free Software Foundation; either
 *    version 2.1 of the License, or (at your option) any later version.
 *  
 *    This library is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 *    Lesser General Public License for more details.
 *  
 *    You should have received a copy of the GNU Lesser General Public
 *    License along with this library; if not, write to the Free Software
 *    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307, USA
 *  
 *   Copyright 2008  Gisgraphy project 
 * 
 *   David Masclet <davidmasclet@gisgraphy.com>
 ******************************************************************************/
package com.gisgraphy.serializer.yaml;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.serializer.UniversalSerializer;
import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.SerializerException;
import com.gisgraphy.serializer.testdata.BeanForTest;
import com.gisgraphy.serializer.testdata.BeanForTestWoSetter;
import com.gisgraphy.serializer.testdata.TestUtils;

public class YamlSerializerTest {

	@Test
	public void read() {
		YamlSerializer parser = new YamlSerializer();
		BeanForTest bean = TestUtils.createBeanForTest();
		byte[] bytes = parser.writeToString(bean).getBytes();
		System.out.println(new String(bytes));
		InputStream inputStream = new ByteArrayInputStream(bytes);
		BeanForTest result = parser.read(inputStream, BeanForTest.class);
		assertEquals(bean, result);
	}

	@Test
	public void readDTO() {
		YamlSerializer parser = new YamlSerializer();
		BeanForTestWoSetter bean = TestUtils.createBeanForTestWoSetters();
		byte[] bytes = parser.writeToString(bean).getBytes();
		System.out.println(new String(bytes));
		InputStream inputStream = new ByteArrayInputStream(bytes);
		BeanForTestWoSetter result = parser.read(inputStream, BeanForTestWoSetter.class);
		assertEquals(bean, result);
	}

	@Test
	public void writeOutputStreamObjectBooleanObjectArray() {
		// setup
		YamlSerializer serializer = new YamlSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = TestUtils.createBeanForTest();
		// exercice
		serializer.write(outputStream, bean, false, null);
		String result = new String(outputStream.toByteArray());
		// verify
		System.out.println(result);
	}

	@Test
	public void CouldWriteNullValue() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			UniversalSerializer.getInstance().write(byteArrayOutputStream, null, OutputFormat.YAML);
			Assert.fail("serialize a null object in Yaml should throw an exception");
		} catch (SerializerException e) {
		}
		try {
			 UniversalSerializer.getInstance().writeToString(null, OutputFormat.YAML);
			 Assert.fail("serialize a null object in Yaml should throw an exception");
		} catch (SerializerException e) {
		}
	}

}
