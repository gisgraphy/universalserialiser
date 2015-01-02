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

import org.junit.Assert;
import org.junit.Test;

import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.json.JsonSerializer;
import com.gisgraphy.serializer.php.PhpSerializer;
import com.gisgraphy.serializer.python.PythonSerializer;
import com.gisgraphy.serializer.ruby.RubySerializer;
import com.gisgraphy.serializer.xml.JaxbXmlSerializer;
import com.gisgraphy.serializer.yaml.YamlSerializer;

public class UniversalSerializerStrategyTest {

	UniversalSerializerStrategy strategy = new UniversalSerializerStrategy();

	@Test
	public void getSerializerFromFormat() {
		Assert.assertTrue(strategy.getSerializerFromFormat(OutputFormat.XML) instanceof JaxbXmlSerializer);
		Assert.assertTrue(strategy.getSerializerFromFormat(OutputFormat.JSON) instanceof JsonSerializer);
		Assert.assertTrue(strategy.getSerializerFromFormat(OutputFormat.PHP) instanceof PhpSerializer);
		Assert.assertTrue(strategy.getSerializerFromFormat(OutputFormat.PYTHON) instanceof PythonSerializer);
		Assert.assertTrue(strategy.getSerializerFromFormat(OutputFormat.RUBY) instanceof RubySerializer);
		Assert.assertTrue(strategy.getSerializerFromFormat(OutputFormat.YAML) instanceof YamlSerializer);
	}
}
