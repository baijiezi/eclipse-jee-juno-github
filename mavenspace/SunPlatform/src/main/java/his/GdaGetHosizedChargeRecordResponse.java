
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
 *         &lt;element name="GdaGetHosizedChargeRecordResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetHosizedChargeRecordResult"
})
@XmlRootElement(name = "GdaGetHosizedChargeRecordResponse")
public class GdaGetHosizedChargeRecordResponse {

    @XmlElement(name = "GdaGetHosizedChargeRecordResult")
    protected String gdaGetHosizedChargeRecordResult;

    /**
     * Gets the value of the gdaGetHosizedChargeRecordResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetHosizedChargeRecordResult() {
        return gdaGetHosizedChargeRecordResult;
    }

    /**
     * Sets the value of the gdaGetHosizedChargeRecordResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetHosizedChargeRecordResult(String value) {
        this.gdaGetHosizedChargeRecordResult = value;
    }

}
