
package cuonghn.jaxb.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
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
 *         &lt;element name="actorKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="directorKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="kindKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="nationKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="durationKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="yearKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="viewKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rateKeyword" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="host" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="webURL" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="XpathLinkEachItem" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathNameFilm" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xPathMainCategory" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathNameFilmEnglish" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathPagination" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathDescription" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathGroupInfo" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathRate" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathView" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathYear" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathDuration" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathNation" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathKind" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathDirector" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathActor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="xpathImgURL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "actorKeyword",
    "directorKeyword",
    "kindKeyword",
    "nationKeyword",
    "durationKeyword",
    "yearKeyword",
    "viewKeyword",
    "rateKeyword"
})
@XmlRootElement(name = "CrawlerConfig")
public class CrawlerConfig {

    @XmlElement(required = true)
    protected String actorKeyword;
    @XmlElement(required = true)
    protected String directorKeyword;
    @XmlElement(required = true)
    protected String kindKeyword;
    @XmlElement(required = true)
    protected String nationKeyword;
    @XmlElement(required = true)
    protected String durationKeyword;
    @XmlElement(required = true)
    protected String yearKeyword;
    @XmlElement(required = true)
    protected String viewKeyword;
    @XmlElement(required = true)
    protected String rateKeyword;
    @XmlAttribute(name = "host", required = true)
    protected String host;
    @XmlAttribute(name = "webURL", required = true)
    protected String webURL;
    @XmlAttribute(name = "XpathLinkEachItem", required = true)
    protected String xpathLinkEachItem;
    @XmlAttribute(name = "xpathNameFilm", required = true)
    protected String xpathNameFilm;
    @XmlAttribute(name = "xPathMainCategory", required = true)
    protected String xPathMainCategory;
    @XmlAttribute(name = "xpathNameFilmEnglish", required = true)
    protected String xpathNameFilmEnglish;
    @XmlAttribute(name = "xpathPagination", required = true)
    protected String xpathPagination;
    @XmlAttribute(name = "xpathDescription")
    protected String xpathDescription;
    @XmlAttribute(name = "xpathGroupInfo")
    protected String xpathGroupInfo;
    @XmlAttribute(name = "xpathRate")
    protected String xpathRate;
    @XmlAttribute(name = "xpathView")
    protected String xpathView;
    @XmlAttribute(name = "xpathYear")
    protected String xpathYear;
    @XmlAttribute(name = "xpathDuration")
    protected String xpathDuration;
    @XmlAttribute(name = "xpathNation")
    protected String xpathNation;
    @XmlAttribute(name = "xpathKind")
    protected String xpathKind;
    @XmlAttribute(name = "xpathDirector")
    protected String xpathDirector;
    @XmlAttribute(name = "xpathActor")
    protected String xpathActor;
    @XmlAttribute(name = "xpathImgURL")
    protected String xpathImgURL;

