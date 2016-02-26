
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
 *         &lt;element name="GdaGetHosizedChargeResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetHosizedChargeResult"
})
@XmlRootElement(name = "GdaGetHosizedChargeResponse")
public class GdaGetHosizedChargeResponse {

    @XmlElement(name = "GdaGetHosizedChargeResult")
    protected String gdaGetHosizedChargeResult;

    /**
     * Gets the value of the gdaGetHosizedChargeResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetHosizedChargeResult() {
        return gdaGetHosizedChargeResult;
    }

    /**
     * Sets the value of the gdaGetHosizedChargeResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetHosizedChargeResult(String value) {
        this.gdaGetHosizedChargeResult = value;
    }

}
