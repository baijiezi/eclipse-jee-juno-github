
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
 *         &lt;element name="inXml" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "inXml"
})
@XmlRootElement(name = "AutoOPBillCharge")
public class AutoOPBillCharge {

    protected String inXml;

    /**
     * Gets the value of the inXml property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInXml() {
        return inXml;
    }

    /**
     * Sets the value of the inXml property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInXml(String value) {
        this.inXml = value;
    }

}
