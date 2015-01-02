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
package com.gisgraphy.serializer.php;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.serializer.UniversalSerializer;
import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.UniversalSerializerConstant;
import com.gisgraphy.serializer.testdata.BeanForTest;
import com.gisgraphy.serializer.testdata.TestUtils;

public class PhpSerializerTest {

	@Test
	public void read() {
		// not implemented
	}

	@Test
	public void writeOutputStreamObjectBooleanObjectArray() {
		// setup
		PhpSerializer serializer = new PhpSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = TestUtils.createBeanForTest();
		bean.setSampleString("s é ample\\");
		// exercice
		serializer.write(outputStream, bean, false, null);
		String result = new String(outputStream.toByteArray());
		System.out.println(result);
	}
	
	@Test
	public void writeWithMethodCallback() {

		// setup
		PhpSerializer parser = new PhpSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = new BeanForTest();
		bean.setSampleInteger(1);
		// exercice
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(UniversalSerializerConstant.CALLBACK_METHOD_NAME, "do");

		parser.write(outputStream, bean, false, parameters);
		String result = new String(outputStream.toByteArray());
		// verify

		Assert.assertTrue(result.startsWith("do("));
		Assert.assertTrue(result.endsWith(");"));
	}

	@Test
	public void writeWithMethodCallbackNonAlphaNum() {
		// setup
		PhpSerializer parser = new PhpSerializer();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		BeanForTest bean = new BeanForTest();
		bean.setSampleInteger(1);
		// exercice
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put(UniversalSerializerConstant.CALLBACK_METHOD_NAME, "d'o");

		parser.write(outputStream, bean, false, parameters);
		String result = new String(outputStream.toByteArray());
		// verify

		Assert.assertFalse(result.startsWith("do("));
		Assert.assertFalse(result.endsWith(");"));
	}
	
	 @Test
	    public void CouldWriteNullValue(){
	    		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
				UniversalSerializer.getInstance().write(byteArrayOutputStream,null, OutputFormat.PHP);
				Assert.assertEquals("null",byteArrayOutputStream.toString());
	    		String actual = UniversalSerializer.getInstance().writeToString(null, OutputFormat.PHP);
	    		Assert.assertEquals("null",actual);
	    }

}
