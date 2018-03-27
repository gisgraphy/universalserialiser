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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.apache.log4j.Logger;

import com.gisgraphy.serializer.common.AbstractSerializer;
import com.gisgraphy.serializer.common.Helper;
import com.gisgraphy.serializer.common.SerializerException;

public class JaxbXmlSerializer extends AbstractSerializer {
    
    JAXBContextFactory factory = JAXBContextFactory.getInstance();

	private static final Logger logger = Logger.getLogger(JaxbXmlSerializer.class);

	public <T> T read(InputStream inputStream, Class<T> classToBeBound) throws SerializerException {
		JAXBContext context = null;
		Unmarshaller unMarshaller = null;
		try {
		    	if (classToBeBound == String.class){
		    	    return (T) Helper.inputStreamToString(inputStream);
		    	}
			//context = JAXBContext.newInstance(classToBeBound);
		    	context = factory.getJaxBContext(classToBeBound);
			unMarshaller = context.createUnmarshaller();
			JAXBElement<T> objectUnmarshal = unMarshaller.unmarshal(new StreamSource(inputStream), classToBeBound);
			return objectUnmarshal != null ? objectUnmarshal.getValue() : null;
		} catch (JAXBException e) {
			throw new SerializerException(e);
		} catch (IOException e) {
			throw new SerializerException(e);
		} catch (NullPointerException e) {
			logger.error("Invalid XML document", e);
			return null;
		}
	}

	public void write(OutputStream outputStream, Object object, boolean indent, Map<String, Object> extraParams) throws SerializerException {
		if (object == null){
			throw new SerializerException("can not serialize null object in XML");
		}
		JAXBContext context = null;
		Marshaller marshaller = null;
		try {
			//context = JAXBContext.newInstance(object.getClass());
		    context = factory.getJaxBContext(object.getClass());
			marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, indent);
			marshaller.marshal(object, outputStream);
		} catch (JAXBException e) {
			throw new SerializerException(e);
		}

	}

}
