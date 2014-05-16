/**
 * 
 */
package com.sun309.dto;

import org.xmappr.Element;
import org.xmappr.RootElement;

/**
 * @author Wormwood OPC_GROUP
 *
 */
@RootElement
public class Field 
{
	@Element
	private String 	name;
	@Element
	private String 	type;
	@Element
	private String 	value;
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	
	
}
