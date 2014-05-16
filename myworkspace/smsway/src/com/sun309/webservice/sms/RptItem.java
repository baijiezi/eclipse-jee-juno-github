package com.sun309.webservice.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for rptItem complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="rptItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="code" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="desc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mobile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="rptTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="smID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "rptItem", propOrder = { "code", "desc", "mobile", "rptTime",
		"smID" })
public class RptItem {

	protected int code;
	protected String desc;
	protected String mobile;
	protected String rptTime;
	protected long smID;

	/**
	 * Gets the value of the code property.
	 * 
	 */
	public int getCode() {
		return code;
	}

	/**
	 * Sets the value of the code property.
	 * 
	 */
	public void setCode(int value) {
		this.code = value;
	}

	/**
	 * Gets the value of the desc property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * Sets the value of the desc property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDesc(String value) {
		this.desc = value;
	}

	/**
	 * Gets the value of the mobile property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Sets the value of the mobile property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMobile(String value) {
		this.mobile = value;
	}

	/**
	 * Gets the value of the rptTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRptTime() {
		return rptTime;
	}

	/**
	 * Sets the value of the rptTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRptTime(String value) {
		this.rptTime = value;
	}

	/**
	 * Gets the value of the smID property.
	 * 
	 */
	public long getSmID() {
		return smID;
	}

	/**
	 * Sets the value of the smID property.
	 * 
	 */
	public void setSmID(long value) {
		this.smID = value;
	}

}
