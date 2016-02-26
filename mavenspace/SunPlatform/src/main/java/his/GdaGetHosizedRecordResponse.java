
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
 *         &lt;element name="GdaGetHosizedRecordResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetHosizedRecordResult"
})
@XmlRootElement(name = "GdaGetHosizedRecordResponse")
public class GdaGetHosizedRecordResponse {

    @XmlElement(name = "GdaGetHosizedRecordResult")
    protected String gdaGetHosizedRecordResult;

    /**
     * Gets the value of the gdaGetHosizedRecordResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetHosizedRecordResult() {
        return gdaGetHosizedRecordResult;
    }

    /**
     * Sets the value of the gdaGetHosizedRecordResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetHosizedRecordResult(String value) {
        this.gdaGetHosizedRecordResult = value;
    }

}
