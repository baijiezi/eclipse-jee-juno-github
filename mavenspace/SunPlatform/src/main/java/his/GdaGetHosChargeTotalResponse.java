
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
 *         &lt;element name="GdaGetHosChargeTotalResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetHosChargeTotalResult"
})
@XmlRootElement(name = "GdaGetHosChargeTotalResponse")
public class GdaGetHosChargeTotalResponse {

    @XmlElement(name = "GdaGetHosChargeTotalResult")
    protected String gdaGetHosChargeTotalResult;

    /**
     * Gets the value of the gdaGetHosChargeTotalResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetHosChargeTotalResult() {
        return gdaGetHosChargeTotalResult;
    }

    /**
     * Sets the value of the gdaGetHosChargeTotalResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetHosChargeTotalResult(String value) {
        this.gdaGetHosChargeTotalResult = value;
    }

}
