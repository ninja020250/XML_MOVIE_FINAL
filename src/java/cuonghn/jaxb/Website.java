
package cuonghn.jaxb;

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
 *       &lt;all>
 *         &lt;element name="websiteName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="websiteURL" type="{http://www.website.com}URLDeclare" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Website", namespace = "http://www.website.com")
public class Website {

    @XmlElement(namespace = "http://www.website.com")
    protected String websiteName;
    @XmlElement(namespace = "http://www.website.com")
    protected String websiteURL;

    /**
     * Gets the value of the websiteName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    
    public Website() {
    }

    public Website(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    /**
     * Sets the value of the websiteName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsiteName(String value) {
        this.websiteName = value;
    }

    /**
     * Gets the value of the websiteURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
     * Sets the value of the websiteURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebsiteURL(String value) {
        this.websiteURL = value;
    }

}
