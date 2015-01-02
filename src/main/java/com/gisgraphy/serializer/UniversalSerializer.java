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
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.gisgraphy.serializer.common.AbstractSerializer;
import com.gisgraphy.serializer.common.OutputFormat;
import com.gisgraphy.serializer.common.SerializerException;
import com.gisgraphy.serializer.exception.UnsupportedFormatException;

public class UniversalSerializer {
	
	private static UniversalSerializer universalSerializer = new UniversalSerializer();
	
	private UniversalSerializer() {
	}
	
	public static UniversalSerializer getInstance(){
		return universalSerializer;
	}

	UniversalSerializerStrategy strategy = new UniversalSerializerStrategy();

	public void write(OutputStream outputStream, Object object, OutputFormat format) throws SerializerException {
		if (outputStream == null){
			throw new SerializerException("Can not serialize in a null outputstream");
		}
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		serializer.write(outputStream, object, serializer.isDefault_indentation(), null);
	}

	public void write(OutputStream outputStream, Object object, boolean indent, OutputFormat format) throws SerializerException {
		if (outputStream == null){
			throw new SerializerException("Can not serialize in a null outputstream");
		}
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		serializer.write(outputStream, object, indent, null);
	}

	public String writeToString(Object object, OutputFormat format) throws SerializerException {
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		serializer.write(outputStream, object);
		return new String(outputStream.toByteArray());
	}

	public String writeToString(Object object, boolean indent, OutputFormat format) throws SerializerException {
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		serializer.write(outputStream, object, indent, null);
		return new String(outputStream.toByteArray());

	}

	public <T> T read(InputStream inputStream, Class<T> classToBeBound, OutputFormat format) throws SerializerException ,UnsupportedFormatException{
		if (!format.supportRead()){
			throw new UnsupportedFormatException(format+" does not support de serialisation");
		}
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		return serializer.read(inputStream, classToBeBound);
	}
	
	public <T> T read(String stream, Class<T> classToBeBound, OutputFormat format) throws SerializerException ,UnsupportedFormatException{
		if (!format.supportRead()){
			throw new UnsupportedFormatException(format+" does not support de serialisation");
		}
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		return serializer.read(new ByteArrayInputStream(stream.getBytes()), classToBeBound);
	}

	public void write(OutputStream outputStream, Object object, boolean indent, Map<String, Object> extraParameter, OutputFormat format) throws SerializerException {
		if (outputStream == null){
			throw new SerializerException("Can not serialize in a null outputstream");
		}
		AbstractSerializer serializer = strategy.getSerializerFromFormat(format);
		serializer.write(outputStream, object, indent,extraParameter);
	}

}
