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
package com.gisgraphy.serializer.xml;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import com.gisgraphy.serializer.UniversalSerializer;
import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.SerializerException;
import com.gisgraphy.serializer.testdata.BeanForTest;
import com.gisgraphy.serializer.testdata.TestUtils;

//@Ignore
public class JaxbXmlSerializerTest {

	@Test
	public void read() {
		String string = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><myBean><sampleString>testString</sampleString><sampleLong>1</sampleLong><sampleInteger>0</sampleInteger><element><sampleString>samplelistvalue1</sampleString></element><element><sampleString>samplelistvalue2</sampleString></element></myBean>";
		InputStream inputStream = new ByteArrayInputStream(string.getBytes());
		JaxbXmlSerializer parser = new JaxbXmlSerializer();
		BeanForTest beanForTest = parser.read(inputStream, BeanForTest.class);
		Assert.assertEquals(TestUtils.createBeanForTest(), beanForTest);
	}
	
	@Test
	public void readInString() {
		String string = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><myBean><sampleString>testString</sampleString><sampleLong>1</sampleLong><sampleInteger>0</sampleInteger><element><sampleString>samplelistvalue1</sampleString></element><element><sampleString>samplelistvalue2</sampleString></element></myBean>";
		InputStream inputStream = new ByteArrayInputStream(string.getBytes());
		JaxbXmlSerializer parser = new JaxbXmlSerializer();
		String actual = parser.read(inputStream, String.class);
		Assert.assertNotNull(actual);
		System.out.println(actual);

	}


	@Test
	public void write() {
		JaxbXmlSerializer parser = new JaxbXmlSerializer();
		parser.setDefault_indentation(false);
		BeanForTest myBean = TestUtils.createBeanForTest();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		parser.write(outputStream, myBean);
		String result = new String(outputStream.toByteArray());
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><myBean><samplemap><entry><key>bb</key><value/></entry><entry><key>aa</key><value/></entry></samplemap><sampleString>testString</sampleString><sampleLong>1</sampleLong><sampleInteger>0</sampleInteger><element><sampleString>samplelistvalue1</sampleString></element><element><sampleString>samplelistvalue2</sampleString></element><enumeration>value1</enumeration></myBean>", result);

	}

	@Test
	public void writeIndent() {
		JaxbXmlSerializer parser = new JaxbXmlSerializer();
		parser.setDefault_indentation(true);
		BeanForTest myBean = TestUtils.createBeanForTest();
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		parser.write(outputStream, myBean);
		String result = new String(outputStream.toByteArray());
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<myBean>\n    <samplemap>\n        <entry>\n            <key>bb</key>\n            <value/>\n        </entry>\n        <entry>\n            <key>aa</key>\n            <value/>\n        </entry>\n    </samplemap>\n    <sampleString>testString</sampleString>\n    <sampleLong>1</sampleLong>\n    <sampleInteger>0</sampleInteger>\n    <element>\n        <sampleString>samplelistvalue1</sampleString>\n    </element>\n    <element>\n        <sampleString>samplelistvalue2</sampleString>\n    </element>\n    <enumeration>value1</enumeration>\n</myBean>\n", result);

	}

	@Test
	public void writeToString() {
		JaxbXmlSerializer parser = new JaxbXmlSerializer();
		parser.setDefault_indentation(false);
		BeanForTest myBean = TestUtils.createBeanForTest();

		String result = parser.writeToString(myBean);
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><myBean><samplemap><entry><key>bb</key><value/></entry><entry><key>aa</key><value/></entry></samplemap><sampleString>testString</sampleString><sampleLong>1</sampleLong><sampleInteger>0</sampleInteger><element><sampleString>samplelistvalue1</sampleString></element><element><sampleString>samplelistvalue2</sampleString></element><enumeration>value1</enumeration></myBean>", result);

	}

	@Test
	public void writeToStringIndent() {
		JaxbXmlSerializer parser = new JaxbXmlSerializer();
		BeanForTest myBean = TestUtils.createBeanForTest();

		String result = parser.writeToString(myBean, true);
		Assert.assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n<myBean>\n    <samplemap>\n        <entry>\n            <key>bb</key>\n            <value/>\n        </entry>\n        <entry>\n            <key>aa</key>\n            <value/>\n        </entry>\n    </samplemap>\n    <sampleString>testString</sampleString>\n    <sampleLong>1</sampleLong>\n    <sampleInteger>0</sampleInteger>\n    <element>\n        <sampleString>samplelistvalue1</sampleString>\n    </element>\n    <element>\n        <sampleString>samplelistvalue2</sampleString>\n    </element>\n    <enumeration>value1</enumeration>\n</myBean>\n", result);

	}
	
	@Test
	public void CouldWriteNullValue() {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		try {
			UniversalSerializer.getInstance().write(byteArrayOutputStream, null, OutputFormat.XML);
			Assert.fail("serialize a null object in Yaml should throw an exception");
		} catch (SerializerException e) {
		}
		try {
			UniversalSerializer.getInstance().writeToString(null, OutputFormat.XML);
			Assert.fail("serialize a null object in Yaml should throw an exception");
		} catch (SerializerException e) {
		}
	}

}
