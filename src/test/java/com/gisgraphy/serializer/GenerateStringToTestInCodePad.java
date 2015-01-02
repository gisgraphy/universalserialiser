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

import java.io.ByteArrayOutputStream;

import org.junit.Test;

import com.gisgraphy.serializer.php.PhpSerializer;
import com.gisgraphy.serializer.python.PythonSerializer;
import com.gisgraphy.serializer.ruby.RubySerializer;
import com.gisgraphy.serializer.testdata.BeanForTest;
import com.gisgraphy.serializer.testdata.TestUtils;

/**
 * copy paste the strings output to http://codepad.org and choose the right language
 * it allow to test generated code
 * @author dmasclet
 *
 */
public class GenerateStringToTestInCodePad {

    @Test
    public void forPhP() {
	// setup
	PhpSerializer serializer = new PhpSerializer();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	BeanForTest bean = TestUtils.createBeanForTest();
	bean.setSampleString("s é ample\\");
	// exercice
	serializer.write(outputStream, bean, false, null);
	String result = new String(outputStream.toByteArray());
	System.out.println("-- php --");
	System.out.println("<?php eval(\"\\$str ="+result+";\");echo (print_r($str)); ?>");
    }

    @Test
    public void forPython() {
	// setup
	PythonSerializer serializer = new PythonSerializer();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	BeanForTest bean = TestUtils.createBeanForTest();
	bean.setSampleString("sam é ple\\");
	bean.setaFloatNumber(3.5F);
	// exercice
	serializer.write(outputStream, bean, false, null);
	String result = new String(outputStream.toByteArray());
	System.out.println("-- python --");
	System.out.println("evaluated="+result+";print str(evaluated['aFloatNumber'])");
    }

    @Test
    public void forRuby() {
	// setup
	RubySerializer serializer = new RubySerializer();
	ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
	BeanForTest bean = TestUtils.createBeanForTest();
	bean.setSampleString("sample\\");
	bean.setaFloatNumber(3.5F);
	// exercice
	serializer.write(outputStream, bean, false, null);
	String result = new String(outputStream.toByteArray());
	System.out.println("-- ruby --");
	System.out.println("evaluated ="+result+";puts  evaluated.inspect");
    }

}
