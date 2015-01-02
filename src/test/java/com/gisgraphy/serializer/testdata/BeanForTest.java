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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.junit.Ignore;

@XmlRootElement(name = BeanForTest.ROOT_ELEMENT_NAME)
@XmlAccessorType(XmlAccessType.FIELD)
@Ignore
public class BeanForTest {
	public final static String ROOT_ELEMENT_NAME = "myBean";
	private Map<String, ArrayList<String>> samplemap;
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

	public void setSampleString(String sampleString) {
		this.sampleString = sampleString;
	}

	public Long getSampleLong() {
		return sampleLong;
	}

	public void setSampleLong(Long sampleLong) {
		this.sampleLong = sampleLong;
	}

	public Integer getSampleInteger() {
		return sampleInteger;
	}

	public void setSampleInteger(Integer sampleInteger) {
		this.sampleInteger = sampleInteger;
	}

	public String getTranscient() {
		return transcient;
	}

	public void setTranscient(String transcient) {
		this.transcient = transcient;
	}

	public List<EmbededBeanForTest> getListElements() {
		return listElements;
	}

	public void setListElements(List<EmbededBeanForTest> listElements) {
		this.listElements = listElements;
	}

	public enumForTest getEnumeration() {
		return enumeration;
	}

	public void setEnumeration(enumForTest enumeration) {
		this.enumeration = enumeration;
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
		BeanForTest other = (BeanForTest) obj;
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

	public void setNullField(String nullField) {
		this.nullField = nullField;
	}

	public Float getaFloatNumber() {
		return aFloatNumber;
	}

	public void setaFloatNumber(Float aFloatNumber) {
		this.aFloatNumber = aFloatNumber;
	}

	public Map<String, ArrayList<String>> getSamplemap() {
	    return samplemap;
	}

	public void setSamplemap(Map<String, ArrayList<String>> samplemap) {
	    this.samplemap = samplemap;
	}

}
