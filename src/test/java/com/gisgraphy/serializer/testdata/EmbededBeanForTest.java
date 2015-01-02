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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.junit.Ignore;

@XmlRootElement(name = EmbededBeanForTest.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Ignore
public class EmbededBeanForTest {

	public EmbededBeanForTest() {
	}

	/**
	 * @param sampleString
	 */
	public EmbededBeanForTest(String sampleString) {
		super();
		this.sampleString = sampleString;
	}

	public final static String ROOT_ELEMENT_NAME = "embededBeanForTest";

	String sampleString;

	public String getSampleString() {
		return sampleString;
	}

	public void setSampleString(String sampleString) {
		this.sampleString = sampleString;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sampleString == null) ? 0 : sampleString.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmbededBeanForTest other = (EmbededBeanForTest) obj;
		if (sampleString == null) {
			if (other.sampleString != null)
				return false;
		} else if (!sampleString.equals(other.sampleString))
			return false;
		return true;
	}

}
