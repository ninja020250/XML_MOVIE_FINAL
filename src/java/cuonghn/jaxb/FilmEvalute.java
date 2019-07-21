package cuonghn.jaxb;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 *
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rated" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element ref="{http://www.website.com}Website"/>
 *         &lt;element name="url" type="{http://www.filmEvalute.com}URLDeclare"/>
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
    "rated",
    "description",
    "website",
    "url",
    "numberOfView"
})
@XmlRootElement(name = "FilmEvalute", namespace = "http://www.filmEvalute.com")
public class FilmEvalute {

    @XmlElement(namespace = "http://www.filmEvalute.com")
    protected float rated;
    @XmlElement(namespace = "http://www.filmEvalute.com")
    protected String description;
    @XmlElement(name = "Website", namespace = "http://www.website.com", required = true)
    protected Website website;
    @XmlElement(namespace = "http://www.filmEvalute.com", required = true)
    protected String url;
    @XmlElement(namespace = "http://www.filmEvalute.com", required = true)
    protected int numberOfView;

    public int getNumberOfView() {
        return numberOfView;
    }

    /**
     * Gets the value of the rated property.
     *
     */
    public void setNumberOfView(int numberOfView) {
        this.numberOfView = numberOfView;
    }

    public float getRated() {
        return rated;
    }

    /**
     * Sets the value of the rated property.
     *
     */
    public void setRated(float value) {
        this.rated = value;
    }

    /**
     * Gets the value of the description property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the website property.
     *
     * @return possible object is {@link Website }
     *
     */
    public Website getWebsite() {
        return website;
    }

    /**
     * Sets the value of the website property.
     *
     * @param value allowed object is {@link Website }
     *
     */
    public void setWebsite(Website value) {
        this.website = value;
    }

    /**
     * Gets the value of the url property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUrl() {
        return url;
    }

    /**
     * Sets the value of the url property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setUrl(String value) {
        this.url = value;
    }

}
