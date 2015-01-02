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
package com.gisgraphy.serializer.ruby;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerator.Feature;
import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import com.gisgraphy.serializer.common.AbstractSerializer;
import com.gisgraphy.serializer.common.SerializerException;
import com.gisgraphy.serializer.common.UniversalSerializerConstant;

/**
 * 
 * @author <a href="mailto:david.masclet@gisgraphy.com">David Masclet</a>
 */
public class RubySerializer extends AbstractSerializer {

	private static Pattern p = Pattern.compile("\\w+");

	private static final Logger logger = Logger.getLogger(RubySerializer.class);
	private ObjectMapper mapper;

	/**
	 * 
	 */
	public RubySerializer() {
		mapper = new ObjectMapper(new RubyFactory());
		AnnotationIntrospector introspector = new JaxbAnnotationIntrospector();
		mapper.getDeserializationConfig().setAnnotationIntrospector(introspector);
		mapper.getSerializationConfig().setAnnotationIntrospector(introspector);
		mapper.getSerializationConfig().set(SerializationConfig.Feature.INDENT_OUTPUT, false);
		mapper.getJsonFactory().disable(Feature.AUTO_CLOSE_TARGET);
		// mapper.getSerializationConfig().setSerializationInclusion(Inclusion.NON_NULL);

	}

	public <T> T read(InputStream inputStream, Class<T> classToBeBound) throws SerializerException {
		throw new RuntimeException("read is not implemented for Ruby");
	}

	public void write(OutputStream outputStream, Object object, boolean indent, Map<String, Object> extraParams) throws SerializerException {
		Object callbackMethodName = null;
		if (extraParams != null) {
			callbackMethodName = extraParams.get(UniversalSerializerConstant.CALLBACK_METHOD_NAME);
		}

		try {
			boolean isValidMethodName = callbackMethodName != null && callbackMethodName instanceof String && p.matcher((String) callbackMethodName).matches();
			if (isValidMethodName) {
				outputStream.write(new String(callbackMethodName + "(").getBytes(CHARSET));
			}
			mapper.writeValue(outputStream, object);
			if (isValidMethodName) {
				outputStream.write(new String(");").getBytes(CHARSET));
			}
		} catch (Exception e) {
			throw new SerializerException(e);
		}

	}

}
