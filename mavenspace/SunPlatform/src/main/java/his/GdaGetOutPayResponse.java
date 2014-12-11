
package his;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
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
 *         &lt;element name="GdaGetOutPayResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetOutPayResult"
})
@XmlRootElement(name = "GdaGetOutPayResponse")
public class GdaGetOutPayResponse {

    @XmlElement(name = "GdaGetOutPayResult")
    protected String gdaGetOutPayResult;

    /**
     * Gets the value of the gdaGetOutPayResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetOutPayResult() {
        return gdaGetOutPayResult;
    }

    /**
     * Sets the value of the gdaGetOutPayResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetOutPayResult(String value) {
        this.gdaGetOutPayResult = value;
    }

}
