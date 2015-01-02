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

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.junit.Ignore;

@XmlRootElement(name = BeanForTestWoSetter.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Ignore
public class BeanForTestWoSetter {
	/**
     * 
     */
    public BeanForTestWoSetter() {
	super();
	// TODO Auto-generated constructor stub
    }


	/**
     * @param sampleString
     * @param sampleInteger
     */
    public BeanForTestWoSetter(String sampleString, Integer sampleInteger) {
	super();
	this.sampleString = sampleString;
	this.sampleInteger = sampleInteger;
    }


	public final static String ROOT_ELEMENT_NAME = "myBean";

	private Float aFloatNumber;
	private String nullField;
	private String sampleString;
	private Long sampleLong;
	private Integer sampleInteger;
	@XmlElement(name = "element")
	private List<EmbededBeanForTest> listElements;
	enumForTest enumeration;
	@XmlTransient
	// @JsonIgnore
	private String transcient;

	public String getSampleString() {
		return sampleString;
	}


	public Long getSampleLong() {
		return sampleLong;
	}


	public Integer getSampleInteger() {
		return sampleInteger;
	}


	public String getTranscient() {
		return transcient;
	}


	public List<EmbededBeanForTest> getListElements() {
		return listElements;
	}


	public enumForTest getEnumeration() {
		return enumeration;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listElements == null) ? 0 : listElements.hashCode());
		result = prime * result + ((sampleInteger == null) ? 0 : sampleInteger.hashCode());
		result = prime * result + ((sampleLong == null) ? 0 : sampleLong.hashCode());
		result = prime * result + ((sampleString == null) ? 0 : sampleString.hashCode());
		result = prime * result + ((transcient == null) ? 0 : transcient.hashCode());
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
		BeanForTestWoSetter other = (BeanForTestWoSetter) obj;
		if (listElements == null) {
			if (other.listElements != null)
				return false;
		} else if (!listElements.equals(other.listElements))
			return false;
		if (sampleInteger == null) {
			if (other.sampleInteger != null)
				return false;
		} else if (!sampleInteger.equals(other.sampleInteger))
			return false;
		if (sampleLong == null) {
			if (other.sampleLong != null)
				return false;
		} else if (!sampleLong.equals(other.sampleLong))
			return false;
		if (sampleString == null) {
			if (other.sampleString != null)
				return false;
		} else if (!sampleString.equals(other.sampleString))
			return false;
		if (transcient == null) {
			if (other.transcient != null)
				return false;
		} else if (!transcient.equals(other.transcient))
			return false;
		return true;
	}

	public String getNullField() {
		return nullField;
	}


	public Float getaFloatNumber() {
		return aFloatNumber;
	}


}
