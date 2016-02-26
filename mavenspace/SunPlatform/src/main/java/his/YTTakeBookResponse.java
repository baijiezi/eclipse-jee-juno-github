
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
 *         &lt;element name="YTTakeBookResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "ytTakeBookResult"
})
@XmlRootElement(name = "YTTakeBookResponse")
public class YTTakeBookResponse {

    @XmlElement(name = "YTTakeBookResult")
    protected String ytTakeBookResult;

    /**
     * Gets the value of the ytTakeBookResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYTTakeBookResult() {
        return ytTakeBookResult;
    }

    /**
     * Sets the value of the ytTakeBookResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYTTakeBookResult(String value) {
        this.ytTakeBookResult = value;
    }

}
