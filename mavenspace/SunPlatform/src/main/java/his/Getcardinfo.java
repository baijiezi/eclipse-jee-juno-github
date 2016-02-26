
package his;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="strval" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "strval"
})
@XmlRootElement(name = "getcardinfo")
public class Getcardinfo {

    protected String strval;

    /**
     * Gets the value of the strval property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStrval() {
        return strval;
    }

    /**
     * Sets the value of the strval property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStrval(String value) {
        this.strval = value;
    }

}