    /**
     * Gets the value of the actorKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActorKeyword() {
        return actorKeyword;
    }

    /**
     * Sets the value of the actorKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActorKeyword(String value) {
        this.actorKeyword = value;
    }

    /**
     * Gets the value of the directorKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDirectorKeyword() {
        return directorKeyword;
    }

    /**
     * Sets the value of the directorKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDirectorKeyword(String value) {
        this.directorKeyword = value;
    }

    /**
     * Gets the value of the kindKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKindKeyword() {
        return kindKeyword;
    }

    /**
     * Sets the value of the kindKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKindKeyword(String value) {
        this.kindKeyword = value;
    }

    /**
     * Gets the value of the nationKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNationKeyword() {
        return nationKeyword;
    }

    /**
     * Sets the value of the nationKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNationKeyword(String value) {
        this.nationKeyword = value;
    }

    /**
     * Gets the value of the durationKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDurationKeyword() {
        return durationKeyword;
    }

    /**
     * Sets the value of the durationKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDurationKeyword(String value) {
        this.durationKeyword = value;
    }

    /**
     * Gets the value of the yearKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getYearKeyword() {
        return yearKeyword;
    }

    /**
     * Sets the value of the yearKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setYearKeyword(String value) {
        this.yearKeyword = value;
    }

    /**
     * Gets the value of the viewKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getViewKeyword() {
        return viewKeyword;
    }

    /**
     * Sets the value of the viewKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setViewKeyword(String value) {
        this.viewKeyword = value;
    }

    /**
     * Gets the value of the rateKeyword property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRateKeyword() {
        return rateKeyword;
    }

    /**
     * Sets the value of the rateKeyword property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRateKeyword(String value) {
        this.rateKeyword = value;
    }

    /**
     * Gets the value of the host property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the value of the host property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHost(String value) {
        this.host = value;
    }

    /**
     * Gets the value of the webURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWebURL() {
        return webURL;
    }

    /**
     * Sets the value of the webURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWebURL(String value) {
        this.webURL = value;
    }

    /**
     * Gets the value of the xpathLinkEachItem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathLinkEachItem() {
        return xpathLinkEachItem;
    }

    /**
     * Sets the value of the xpathLinkEachItem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathLinkEachItem(String value) {
        this.xpathLinkEachItem = value;
    }

    /**
     * Gets the value of the xpathNameFilm property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathNameFilm() {
        return xpathNameFilm;
    }

    /**
     * Sets the value of the xpathNameFilm property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathNameFilm(String value) {
        this.xpathNameFilm = value;
    }

    /**
     * Gets the value of the xPathMainCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXPathMainCategory() {
        return xPathMainCategory;
    }

    /**
     * Sets the value of the xPathMainCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXPathMainCategory(String value) {
        this.xPathMainCategory = value;
    }

    /**
     * Gets the value of the xpathNameFilmEnglish property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathNameFilmEnglish() {
        return xpathNameFilmEnglish;
    }

    /**
     * Sets the value of the xpathNameFilmEnglish property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathNameFilmEnglish(String value) {
        this.xpathNameFilmEnglish = value;
    }

    /**
     * Gets the value of the xpathPagination property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathPagination() {
        return xpathPagination;
    }

    /**
     * Sets the value of the xpathPagination property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathPagination(String value) {
        this.xpathPagination = value;
    }

    /**
     * Gets the value of the xpathDescription property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathDescription() {
        return xpathDescription;
    }

    /**
     * Sets the value of the xpathDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathDescription(String value) {
        this.xpathDescription = value;
    }

    /**
     * Gets the value of the xpathGroupInfo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathGroupInfo() {
        return xpathGroupInfo;
    }

    /**
     * Sets the value of the xpathGroupInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathGroupInfo(String value) {
        this.xpathGroupInfo = value;
    }

    /**
     * Gets the value of the xpathRate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathRate() {
        return xpathRate;
    }

    /**
     * Sets the value of the xpathRate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathRate(String value) {
        this.xpathRate = value;
    }

    /**
     * Gets the value of the xpathView property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathView() {
        return xpathView;
    }

    /**
     * Sets the value of the xpathView property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathView(String value) {
        this.xpathView = value;
    }

    /**
     * Gets the value of the xpathYear property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathYear() {
        return xpathYear;
    }

    /**
     * Sets the value of the xpathYear property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathYear(String value) {
        this.xpathYear = value;
    }

    /**
     * Gets the value of the xpathDuration property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathDuration() {
        return xpathDuration;
    }

    /**
     * Sets the value of the xpathDuration property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathDuration(String value) {
        this.xpathDuration = value;
    }

    /**
     * Gets the value of the xpathNation property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathNation() {
        return xpathNation;
    }

    /**
     * Sets the value of the xpathNation property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathNation(String value) {
        this.xpathNation = value;
    }

    /**
     * Gets the value of the xpathKind property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathKind() {
        return xpathKind;
    }

    /**
     * Sets the value of the xpathKind property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathKind(String value) {
        this.xpathKind = value;
    }

    /**
     * Gets the value of the xpathDirector property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathDirector() {
        return xpathDirector;
    }

    /**
     * Sets the value of the xpathDirector property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathDirector(String value) {
        this.xpathDirector = value;
    }

    /**
     * Gets the value of the xpathActor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathActor() {
        return xpathActor;
    }

    /**
     * Sets the value of the xpathActor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathActor(String value) {
        this.xpathActor = value;
    }

    /**
     * Gets the value of the xpathImgURL property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getXpathImgURL() {
        return xpathImgURL;
    }

    /**
     * Sets the value of the xpathImgURL property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setXpathImgURL(String value) {
        this.xpathImgURL = value;
    }

}
