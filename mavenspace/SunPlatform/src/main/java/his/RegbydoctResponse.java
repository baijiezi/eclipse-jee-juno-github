
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
 *         &lt;element name="regbydoctResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "regbydoctResult"
})
@XmlRootElement(name = "regbydoctResponse")
public class RegbydoctResponse {

    protected String regbydoctResult;

    /**
     * Gets the value of the regbydoctResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRegbydoctResult() {
        return regbydoctResult;
    }

    /**
     * Sets the value of the regbydoctResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRegbydoctResult(String value) {
        this.regbydoctResult = value;
    }

}
