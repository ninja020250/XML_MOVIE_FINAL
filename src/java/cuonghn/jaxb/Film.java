package cuonghn.jaxb;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element name="filmID" type="{http://www.film.com}filmIDDeclare"/>
 *         &lt;element name="time" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="duration" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="limitAge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filmName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="websiteName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="websiteURL" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="rate" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="kindOfFilms" type="{http://www.film.com}kindOfFilmDeclare" maxOccurs="unbounded"/>
 *         &lt;element name="directors" type="{http://www.film.com}directorDeclare" maxOccurs="unbounded"/>
 *         &lt;element name="actors" type="{http://www.film.com}actorDeclare" maxOccurs="unbounded"/>
 *         &lt;element name="nation" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="imageURL" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="filmURL" type="{http://www.film.com}URLDeclare"/>
 *         &lt;element name="numberOfView" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "filmID",
    "time",
    "duration",
    "limitAge",
    "filmName",
    "engName",
    "websiteName",
    "websiteURL",
    "rate",
    "kindOfFilms",
    "directors",
    "actors",
    "nation",
    "imageURL",
    "description",
    "filmURL",
    "numberOfView",
    "searchContent"
})
@XmlRootElement(name = "Film")
public class Film implements Comparable< Film> {

    @XmlElement(required = true)
    protected String filmID;
    protected int time;
    protected Integer duration;
    protected String limitAge;
    @XmlElement(required = true)
    protected String filmName;
    @XmlElement(required = true)
    protected String engName;
    @XmlElement(required = true)
    protected String websiteName;
    @XmlElement(required = true)
    protected String websiteURL;
    protected float rate;
    @XmlElement(required = true)
    protected List<String> kindOfFilms;
    @XmlElement(required = true)
    protected List<String> directors;
    @XmlElement(required = true)
    protected List<String> actors;
    @XmlElement(required = true)
    protected String nation;
    protected String imageURL;
    protected String description;
    @XmlElement(required = true)
    protected String filmURL;
    protected int numberOfView;
    protected String searchContent;
    public Film(String filmName) {
        this.filmName = filmName;
        this.numberOfView = 0;
    }

    public Film() {
    }

    public Film(String filmID, String filmName, String imageURL) {
        this.filmID = filmID;
        this.filmName = filmName;
        this.imageURL = imageURL;
        this.numberOfView = 0;
    }

    public String getEngName() {
        return engName;
    }

    
    public String getSearchContent() {
        return searchContent;
    }

    /**
     * Gets the value of the filmID property.
     *
     * @return possible object is {@link String }
     *
     */
    public void setSearchContent(String searchContent) {    
        this.searchContent = searchContent;
    }

    public void setEngName(String engName) {
        this.engName = engName;
        String makeFilmID = engName;
        this.filmID = makeFilmID.replaceAll(" ", "");
    }

    public String getFilmID() {
        return filmID;
    }

    /**
     * Sets the value of the filmID property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFilmID(String value) {
        this.filmID = value;
    }

    /**
     * Gets the value of the time property.
     *
     */
    public int getTime() {
        return time;
    }

    /**
     * Sets the value of the time property.
     *
     */
    public void setTime(int value) {
        this.time = value;
    }

    /**
     * Gets the value of the duration property.
     *
     * @return possible object is {@link Integer }
     *
     */
    public Integer getDuration() {
        return duration;
    }

    /**
     * Sets the value of the duration property.
     *
     * @param value allowed object is {@link Integer }
     *
     */
    public void setDuration(Integer value) {
        this.duration = value;
    }

    /**
     * Gets the value of the limitAge property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getLimitAge() {
        return limitAge;
    }

    /**
     * Sets the value of the limitAge property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setLimitAge(String value) {
        this.limitAge = value;
    }

    /**
     * Gets the value of the filmName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFilmName() {
        return filmName;
    }

    /**
     * Sets the value of the filmName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFilmName(String value) {
        this.filmName = value;
    }

    /**
     * Gets the value of the websiteName property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getWebsiteName() {
        return websiteName;
    }

    /**
     * Sets the value of the websiteName property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setWebsiteName(String value) {
        this.websiteName = value;
    }

    /**
     * Gets the value of the websiteURL property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getWebsiteURL() {
        return websiteURL;
    }

    /**
     * Sets the value of the websiteURL property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setWebsiteURL(String value) {
        this.websiteURL = value;
    }

    /**
     * Gets the value of the rate property.
     *
     */
    public float getRate() {
        return rate;
    }

    /**
     * Sets the value of the rate property.
     *
     */
    public void setRate(float value) {
        this.rate = value;
    }

    /**
     * Gets the value of the kindOfFilms property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the kindOfFilms property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKindOfFilms().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     */
    public List<String> getKindOfFilms() {
        if (kindOfFilms == null) {
            kindOfFilms = new ArrayList<String>();
        }
        return this.kindOfFilms;
    }

    /**
     * Gets the value of the directors property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the directors property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDirectors().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     */
    public List<String> getDirectors() {
        if (directors == null) {
            directors = new ArrayList<String>();
        }
        return this.directors;
    }

    /**
     * Gets the value of the actors property.
     *
     * <p>
     * This accessor method returns a reference to the live list, not a
     * snapshot. Therefore any modification you make to the returned list will
     * be present inside the JAXB object. This is why there is not a
     * <CODE>set</CODE> method for the actors property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActors().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list {@link String }
     *
     *
     */
    public List<String> getActors() {
        if (actors == null) {
            actors = new ArrayList<String>();
        }
        return this.actors;
    }

    /**
     * Gets the value of the nation property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNation() {
        return nation;
    }

    /**
     * Sets the value of the nation property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setNation(String value) {
        this.nation = value;
    }

    /**
     * Gets the value of the imageURL property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getImageURL() {
        return imageURL;
    }

    /**
     * Sets the value of the imageURL property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setImageURL(String value) {
        this.imageURL = value;
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
     * Gets the value of the filmURL property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFilmURL() {
        return filmURL;
    }

    /**
     * Sets the value of the filmURL property.
     *
     * @param value allowed object is {@link String }
     *
     */
    public void setFilmURL(String value) {
        this.filmURL = value;
    }

    /**
     * Gets the value of the numberOfView property.
     *
     */
    public int getNumberOfView() {
        return numberOfView;
    }

    /**
     * Sets the value of the numberOfView property.
     *
     */
    public void setNumberOfView(int value) {
        this.numberOfView = value;
    }

    public String GetStringDirector() {
        String stringDirector = "";
        if (this.directors != null) {
            stringDirector = this.directors.get(0);
            for (int i = 1; i < this.directors.size(); i++) {
                stringDirector += "," + this.directors.get(i);
            }
        }
        return stringDirector;
    }

    public String getStringActor() {
        String stringActors = "";
        if (this.actors != null) {
            stringActors = this.actors.get(0);
            for (int i = 1; i < this.actors.size(); i++) {
                stringActors += "," + this.actors.get(i);
            }

        }
        return stringActors;
    }

    public void setKindOfFilms(List<String> kindOfFilms) {
        this.kindOfFilms = kindOfFilms;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    @Override
    public int compareTo(Film o) {
        return (o.getNumberOfView() - this.getNumberOfView());
    }
}
