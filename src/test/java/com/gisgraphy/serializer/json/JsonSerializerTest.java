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
package com.gisgraphy.serializer.json;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import net.sf.jstester.JsTester;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.serializer.UniversalSerializer;
import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.UniversalSerializerConstant;
import com.gisgraphy.serializer.testdata.BeanForTest;
import com.gisgraphy.serializer.testdata.TestUtils;

public class JsonSerializerTest {

	@Test
	public void read() {
		JsonSerializer parser = new JsonSerializer();
		BeanForTest bean = TestUtils.createBeanForTest();
		InputStream inputStream = new ByteArrayInputStream(parser.writeToString(bean).getBytes());
		BeanForTest result = parser.read(inputStream, BeanForTest.class);
		assertEquals(bean, result);
	}
	
	@Test
	public void readInString() {
		JsonSerializer parser = new JsonSerializer();
		BeanForTest bean = TestUtils.createBeanForTest();
		InputStream inputStream = new ByteArrayInputStream(parser.writeToString(bean).getBytes());
		String result = parser.read(inputStream, String.class);
		System.out.println(result);
		Assert.assertNotNull(result);
	}
	
	@SuppressWarnings("rawtypes")
	@Test
	public void readInMap() {
		JsonSerializer parser = new JsonSerializer();
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("key","value");
		String mapAsString = parser.writeToString(map);
		InputStream inputStream = new ByteArrayInputStream(parser.writeToString(mapAsString).getBytes());
		Object result = parser.read(inputStream, new HashMap<String,String>().getClass());
		System.out.println(result);
		Assert.assertNotNull(result);
	}

	@Test
	public void writeOutputStreamObjectBooleanObjectArray() {
		// setup
		JsonSerializer serializer = new JsonSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = TestUtils.createBeanForTest();
		// exercice
		serializer.write(outputStream, bean, false, null);
		String result = new String(outputStream.toByteArray());
		// verify
		JsTester jsTester = new JsTester();
		jsTester.onSetUp();
		jsTester.eval("evalresult= eval(" + result + ");");
		Assert.assertNotNull(jsTester.eval("evalresult"));
		assertEquals(bean.getSampleString(), jsTester.eval("evalresult.sampleString"));
		assertEquals(bean.getSampleLong().longValue(), ((Double) jsTester.eval("evalresult.sampleLong")).longValue());
		assertEquals(bean.getSampleInteger().longValue(), ((Double) jsTester.eval("evalresult.sampleInteger")).intValue());
		for (int i = 0; i < bean.getListElements().size(); i++) {
			assertEquals(bean.getListElements().get(i).getSampleString(), jsTester.eval("evalresult.element[" + i + "]['sampleString']"));
		}
		assertEquals(bean.getEnumeration().toString(), jsTester.eval("evalresult.enumeration"));
	}
	
	@Test
	public void ReadWithUnknownFields(){
	String feed = "{\"sampleString\":\"testString\",\"unknow filed\":1}";
	InputStream inputStream = new ByteArrayInputStream(feed.getBytes());
	JsonSerializer serializer = new JsonSerializer();
	serializer.read(inputStream, BeanForTest.class);
	
	}
	@Test
	public void writeWithMethodCallback() {

		// setup
		JsonSerializer parser = new JsonSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = new BeanForTest();
		bean.setSampleInteger(1);
		// exercice
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(UniversalSerializerConstant.CALLBACK_METHOD_NAME, "do");

		parser.write(outputStream, bean, false, parameters);
		String result = new String(outputStream.toByteArray());
		// verify

		assertEquals("do({\"sampleInteger\":1});", result);
	}

	@Test
	public void writeWithMethodCallbackNonAlphaNum() {
		// setup
		JsonSerializer parser = new JsonSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = new BeanForTest();
		bean.setSampleInteger(1);
		// exercice
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(UniversalSerializerConstant.CALLBACK_METHOD_NAME, "d'o");

		parser.write(outputStream, bean, false, parameters);
		String result = new String(outputStream.toByteArray());
		// verify

		assertEquals("{\"sampleInteger\":1}", result);
	}
	
	 @Test
	    public void CouldWriteNullValue(){
	    		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				UniversalSerializer.getInstance().write(byteArrayOutputStream,null, OutputFormat.JSON);
				Assert.assertEquals("null",byteArrayOutputStream.toString());
	    		String actual = UniversalSerializer.getInstance().writeToString(null, OutputFormat.JSON);
	    		Assert.assertEquals("null",actual);
	    }

}
