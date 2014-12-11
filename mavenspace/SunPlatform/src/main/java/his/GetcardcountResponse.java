
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
 *         &lt;element name="getcardcountResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getcardcountResult"
})
@XmlRootElement(name = "getcardcountResponse")
public class GetcardcountResponse {

    protected String getcardcountResult;

    /**
     * Gets the value of the getcardcountResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetcardcountResult() {
        return getcardcountResult;
    }

    /**
     * Sets the value of the getcardcountResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetcardcountResult(String value) {
        this.getcardcountResult = value;
    }

}
