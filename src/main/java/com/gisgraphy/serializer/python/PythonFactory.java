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
package com.gisgraphy.serializer.python;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.io.IOContext;
import org.codehaus.jackson.map.MappingJsonFactory;

public class PythonFactory extends MappingJsonFactory {

	@Override
	protected JsonGenerator _createJsonGenerator(Writer out, IOContext ctxt) throws IOException {
		return super._createJsonGenerator(out, ctxt);
	}

	@Override
	protected JsonGenerator _createUTF8JsonGenerator(OutputStream out, IOContext ctxt) throws IOException {
		return new PythonGenerator(ctxt, _generatorFeatures, _objectCodec, out);
	}

}
