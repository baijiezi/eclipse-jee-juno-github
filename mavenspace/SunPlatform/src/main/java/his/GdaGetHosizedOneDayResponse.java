
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
 *         &lt;element name="GdaGetHosizedOneDayResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "gdaGetHosizedOneDayResult"
})
@XmlRootElement(name = "GdaGetHosizedOneDayResponse")
public class GdaGetHosizedOneDayResponse {

    @XmlElement(name = "GdaGetHosizedOneDayResult")
    protected String gdaGetHosizedOneDayResult;

    /**
     * Gets the value of the gdaGetHosizedOneDayResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGdaGetHosizedOneDayResult() {
        return gdaGetHosizedOneDayResult;
    }

    /**
     * Sets the value of the gdaGetHosizedOneDayResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGdaGetHosizedOneDayResult(String value) {
        this.gdaGetHosizedOneDayResult = value;
    }

}
