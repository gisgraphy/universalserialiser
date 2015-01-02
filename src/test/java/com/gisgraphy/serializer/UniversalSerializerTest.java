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
package com.gisgraphy.serializer;

import java.io.ByteArrayInputStream;

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.SerializerException;
import com.gisgraphy.serializer.exception.UnsupportedFormatException;
import com.gisgraphy.serializer.testdata.BeanForTest;
import com.gisgraphy.serializer.testdata.TestUtils;

public class UniversalSerializerTest {

    @Test
    public void getInstance() {
	Assert.assertNotNull(UniversalSerializer.getInstance());
    }
    
    @Test
    public void serializeInANullOutputStreamThrowsAnException() {
	try {
		UniversalSerializer.getInstance().write(null, TestUtils.createBeanForTest(),OutputFormat.getDefault() );
		Assert.fail("serialize in a null outputstream should throws");
	} catch (SerializerException e) {
		//ignore
	}
    }
    
    @Test
    public void serializeWithIndentationInANullOutputStreamThrowsAnException() {
	try {
		UniversalSerializer.getInstance().write(null, TestUtils.createBeanForTest(),true,OutputFormat.getDefault() );
		Assert.fail("serialize in a null outputstream should throws");
	} catch (SerializerException e) {
		//ignore
	}
    }
    
    @Test
    public void serializeWithIndentationAndParamsInANullOutputStreamThrowsAnException() {
	try {
		UniversalSerializer.getInstance().write(null, TestUtils.createBeanForTest(), true, null,OutputFormat.getDefault());
		Assert.fail("serialize in a null outputstream should throws");
	} catch (SerializerException e) {
		//ignore
	}
    }
    
    @Test
    public void readWithUnsuportedFormatThrowsAnException() {
	try {
		UniversalSerializer.getInstance().read(new ByteArrayInputStream("".getBytes()), BeanForTest.class, OutputFormat.UNSUPPORTED);
		Assert.fail("read with unsupported format should throws");
	} catch (UnsupportedFormatException e) {
		//ignore
	}
    }
    
    
    
   


}
