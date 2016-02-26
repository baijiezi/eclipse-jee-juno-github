
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
 *         &lt;element name="GdaGetHosizedReceiptResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetHosizedReceiptResult"
})
@XmlRootElement(name = "GdaGetHosizedReceiptResponse")
public class GdaGetHosizedReceiptResponse {

    @XmlElement(name = "GdaGetHosizedReceiptResult")
    protected String gdaGetHosizedReceiptResult;

    /**
     * Gets the value of the gdaGetHosizedReceiptResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetHosizedReceiptResult() {
        return gdaGetHosizedReceiptResult;
    }

    /**
     * Sets the value of the gdaGetHosizedReceiptResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetHosizedReceiptResult(String value) {
        this.gdaGetHosizedReceiptResult = value;
    }

}
