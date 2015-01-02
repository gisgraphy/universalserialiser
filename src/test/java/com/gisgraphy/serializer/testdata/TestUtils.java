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
package com.gisgraphy.serializer.testdata;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Ignore;

@Ignore
public class TestUtils {

	@SuppressWarnings("serial")
	public static BeanForTest createBeanForTest() {
		BeanForTest myBean = new BeanForTest();
		myBean.setSampleInteger(0);
		myBean.setSampleLong(1L);
		myBean.setSampleString("testString");
		myBean.setListElements(new ArrayList<EmbededBeanForTest>() {
			{
				add(new EmbededBeanForTest("samplelistvalue1"));
				add(new EmbededBeanForTest("samplelistvalue2"));
			}
		});
		
		myBean.setEnumeration(enumForTest.value1);
		final ArrayList<String> aList = new ArrayList<String>(){
		    {add("foo");
		    add("bar");
		    }
		};
		final ArrayList<String> otherList = new ArrayList<String>(){
		    {add("foo");
		    add("bar");
		    }
		};
		
		Map<String, ArrayList<String>> samplemap = new HashMap<String, ArrayList<String>>();
			samplemap.put("aa", aList);
			samplemap.put("bb", otherList);
		myBean.setSamplemap(samplemap);
		return myBean;
	}
	
	public static BeanForTestWoSetter createBeanForTestWoSetters() {
	    BeanForTestWoSetter myBean = new BeanForTestWoSetter("testString",1);
		return myBean;
	}


}
