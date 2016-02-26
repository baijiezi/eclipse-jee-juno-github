
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
 *         &lt;element name="getdoctorResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "getdoctorResult"
})
@XmlRootElement(name = "getdoctorResponse")
public class GetdoctorResponse {

    protected String getdoctorResult;

    /**
     * Gets the value of the getdoctorResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGetdoctorResult() {
        return getdoctorResult;
    }

    /**
     * Sets the value of the getdoctorResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGetdoctorResult(String value) {
        this.getdoctorResult = value;
    }

}
