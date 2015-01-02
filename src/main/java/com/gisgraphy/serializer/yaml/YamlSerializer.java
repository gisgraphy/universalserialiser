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

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Map;

import org.apache.log4j.Logger;

import com.esotericsoftware.yamlbeans.YamlConfig;
import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.esotericsoftware.yamlbeans.YamlWriter;
import com.gisgraphy.serializer.common.AbstractSerializer;
import com.gisgraphy.serializer.common.SerializerException;

public class YamlSerializer extends AbstractSerializer {

	private static final Logger logger = Logger.getLogger(YamlSerializer.class);

	public <T> T read(InputStream inputStream, Class<T> classToBeBound) throws SerializerException {
	    YamlConfig config = new YamlConfig();
	    config.setPrivateFields(true);
		YamlReader yamlReader = new YamlReader(new InputStreamReader(inputStream),config);
		try {
			return yamlReader.read(classToBeBound);
		} catch (Exception e) {
			throw new SerializerException(e);
		} 
	}

	public void write(OutputStream outputStream, Object object, boolean indent, Map<String, Object> extraParams) throws SerializerException {
		if (object == null){
			throw new SerializerException("can not serialize null object in YAML");
		}
	    YamlConfig config = new YamlConfig();
	    config.setPrivateFields(true);
		YamlWriter writer = new YamlWriter(new OutputStreamWriter(outputStream),config);
		try {
			writer.write(object);
		} catch (Exception e) {
			throw new SerializerException(e);
		} finally{
			if (writer != null){
				try {
					writer.close();
				} catch (YamlException e) {
				}
			}
		}

	}

}
