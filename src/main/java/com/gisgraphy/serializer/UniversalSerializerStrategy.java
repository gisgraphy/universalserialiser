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

import com.gisgraphy.serializer.common.AbstractSerializer
;
import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.SerializerException;
import com.gisgraphy.serializer.json.JsonSerializer;
import com.gisgraphy.serializer.php.PhpSerializer;
import com.gisgraphy.serializer.python.PythonSerializer;
import com.gisgraphy.serializer.ruby.RubySerializer;
import com.gisgraphy.serializer.xml.JaxbXmlSerializer;
import com.gisgraphy.serializer.yaml.YamlSerializer;

public class UniversalSerializerStrategy {

	private JaxbXmlSerializer xmlSerializer = new JaxbXmlSerializer();
	private JsonSerializer jsonSerializer = new JsonSerializer();
	private PhpSerializer phpSerializer = new PhpSerializer();
	private PythonSerializer pythonSerializer = new PythonSerializer();
	private RubySerializer rubySerializer = new RubySerializer();
	private YamlSerializer yamlSerializer = new YamlSerializer();

	public AbstractSerializer getSerializerFromFormat(OutputFormat format) {
		if (format == OutputFormat.XML) {
			return xmlSerializer;
		} else if (format == OutputFormat.JSON) {
			return jsonSerializer;
		} else if (format == OutputFormat.PHP) {
			return phpSerializer;
		} else if (format == OutputFormat.PYTHON) {
			return pythonSerializer;
		} else if (format == OutputFormat.RUBY) {
			return rubySerializer;
		} else if (format == OutputFormat.YAML) {
			return yamlSerializer;
		} else {
			throw new SerializerException(format + " is not supported");
		}

	}
}
