package com.sun309.webservice.sms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for receiveRPT complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="receiveRPT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="smID" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="amount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "receiveRPT", propOrder = { "smID", "amount" })
public class ReceiveRPT {

	protected long smID;
	protected int amount;

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

	/**
	 * Gets the value of the amount property.
	 * 
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * Sets the value of the amount property.
	 * 
	 */
	public void setAmount(int value) {
		this.amount = value;
	}

}
